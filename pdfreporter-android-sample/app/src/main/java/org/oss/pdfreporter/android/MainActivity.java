package org.oss.pdfreporter.android;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final String TAG = "pdf-reporter-sample";
    private ArrayAdapter<ReportTestRunner.ReportPlist> mReportAdapter;
    private String mClickedReportIdentifier = null;

    private ReportTestRunner mReportTestRunner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReportTestRunner = new ReportTestRunner(this);

        mReportAdapter = new ArrayAdapter<>(this, R.layout.list_item, android.R.id.text1, mReportTestRunner.loadReportList());
        ListView list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(mReportAdapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ReportTestRunner.ReportPlist pickedItem = mReportAdapter.getItem(arg2);
                TextView tView = (TextView) findViewById(R.id.spinnerText);
                tView.setText(pickedItem.name);
                mClickedReportIdentifier = pickedItem.id;
                hidePicker();
            }
        });

        findViewById(R.id.picker).setOnClickListener(mOnClickListener);
        findViewById(R.id.linearLayout1).setOnClickListener(mOnClickListener);
        findViewById(R.id.button1).setOnClickListener(mOnClickListener);

        tryCopyAssetsToSDCard();
    }

    private final OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                    if (mClickedReportIdentifier != null) {
                        generate(mClickedReportIdentifier);
                    }
                    break;
                case R.id.linearLayout1:
                    showPicker();
                    break;
                case R.id.picker:
                    hidePicker();
                    break;

            }
        }
    };

    public void generate(String identifier) {

        final ProgressDialog progressDialog = ProgressDialog.show(this, "PDFReporter", "Generating report...", true, false);

        new AsyncTask<String, Void, ReportResult>() {

            @Override
            protected ReportResult doInBackground(String... params) {
                final String identifier = params[0];
                try {
                    String fileName = null;
                    fileName = mReportTestRunner.exportReport(identifier);
                    return new ReportResult(null, fileName);
                } catch (Exception e) {
                    Log.e(TAG, "Failed to generate report : " + e.getMessage());
                    return new ReportResult(e, null);
                }
            }


            @Override
            protected void onPostExecute(ReportResult e) {
                super.onPostExecute(e);

                if (e.exception == null) {
                    progressDialog.dismiss();
                    handleReportSuccess(e.filename);

                } else {
                    progressDialog.dismiss();
                    AlertDialog.Builder builder = new Builder(MainActivity.this);
                    builder.setTitle("Report failed to generate.")
                            .setNegativeButton("OK", null).create()
                            .show();
                }
            }
        }.execute(identifier);

    }

    public static final class ReportResult {
        final Exception exception;
        final String filename;

        public ReportResult(Exception exception, String filename) {
            this.exception = exception;
            this.filename = filename;
        }
    }

    private void handleReportSuccess(String reportFileName) {
        final Intent viewIntent = new Intent(Intent.ACTION_VIEW);
        viewIntent.setDataAndType(Uri.fromFile(new File(reportFileName)), "application/pdf");
        viewIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("application/pdf");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(reportFileName)));

        AlertDialog.Builder builder = new Builder(MainActivity.this);
        builder.setTitle("Report created.").setCancelable(true);
        boolean needOK = true;
        if (isAvailable(viewIntent)) {
            needOK = false;
            builder.setPositiveButton("View", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(viewIntent);
                }
            });
        }

        if (isAvailable(emailIntent)) {
            needOK = false;
            builder.setNegativeButton("Send by E-Mail", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(emailIntent);
                }
            });
        }
        if (needOK) {
            builder.setNeutralButton("OK", null);
        }
        builder.create().show();
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

        final String dirPath = getExternalFilesDir(null) + "/reports";
        final File dir = new File(dirPath);
        if (!dir.exists() || isApplicationUpdated()) {
            final ProgressDialog progress = ProgressDialog.show(this, "Preparing for first run", "Copying files...", true, false);

            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    if (isApplicationUpdated()) {
                        deleteRecursive(dir);
                    }
                    copyAsset("reports");
                    PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putInt("version", getCurrentVersion()).commit();
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    progress.dismiss();
                }
            }.execute();
        }
    }

    private boolean isApplicationUpdated() {
        int lastVersion = PreferenceManager.getDefaultSharedPreferences(this).getInt("version", 0);
        return getCurrentVersion() != lastVersion;
    }

    private int getCurrentVersion() {
        int currentVersion = 0;
        try {
            currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return currentVersion;
    }

    private void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
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
                Log.e(TAG, "Failed to copy resoruces to SD card : " + e.getMessage());
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

}
