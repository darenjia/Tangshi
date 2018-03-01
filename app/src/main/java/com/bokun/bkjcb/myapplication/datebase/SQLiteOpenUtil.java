package com.bokun.bkjcb.myapplication.datebase;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bokun.bkjcb.myapplication.Constants;


/**
 * Created by DengShuai on 2017/3/20.
 * Description : 数据库打开工具类
 */

public class SQLiteOpenUtil extends SQLiteOpenHelper {

    public SQLiteOpenUtil(Context context) {
        super(context, "ds.db", null, 6);
    }

    public SQLiteOpenUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.table_author);
        db.execSQL(Constants.table_shi);
        db.execSQL(Constants.table_sx);
        db.execSQL(Constants.table_fy);
        db.execSQL(Constants.table_dynasty);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
