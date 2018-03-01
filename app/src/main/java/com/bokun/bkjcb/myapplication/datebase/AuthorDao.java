package com.bokun.bkjcb.myapplication.datebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bokun.bkjcb.myapplication.bean.Author;

import java.util.ArrayList;

/**
 * Created by DengShuai on 2018/3/1.
 * Description :
 */

public class AuthorDao {
    SQLiteDatabase database;
    public AuthorDao(Context context) {
        SQLiteOpenUtil util = new SQLiteOpenUtil(context);
        database=util.getWritableDatabase();
    }
    public void insert(Author author){
        ContentValues values = new ContentValues();
        values.put("image",author.getImage());
        values.put("name",author.getName());
        values.put("description",author.getDescription());
        database.insert("author","id",values);
    }
    public void insert(ArrayList<Author> authors){
        for (int i = 0; i < authors.size(); i++) {
            if (!query(authors.get(i))){
                insert(authors.get(i));
            }
        }
    }

    public boolean query(Author author){
        Cursor cursor = database.query("author",new String[]{"id"},"name=?",new String[]{author.getName()},null,null,null);
        if (cursor.moveToNext()){
            return true;
        }
        return false;
    }
}
