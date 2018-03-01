package com.bokun.bkjcb.myapplication.datebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bokun.bkjcb.myapplication.bean.Dynasty;

import java.util.ArrayList;

/**
 * Created by DengShuai on 2018/3/1.
 * Description :
 */

public class DynastyDao {
    SQLiteDatabase database;
    public DynastyDao(Context context) {
        SQLiteOpenUtil util = new SQLiteOpenUtil(context);
        database=util.getWritableDatabase();
    }
    public void insert(Dynasty dynasty){
        ContentValues values = new ContentValues();
        values.put("author",dynasty.getAuthor());
        values.put("name",dynasty.getName());
        values.put("des",dynasty.getDescription());
        values.put("translation",dynasty.getTranslation());
        values.put("content",dynasty.getContent());
        database.insert("dynasty","id",values);
    }
    public void insert(ArrayList<Dynasty> dynasties){
        for (int i = 0; i < dynasties.size(); i++) {
            insert(dynasties.get(i));
        }
    }
}
