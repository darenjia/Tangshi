package com.bokun.bkjcb.myapplication.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by DengShuai on 2017/7/10.
 */

public class SQLUtil {
    private SQLiteDatabase database;
    public static SQLUtil sqlUtil;
    private Context context;

    private SQLUtil(Context context) {
        this.context = context;
    }

    public static SQLUtil newInstance(Context context) {
        if (sqlUtil == null) {
            sqlUtil = new SQLUtil(context);
        }
        return sqlUtil;
    }

    public SQLiteDatabase getDatabase() {
        SQLiteOpenUtil util = new SQLiteOpenUtil(context);
        try {
            util.createDataBase();
            database = util.openDataBase();
            database.setLocale(Locale.CHINESE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return database;
    }

    public void closeDatabase() {
        database.close();
    }
}
