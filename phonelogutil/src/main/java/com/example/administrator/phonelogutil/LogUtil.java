package com.example.administrator.phonelogutil;

import android.content.Context;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/10/18.
 */
public class LogUtil {
    private static FileUtil fileUtil;
    private static DateFormat dateFormat;
    private static String V = "VERBOSE：";
    private static String D = "DEBUG：";
    private static String I = "INFO：";
    private static String E = "ERROR：";
    private static String ENTER = "\n";
    private static String SPACE = "\t";

    public static void registUtil(Context context) {
        fileUtil = new FileUtil(context);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    }

    public static void unregistUtil() {
        fileUtil = null;
        dateFormat = null;
    }

    private static String getCurTimeStr() {
        return System.currentTimeMillis() + SPACE + dateFormat.format(System.currentTimeMillis());
    }

    private static synchronized void startWriteThread(final String systemOut) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    IOUtil.getInstance().write(fileUtil.getLogFile(), systemOut);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void v(String out) {
        synchronized (fileUtil) {
            String systemOut = getCurTimeStr() + V + out + ENTER;
            startWriteThread(systemOut);
        }
    }

    public static void d(String out) {
        synchronized (fileUtil) {
            final String systemOut = getCurTimeStr() + D + out + ENTER;
            startWriteThread(systemOut);
        }
    }

    public static void i(String out) {
        synchronized (fileUtil) {
            final String systemOut = getCurTimeStr() + I + out + ENTER;
            startWriteThread(systemOut);
        }
    }

    public static void e(String out) {
        synchronized (fileUtil) {
            final String systemOut = getCurTimeStr() + E + out + ENTER;
            startWriteThread(systemOut);
        }
    }

}

