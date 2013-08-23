package org.oss.pdfreporter.text;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.IllegalFormatException;
import java.util.IllegalFormatFlagsException;
import java.util.List;
import java.util.Locale;

public class Formatter {

	private static final int TEXT_MODE = 0;
	private static final int FORMAT_MODE = 1;
	private static final int CUSTOM_MODE = 2;

	private static final int FLAG_DATE_MODE = 0;
	private static final int WIDTH_DATE_MODE = 1;
	private static final int FORMAT_DATE_MODE = 2;

	public static String format(String format, Object... params) {
		StringBuilder sb = new StringBuilder();
		List<Chunk> chunkList = split(format);

		for (Chunk chunk : chunkList) {
			switch (chunk.mode) {
			case TEXT_MODE: {
				sb.append(chunk.val);
				break;
			}

			case FORMAT_MODE: {
				sb.append(String.format(chunk.val, params[chunk.param]));
				break;
			}

			case CUSTOM_MODE: {
				sb.append(parseDate(chunk.val, params[chunk.param]));
				break;
			}
			}
		}
		return sb.toString();
	}

	private static String parseDate(String value, Object param) {
		Calendar cal;
		boolean leftJustify = false;
		boolean upperCase = false;
		int width = -1;
		
		if (param instanceof Date) {
			cal = Calendar.getInstance();
			cal.setTime((Date) param);
		} else if (param instanceof Calendar) {
			cal = (Calendar) param;
		} else {
			throw new RuntimeException(
					"%t parameters must be a java.util.Date or a java.util.Calendar");
		}

		int lastInx = 0;
		int mode = FLAG_DATE_MODE;
		char conversion = 0;
		
		// skipping %
		for (int inx = 1; inx < value.length(); inx++) {
			char ch = value.charAt(inx);
			
			switch (mode) {
			
				case FLAG_DATE_MODE: {
					switch (ch) {
						case '#':
						case '+':
						case ' ':
						case '0':
						case ',':
						case '(': {
							throw new RuntimeException("t conversion supports only '-' flag");
						}
						case '-': {
							leftJustify = true;
							break;
						}
						default: {
							mode = WIDTH_DATE_MODE;
							lastInx = inx;
							inx--;
							break;
						}
					}
					break;
				}
				
				case WIDTH_DATE_MODE: {
					if(ch == 't' || ch == 'T') {
						if(ch =='T') upperCase = true;
						String chunk = value.substring(lastInx, inx);
						if(chunk.length()>0) {
							width = Integer.valueOf(chunk);
						}
						mode = FORMAT_DATE_MODE;
					}
					break;
				}
				
				case FORMAT_DATE_MODE: {
					conversion = ch;
					break;
				}
				
			}
		}
		String result = "Not supported, Yet!";
		switch(conversion) {
			case 'H': {
				result = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY));
				break;
			}
			case 'I': {
				result = String.format("%02d", cal.get(Calendar.HOUR));	
				break;	
			}
			case 'k': {
				result = String.format("%d", cal.get(Calendar.HOUR_OF_DAY));
				break;
			}
			case 'l': {
				result = String.format("%d", cal.get(Calendar.HOUR));
				break;
			}
			case 'M': {
				result = String.format("%02d", cal.get(Calendar.MINUTE));
				break;
			}
			case 'S': {
				result = String.format("%02d", cal.get(Calendar.SECOND));
				break;
			}
			case 'L': {
				result = String.format("%03d", cal.get(Calendar.MILLISECOND));
				break;
			}
			case 'N': {
				result = "000000000";
				break;
			}
			case 'p': {
				if (cal.get(Calendar.AM_PM) == Calendar.AM) result = "am";
				else result = "pm";
				break;
			}
			case 'z': {
				char sign = '+';
				int val = cal.get(Calendar.ZONE_OFFSET);
				if(val<0) {
					sign = '-';
					val = Math.abs(val);
				}
				val /= 1000*60;
				int h = val / 60;
				int m = val % 60;
				result = String.format("%c%02d%02d", sign, h, m);
				break;
			}
			case 'Z': {
				result = "Not supported!";
				break;
			}
			case 's': {
				result = String.format("%d", cal.getTimeInMillis()/1000);
				break;
			}
			case 'Q': {
				result = String.format("%d", cal.getTimeInMillis());
				break;
			}
			case 'B': {
				
			}
			case 'h':
			case 'b': {
				
			}
			case 'A': {
							
			}
			case 'a': {
				
			}
			case 'C': {
				result = String.format("%02d", cal.get(Calendar.YEAR)/100);
				break;
			}
			case 'Y': {
				result = String.format("%04d", cal.get(Calendar.YEAR));
				break;
			}
			case 'y': {
				result = String.format("%02d", cal.get(Calendar.YEAR)%100);
				break;
			}
			case 'j': {
				result = String.format("%03d", cal.get(Calendar.DAY_OF_YEAR));
				break;
			}
			case 'm': {
				result = String.format("%02d", cal.get(Calendar.MONTH)+1);
				break;
			}
			case 'd': {
				result = String.format("%02d", cal.get(Calendar.DATE));
				break;
			}
			case 'e': {
				result = String.format("%d", cal.get(Calendar.DATE));
				break;
			}
			case 'R': {
				result = String.format("%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
				break;
			}
			case 'T': {
				result = String.format("%02d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
				break;
			}
			case 'r': {
				String ampm =  (cal.get(Calendar.AM_PM) == Calendar.AM)? "AM" : "PM";
				result = String.format("%02d:%02d:%02d %s", cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND), ampm);
				break;
			}
			case 'D': {
				result = String.format("%02d/%02d/%02d", cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.YEAR)%100);
				break;
			}
			case 'F': {
				result = String.format("%04d-%02d-%02d",cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH));
				break;
			}
			case 'c': {
				
			}
			/*'R'	 Time formatted for the 24-hour clock as "%tH:%tM"
'T'	 Time formatted for the 24-hour clock as "%tH:%tM:%tS".
'r'	 Time formatted for the 12-hour clock as "%tI:%tM:%tS %Tp". The location of the morning or afternoon marker ('%Tp') may be locale-dependent.
'D'	 Date formatted as "%tm/%td/%ty".
'F'	ISO 8601 complete date formatted as "%tY-%tm-%td".
'c'	 Date and time formatted as "%ta %tb %td %tT %tZ %tY", e.g. "Sun Jul 20 16:17:00 EDT 1969".*/
		}
		if(upperCase) result = result.toUpperCase();
		if(result.length()<width) {
			int spaceLen = width-result.length();
			for(int i=0;i<spaceLen;i++) {
				if(leftJustify) result = result+' ';
				else result = ' '+result;
			}
		}
		return result;
	}

	private static List<Chunk> split(String value) {
		int lastInx = 0;
		int mode = TEXT_MODE;
		int paramCounter = 0;
		List<Chunk> chunkList = new ArrayList<Chunk>();

		for (int inx = 0; inx < value.length(); inx++) {
			char ch = value.charAt(inx);
			switch (mode) {

			case TEXT_MODE: {
				if (ch == '%') {
					String chunk = value.substring(lastInx, inx);
					if (chunk.length() > 0) {
						chunkList.add(new Chunk(chunk, TEXT_MODE));
					}
					lastInx = inx;
					mode = FORMAT_MODE;
				}
				break;
			}

			case FORMAT_MODE: {
				switch (ch) {
				case 'b':
				case 'B':
				case 'h':
				case 'H':
				case 's':
				case 'S':
				case 'c':
				case 'C':
				case 'd':
				case 'o':
				case 'x':
				case 'X':
				case 'e':
				case 'E':
				case 'f':
				case 'g':
				case 'G':
				case 'a':
				case 'A':
				case 'n':
				case '%': {
					String chunk = value.substring(lastInx, inx + 1);
					if (chunk.length() > 0) {
						int param;
						int pos$ = chunk.indexOf('$');
						if (pos$ == -1) {
							param = paramCounter++;
						} else {
							param = Integer.valueOf(chunk.substring(1, pos$)) - 1;
							chunk = "%"
									+ chunk.substring(pos$ + 1, chunk.length());
						}
						chunkList.add(new Chunk(chunk, FORMAT_MODE, param));
					}
					lastInx = inx + 1;
					mode = TEXT_MODE;
					break;
				}
				case 't':
				case 'T': {
					inx++;
					String chunk = value.substring(lastInx, inx + 1);
					if (chunk.length() > 0) {
						int param;
						int pos$ = chunk.indexOf('$');
						if (pos$ == -1) {
							param = paramCounter++;
						} else {
							param = Integer.valueOf(chunk.substring(1, pos$)) - 1;
							chunk = "%"
									+ chunk.substring(pos$ + 1, chunk.length());
						}
						chunkList.add(new Chunk(chunk, CUSTOM_MODE, param));
					}
					lastInx = inx + 1;
					mode = TEXT_MODE;
					break;
				}
				}
				break;
			}
			}
		}
		if (mode == TEXT_MODE) {
			String chunk = value.substring(lastInx, value.length());
			if (chunk.length() > 0) {
				chunkList.add(new Chunk(chunk, TEXT_MODE));
			}
		}

		return chunkList;
	}

}
