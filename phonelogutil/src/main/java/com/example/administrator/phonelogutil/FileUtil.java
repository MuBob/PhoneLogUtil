package com.example.administrator.phonelogutil;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/10/18.
 */
public class FileUtil {
    public static String logFileName = "log.txt";
    public static String logFilePath = "Log";
    private Context mContext;
    public FileUtil(Context context) {
        mContext = context;
    }

    public String getPath() {
        String path = null;
        if (mContext != null) {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                path = mContext.getExternalCacheDir().getAbsolutePath();
            } else {
                path = mContext.getCacheDir().getAbsolutePath();
            }
        } else {
            path = Environment.getRootDirectory().getAbsolutePath();
        }
        return path;
    }

    public String getLogPath() {
        return getPath() + File.separator + logFilePath;
    }

    public String getLogUrl() {
        String logPath = getLogPath();
        File logPathFile = new File(logPath);
        if (!logPathFile.exists()) {
            logPathFile.mkdirs();
        }
        return getLogPath() + File.separator + logFileName;
    }

    public File getLogFile() {
        String logUrl = getLogUrl();
        File file = new File(logUrl);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

}
