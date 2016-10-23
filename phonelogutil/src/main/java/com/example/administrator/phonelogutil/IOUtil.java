package com.example.administrator.phonelogutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/10/18.
 */
public class IOUtil {

    private static IOUtil util;

    private IOUtil() {
    }

    public static IOUtil getInstance() {
        if (util == null) {
            synchronized (IOUtil.class) {
                if (util == null) {
                    util = new IOUtil();
                }
            }
        }
        return util;
    }

    public void write(File file, String info) throws FileNotFoundException {
        FileOutputStream outputStream = new FileOutputStream(file, true);
        byte[] b = info.getBytes();
        try {
            outputStream.write(b, 0, b.length);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
