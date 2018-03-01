package com.bokun.bkjcb.myapplication.datebase;

import android.content.Context;

import com.bokun.bkjcb.myapplication.bean.Author;
import com.bokun.bkjcb.myapplication.bean.Dynasty;

import java.util.ArrayList;

/**
 * Created by DengShuai on 2018/3/1.
 * Description :
 */

public class DataUtil {
    public static void insertDynasties(ArrayList<Dynasty> dynasties, Context context){
        DynastyDao dao = new DynastyDao(context);
        dao.insert(dynasties);
    }
    public static void insertAuthors(ArrayList<Author> authors, Context context){
        AuthorDao dao = new AuthorDao(context);
        dao.insert(authors);
    }
}
