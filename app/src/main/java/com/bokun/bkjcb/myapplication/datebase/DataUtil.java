package com.bokun.bkjcb.myapplication.datebase;

import android.content.Context;

import com.bokun.bkjcb.myapplication.bean.Author;
import com.bokun.bkjcb.myapplication.bean.Dynasty;
import com.bokun.bkjcb.myapplication.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DengShuai on 2018/3/1.
 * Description :
 */

public class DataUtil {
    public static void insertDynasties(ArrayList<Dynasty> dynasties, Context context) {
        DynastyDao dao = new DynastyDao(context);
        dao.insert(dynasties);
    }

    public static void insertAuthors(ArrayList<Author> authors, Context context) {
        AuthorDao dao = new AuthorDao(context);
        dao.insert(authors);
    }

    public static ArrayList<Author> getAllAuthor(Context context) {
        AuthorDao dao = new AuthorDao(context);
        return dao.query();
    }

    public static ArrayList<Author> getAuthor(Context context, String key) {
        AuthorDao dao = new AuthorDao(context);
        return dao.query(key);
    }

    public static Author getAuthorByname(Context context, String key) {
        AuthorDao dao = new AuthorDao(context);
        return dao.queryByName(key);
    }

    public static ArrayList<Dynasty> getDynasty(Context context, String key) {
        DynastyDao dao = new DynastyDao(context);
        return dao.queryByKey(key);
    }

    public static boolean isInsert(Context context) {
        AuthorDao dao = new AuthorDao(context);
        return dao.tableExist();
    }

    public static ArrayList<Dynasty> getDynasties(Context context, String name) {
        DynastyDao dao = new DynastyDao(context);
        return dao.query(name);
    }

    public static ArrayList<Dynasty> getDynasties_detail(Context context, String name) {
        DynastyDao dao = new DynastyDao(context);
        return dao.query_detail(name);
    }

    public static Dynasty getDynasty(Context context, int id) {
        DynastyDao dao = new DynastyDao(context);
        return dao.query(id);
    }

    public static ArrayList<Dynasty> getDynasty(Context context) {
        DynastyDao dao = new DynastyDao(context);
        return dao.query();
    }

    public static List<String> getTags(Context context) {
        AuthorDao dao = new AuthorDao(context);
        return dao.getTags();
    }

    public static ArrayList<Dynasty> getDynastyByKey(Context context, String key) {
        DynastyDao dao = new DynastyDao(context);
        ArrayList<Dynasty> dynasties;
        if (key.equals("")) {
            dynasties = dao.query();
        } else {
            dynasties = dao.queryByKey(key);
        }
        for (int i = 0; i < dynasties.size(); i++) {
            Dynasty dynasty = dynasties.get(i);
            if (key.equals("")) {
                dynasty.setContent(JsonUtil.getContent(dynasty.getContent()));
            } else {
                dynasty.setContent(JsonUtil.getContent(dynasty.getContent()).replace(key, "<span style='color:#FF4444'>" + key + "</span>"));
                dynasty.setName(dynasty.getName().replace(key, "<span style='color:#FF4444'>" + key + "</span>"));
            }
        }
        return dynasties;
    }

}
