package org.oss.pdfreporter.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Util {

    public static void copyAsset(Context context, String file) {
        String extDir = context.getExternalFilesDir(null) + "/";
        try {
            InputStream is = context.getAssets().open(file);
            FileOutputStream os = new FileOutputStream(extDir + file);
            copyFile(is, os);
            is.close();
            os.flush();
            os.close();
        } catch (IOException e) {
            try {
                String[] list = context.getAssets().list(file);
                File dir = new File(extDir + file);
                dir.mkdir();
                for (String listFile : list) {
                    copyAsset(context, file + "/" + listFile);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
                Log.e(MainActivity.TAG, "Failed to copy resoruces to SD card : " + e.getMessage());
            }
        }
    }

    public static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024 * 16];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    public static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }


    public static int getCurrentVersion(Context context) {
        int currentVersion = 0;
        try {
            currentVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return currentVersion;
    }

    public static boolean isAvailable(Context context, Intent intent) {
        final PackageManager mgr = context.getPackageManager();
        List<ResolveInfo> list = mgr.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
