package org.oss.pdfreporter.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.oss.pdfreporter.android.pdfreporter.R;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private ReportAdapter reportAdapter;
	private String plistFile = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		reportAdapter = new ReportAdapter(this);
		ListView list = (ListView) findViewById(R.id.listView1);
		list.setAdapter(reportAdapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String text = (String) arg0.getItemAtPosition(arg2);
				TextView tView = (TextView) findViewById(R.id.spinnerText);
				tView.setText(text);
				plistFile = text;
				hidePicker();
			}
		});

		findViewById(R.id.picker).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				hidePicker();
			}
		});

		findViewById(R.id.linearLayout1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showPicker();
			}
		});

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				generate();
			}
		});

		tryCopyAssetsToSDCard();
		reportAdapter.setReportList(loadPlists());
	}
	
	public List<String> loadPlists() {
		String dirPath = getExternalFilesDir(null) + "/reports";
		File dir = new File(dirPath);
		ArrayList<String> list = new ArrayList<String>();
		if (dir.exists() && dir.isDirectory()) {
			String[] items = dir.list();
			for (String item : items) {
				if(item.endsWith(".plist")) {
					list.add(item.substring(0, item.indexOf(".")));
				}
			}
		}
		return list;
	}
	
	public ReportPlist readPlist(String path) throws Exception {
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.parse(new FileInputStream(path));
		NodeList keys = doc.getElementsByTagName("key");
		NodeList values = doc.getElementsByTagName("string");
		if(keys.getLength() != values.getLength()) throw new Exception("Malformed plist");
		ReportPlist reportPlist = new ReportPlist();
		for (int i=0; i<keys.getLength(); i++) {
			Node key = keys.item(i);
			Node value = values.item(i);
			String skey = key.getTextContent();
			String svalue = value.getTextContent();
			if(skey.equals("jrxml")) {
				reportPlist.setJrxml(svalue);
			}
			else if(skey.equals("resources")) {
				reportPlist.setResources(svalue);
			}
			else if(skey.equals("extra")) {
				reportPlist.setExtra(svalue);
			}
			else if(skey.equals("sqlite3")) {
				reportPlist.setSqlite3(svalue);
			}
			else if(skey.equals("xml")) {
				reportPlist.setXml(svalue);
			}
			else if(skey.equals("xpath")) {
				reportPlist.setXpath(svalue);
			}
		}
		return reportPlist;
	}

	public void generate() {
		if(plistFile != null) {
			final ProgressDialog progressDialog = ProgressDialog.show(this, "PDFReporter", "Generating report...", true, false);
			new Thread(new Runnable() {

				@Override
				public void run() {
					final String dirPath = getExternalFilesDir(null) + "/reports";
					final String pdfPath = getExternalFilesDir(null) + "/output.pdf";
					
					
					try {
						ReportPlist reportPlist = readPlist(dirPath+"/"+plistFile+".plist");
						String[] folders;
						if(TextUtils.isEmpty(reportPlist.getExtra())) {
							folders = new String[]{dirPath+"/"+reportPlist.getResources()};
						}
						else {
							folders = new String[]{dirPath+"/"+reportPlist.getResources(), dirPath+"/"+reportPlist.getExtra()};
						}
						
						ReportExporter.exportReportToPdf(pdfPath,dirPath + "/"+reportPlist.getJrxml(), folders);
						
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								try {
									
									progressDialog.dismiss();
									Intent intent = new Intent(Intent.ACTION_VIEW);
									intent.setDataAndType(Uri.fromFile(new File(pdfPath)), "application/pdf");
									intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
									startActivity(intent);
									
								} catch (ActivityNotFoundException e1) {
									runOnUiThread(new Runnable() {

										@Override
										public void run() {
											progressDialog.dismiss();
											AlertDialog.Builder builder = new Builder(MainActivity.this);
											builder.setTitle("Report created.").setPositiveButton("OK", null).create().show();

										}
									});
								}
							}
						});

					} catch (Exception e) {
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								progressDialog.dismiss();
								AlertDialog.Builder builder = new Builder(MainActivity.this);
								builder.setTitle("Report failed to generate.").setNegativeButton("OK", null).create().show();
							}
						});
					}
				}
			}).start();
		}
	}

	@Override
	public void onBackPressed() {
		if (findViewById(R.id.picker).getVisibility() == View.VISIBLE) {
			hidePicker();
		}
		else {
			super.onBackPressed();
		}
	}

	public void tryCopyAssetsToSDCard() {

		String dirPath = getExternalFilesDir(null) + "/reports";
		File dir = new File(dirPath);
		if (!dir.exists()) {
			final ProgressDialog progress = ProgressDialog.show(this, "Preparing for first run", "Copying files...",
					true, false);
			Thread copyThread = new Thread(new Runnable() {

				@Override
				public void run() {
					copyAsset("reports");
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							progress.dismiss();
							reportAdapter.setReportList(loadPlists());
						}
					});
				}
			});
			copyThread.start();
		}
	}

	private void copyAsset(String file) {
		String extDir = getExternalFilesDir(null) + "/";
		try {
			InputStream is = getAssets().open(file);
			FileOutputStream os = new FileOutputStream(extDir + file);
			copyFile(is, os);
			is.close();
			os.flush();
			os.close();
		} catch (IOException e) {
			try {
				String[] list = getAssets().list(file);
				File dir = new File(extDir + file);
				dir.mkdir();
				for (String listFile : list) {
					copyAsset(file + "/" + listFile);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024 * 16];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

	private void hidePicker() {
		findViewById(R.id.picker).startAnimation(
				AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_up));
		findViewById(R.id.picker).setVisibility(View.GONE);
	}

	private void showPicker() {
		findViewById(R.id.picker).startAnimation(
				AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_up));
		findViewById(R.id.picker).setVisibility(View.VISIBLE);
	}
}
