package com.bokun.bkjcb.myapplication.datebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    public ArrayList<Dynasty> query(String name){
        ArrayList<Dynasty> dynasties = new ArrayList<>();
        Cursor cursor = database.query("dynasty",new String[]{"id","name"},"where name = ?",new String[]{name},null,null,null);
        while (cursor.moveToNext()){
            Dynasty dynasty = new Dynasty();
            dynasty.setId(cursor.getInt(cursor.getColumnIndex("id")));
            dynasty.setName(cursor.getString(cursor.getColumnIndex("name")));
            dynasties.add(dynasty);
        }
        return dynasties;
    }
    public Dynasty query(int id){
        Dynasty dynasty = new Dynasty();
        Cursor cursor = database.query("dynasty",null,"where id = ?",new String[]{String.valueOf(id)},null,null,null);
        if (cursor.moveToNext()){
           dynasty.setTranslation(cursor.getString(cursor.getColumnIndex("translation")));
           dynasty.setContent(cursor.getString(cursor.getColumnIndex("content")));
           dynasty.setName(cursor.getString(cursor.getColumnIndex("name")));
           dynasty.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
           dynasty.setDescription(cursor.getString(cursor.getColumnIndex("des")));        }
        return dynasty;
    }
}
