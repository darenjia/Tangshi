package com.bokun.bkjcb.myapplication.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by DengShuai on 2018/2/28.
 * Description :
 */

public class FileUtil {
    public static String readFile(Context context,String fileName){
        StringBuilder builder = new StringBuilder();
        try {
            InputStream is= context.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String buffer;
            while ((buffer = reader.readLine())!=null){
                builder.append(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
