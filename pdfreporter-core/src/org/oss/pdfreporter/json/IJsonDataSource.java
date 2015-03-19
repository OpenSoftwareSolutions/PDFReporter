package org.oss.pdfreporter.json;

import java.io.Closeable;
import java.util.Locale;
import java.util.TimeZone;

import org.oss.pdfreporter.engine.JRRewindableDataSource;

public interface IJsonDataSource extends JRRewindableDataSource, Closeable {

	void setDatePattern(String dateFormatPattern);
	void setNumberPattern(String numberFormatPattern);
	void setLocale(Locale jsonLocale);
	void setLocale(String jsonLocale);
	void setTimeZone(TimeZone jsonTimezone);
	void setTimeZone(String jsonTimezone);


}
