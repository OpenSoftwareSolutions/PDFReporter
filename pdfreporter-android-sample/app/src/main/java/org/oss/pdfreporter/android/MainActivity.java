package org.oss.pdfreporter.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.oss.pdfreporter.android.R;
import org.oss.pdfreporter.net.FileUrl;
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
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String TAG = "pdf-reporter-sample";
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
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
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

		findViewById(R.id.linearLayout1).setOnClickListener(
				new OnClickListener() {

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

		findViewById(R.id.textView1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				downloadUpdate();

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
				if (item.endsWith(".plist")) {
					list.add(item.substring(0, item.indexOf(".")));
				}
			}
		}
		return list;
	}

	public ReportPlist readPlist(String path) throws Exception {
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document doc = docBuilder.parse(new FileInputStream(path));
		NodeList keys = doc.getElementsByTagName("key");
		NodeList values = doc.getElementsByTagName("string");
		if (keys.getLength() != values.getLength())
			throw new Exception("Malformed plist");
		ReportPlist reportPlist = new ReportPlist();
		for (int i = 0; i < keys.getLength(); i++) {
			Node key = keys.item(i);
			Node value = values.item(i);
			String skey = key.getTextContent();
			String svalue = value.getTextContent();
			if (skey.equals("jrxml")) {
				reportPlist.setJrxml(svalue);
			} else if (skey.equals("resources")) {
				reportPlist.setResources(svalue);
			} else if (skey.equals("extra")) {
				reportPlist.setExtra(svalue);
			} else if (skey.equals("sqlite3")) {
				reportPlist.setSqlite3(svalue);
			} else if (skey.equals("xml")) {
				reportPlist.setXml(svalue);
			} else if (skey.equals("xpath")) {
				reportPlist.setXpath(svalue);
			}
		}
		return reportPlist;
	}

	public void generate() {
		if (plistFile != null) {
			final ProgressDialog progressDialog = ProgressDialog.show(this,
					"PDFReporter", "Generating report...", true, false);
			new Thread(new Runnable() {

				@Override
				public void run() {
					final String dirPath = getExternalFilesDir(null)
							+ "/reports";
					final String pdfPath = getExternalFilesDir(null)
							+ "/report.pdf";

					try {
						ReportPlist reportPlist = readPlist(dirPath + "/"
								+ plistFile + ".plist");
						String[] folders;
						if (TextUtils.isEmpty(reportPlist.getExtra())) {
							folders = new String[] { dirPath + "/"
									+ reportPlist.getResources() };
						} else {
							folders = new String[] {
									dirPath + "/" + reportPlist.getResources(),
									dirPath + "/" + reportPlist.getExtra() };
						}

						if (!TextUtils.isEmpty(reportPlist.getSqlite3())) {  
							// has SQL datasource
							ReportExporter.exportReportToPdfSql(pdfPath, dirPath
									+ "/" + reportPlist.getJrxml(), folders,
									dirPath + "/" + reportPlist.getSqlite3());
						} else if (!TextUtils.isEmpty(reportPlist.getXml())) { 
							// has XML datasource
							ReportExporter.exportReportToPdfXml(pdfPath, dirPath
									+ "/" + reportPlist.getJrxml(), folders,
									dirPath + "/" + reportPlist.getXml(), 
									reportPlist.getXpath());
						} else { 
							//has no datasource
							ReportExporter.exportReportToPdf(pdfPath, dirPath
									+ "/" + reportPlist.getJrxml(), folders);
						}
						

						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								progressDialog.dismiss();

								final Intent viewIntent = new Intent(
										Intent.ACTION_VIEW);
								viewIntent.setDataAndType(
										Uri.fromFile(new File(pdfPath)),
										"application/pdf");
								viewIntent
										.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

								final Intent emailIntent = new Intent(
										android.content.Intent.ACTION_SEND);
								emailIntent.setType("application/pdf");
								emailIntent.putExtra(Intent.EXTRA_STREAM,
										Uri.fromFile(new File(pdfPath)));

								AlertDialog.Builder builder = new Builder(
										MainActivity.this);
								builder.setTitle("Report created.")
										.setCancelable(true);
								boolean needOK = true;
								if (isAvailable(viewIntent)) {
									needOK = false;
									builder.setPositiveButton(
											"View",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													startActivity(viewIntent);
												}
											});
								}

								if (isAvailable(emailIntent)) {
									needOK = false;
									builder.setNegativeButton(
											"Send by E-Mail",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													startActivity(emailIntent);
												}
											});
								}
								if (needOK) {
									builder.setNeutralButton("OK", null);
								}
								builder.create().show();
							}
						});

					} catch (Exception e) {
						Log.e(TAG, "Failed to generate report : " + (e != null ? e.getMessage() : "null"));
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								progressDialog.dismiss();
								AlertDialog.Builder builder = new Builder(
										MainActivity.this);
								builder.setTitle("Report failed to generate.")
										.setNegativeButton("OK", null).create()
										.show();
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
		} else {
			super.onBackPressed();
		}
	}

	public boolean isAvailable(Intent intent) {
		final PackageManager mgr = getPackageManager();
		List<ResolveInfo> list = mgr.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	public void tryCopyAssetsToSDCard() {

		String dirPath = getExternalFilesDir(null) + "/reports";
		File dir = new File(dirPath);
		if (!dir.exists()) {
			final ProgressDialog progress = ProgressDialog.show(this,
					"Preparing for first run", "Copying files...", true, false);
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
				Log.e(TAG, "Failed to copy resoruces to SD card : " + (e != null ? e.getMessage() : null));
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
				AnimationUtils.loadAnimation(getApplicationContext(),
						R.anim.slide_out_up));
		findViewById(R.id.picker).setVisibility(View.GONE);
	}

	private void showPicker() {
		findViewById(R.id.picker).startAnimation(
				AnimationUtils.loadAnimation(getApplicationContext(),
						R.anim.slide_in_up));
		findViewById(R.id.picker).setVisibility(View.VISIBLE);
	}

	private void downloadUpdate() {
		String url = "http://pdfreporting.com/update-samples/reports.zip";
		DownloadTask task = new DownloadTask(this);
		task.execute(url);
	}

	private class DownloadTask extends AsyncTask<String, Integer, String> {

		ProgressDialog mProgressDialog;
		final int STATUS_DOWNLOADING = 0;
		final int STATUS_UNPACKING = 1;

		private Context context;

		public DownloadTask(Context context) {
			this.context = context;
		}

		@Override
		protected String doInBackground(String... sUrl) {
			// take CPU lock to prevent CPU from going off if the user
			// presses the power button during download
			String zipPath = getExternalFilesDir(null) + "/reports.zip";

			PowerManager pm = (PowerManager) context
					.getSystemService(Context.POWER_SERVICE);
			PowerManager.WakeLock wl = pm.newWakeLock(
					PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
			wl.acquire();

			try {
				InputStream input = null;
				OutputStream output = null;
				HttpURLConnection connection = null;
				try {
					URL url = new URL(sUrl[0]);
					connection = (HttpURLConnection) url.openConnection();
					connection.connect();

					// expect HTTP 200 OK, so we don't mistakenly save error
					// report
					// instead of the file
					if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
						return "Server returned HTTP "
								+ connection.getResponseCode() + " "
								+ connection.getResponseMessage();

					// this will be useful to display download percentage
					// might be -1: server did not report the length
					int fileLength = connection.getContentLength();

					// download the file
					input = connection.getInputStream();
					output = new FileOutputStream(zipPath);

					byte data[] = new byte[1024 * 16];
					long total = 0;
					int count;
					while ((count = input.read(data)) != -1) {
						// allow canceling with back button
						if (isCancelled())
							return null;
						total += count;
						// publishing the progress....
						if (fileLength > 0) // only if total length is known
							publishProgress(STATUS_DOWNLOADING,
									(int) (total * 100 / fileLength));
						output.write(data, 0, count);
					}
				} catch (Exception e) {
					return e.toString();
				} finally {
					try {
						if (output != null)
							output.close();
						if (input != null)
							input.close();
					} catch (IOException ignored) {
					}

					if (connection != null)
						connection.disconnect();
				}

				// Unzip
				publishProgress(STATUS_UNPACKING);
				String unzipPath = getExternalFilesDir(null) + "/zipreports/";
				String reportsPath = getExternalFilesDir(null) + "/reports/";
				File unzipDir = new File(unzipPath);
				DeleteRecursive(unzipDir);
				unzipDir.mkdirs();

				File reportsDir = new File(reportsPath);

				try {
					ZipInputStream zis = new ZipInputStream(
							new FileInputStream(zipPath));
					ZipEntry ze;

					byte[] buffer = new byte[1024 * 16];
					int count = 0;
					int zeCount = 0;
					int actual = 0;
					while ((ze = zis.getNextEntry()) != null) {
						zeCount++;
					}
					zis.close();
					zis = new ZipInputStream(new FileInputStream(zipPath));

					while ((ze = zis.getNextEntry()) != null) {
						publishProgress(STATUS_UNPACKING, actual++, zeCount);
						// zapis do souboru
						String filename = ze.getName();

						// Need to create directories if not exists, or
						// it will generate an Exception...
						if (ze.isDirectory()) {
							File fmd = new File(unzipPath + filename);
							fmd.mkdirs();
							continue;
						} else {
							String path = unzipPath + filename;
							File fmd = new File(path.substring(0,
									path.lastIndexOf("/")));
							fmd.mkdirs();
						}

						FileOutputStream fout = new FileOutputStream(unzipPath
								+ filename);

						// cteni zipu a zapis
						while ((count = zis.read(buffer)) != -1) {
							fout.write(buffer, 0, count);
						}

						fout.close();
						zis.closeEntry();
					}

					zis.close();

				} catch (IOException e) {

				}
				DeleteRecursive(reportsDir);
				unzipDir.renameTo(reportsDir);
				File zipFile = new File(zipPath);
				zipFile.delete();

			} finally {
				wl.release();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			// instantiate it within the onCreate method
			mProgressDialog = new ProgressDialog(context);
			mProgressDialog.setTitle("PDFReporter");
			mProgressDialog.setMessage("Downloading samples...");
			mProgressDialog.setIndeterminate(true);
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setCancelable(false);

			mProgressDialog.show();
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			super.onProgressUpdate(progress);
			if (progress[0] == STATUS_DOWNLOADING) {
				mProgressDialog.setIndeterminate(false);
				mProgressDialog.setProgress(progress[1]);
			} else {
				if (progress.length == 1) {
					mProgressDialog.setIndeterminate(true);
					mProgressDialog.setMessage("Updating samples...");
					mProgressDialog.setProgress(0);
					mProgressDialog.setMax(0);
				} else {
					mProgressDialog.setIndeterminate(false);
					mProgressDialog.setProgress(progress[1]);
					mProgressDialog.setMax(progress[2]);
				}
			}

		}

		@Override
		protected void onPostExecute(String result) {
			mProgressDialog.dismiss();
			if (result != null) {
				Toast.makeText(context, "Can't update samples.",
						Toast.LENGTH_LONG).show();
			}
			else {
				reportAdapter.setReportList(loadPlists());
			}
		}
	}

	void DeleteRecursive(File fileOrDirectory) {
		if (fileOrDirectory.isDirectory())
			for (File child : fileOrDirectory.listFiles())
				DeleteRecursive(child);

		fileOrDirectory.delete();
	}

}
