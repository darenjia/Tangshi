package com.bokun.bkjcb.myapplication.util;

import com.bokun.bkjcb.myapplication.bean.Author;
import com.bokun.bkjcb.myapplication.bean.Dynasty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DengShuai on 2018/2/28.
 * Description :
 */

public class JsonUtil {
    public static ArrayList<Author> getAuthor(String s){
        ArrayList<Author> authors = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                Author author = new Author();
                JSONObject object = jsonArray.optJSONObject(i);
                author.setName(object.getString("author"));
                JSONArray array = new JSONArray(object.getString("image"));
                if (array.length()>0){
                    author.setImage(array.optString(0));
                }else {
                    author.setImage("");
                }
                JSONArray array1 = new JSONArray(object.getString("des"));
                if (array1.length()>0){
                    author.setDescription(array1.optString(0));
                }else {
                    author.setDescription("");
                }
                authors.add(author);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return authors;
    }
    public static ArrayList<Dynasty> getDynasty(String s){
        ArrayList<Dynasty> dynasties = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                Dynasty dynasty = new Dynasty();
                JSONObject object = jsonArray.optJSONObject(i);
                /*JSONArray array = object.getJSONArray("text");
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j <array.length() ; j++) {
                    builder.append(array.optString(j));

                }*/
                dynasty.setName(object.getString("title"));
                dynasty.setAuthor(object.getString("author"));
                dynasty.setDescription(object.getString("des"));
                dynasty.setTranslation(object.getString("translate"));
                dynasty.setContent(object.getString("text"));
                dynasties.add(dynasty);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dynasties;
    }

    public static ArrayList<String> getDynastyContent(String content){
        ArrayList<String> strings = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                String s = jsonArray.optString(i);
                if (s.contains("(")){
                    s= s.replace(s.substring(s.indexOf("("),s.indexOf(")")+1),"");
                }
                strings.add(s);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
