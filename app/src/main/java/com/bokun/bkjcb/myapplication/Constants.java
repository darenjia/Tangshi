package com.bokun.bkjcb.myapplication;

/**
 * Created by DengShuai on 2018/2/28.
 * Description :
 */

public class Constants {
    public static final String table_author="create table author(" +
            "id Integer primary key," +
            "name char(30)," +
            "image char(100)," +
            "description text" +
            ")";
    public static final String table_shi="create table content(" +
            "id Integer primary key," +
            "name char(50)," +
            "authorId int(10)," +
            "des int(10)," +
            "translation int(10)" +
            ")";
    public static final String table_dynasty="create table dynasty(" +
            "id Integer primary key," +
            "name char(50)," +
            "content text," +
            "author char(10)," +
            "des text," +
            "translation text" +
            ")";
    public static final String table_sx="create table description(" +
            "id Integer primary key," +
            "des text" +
            ")";
    public static final String table_fy="create table translation(" +
            "id Integer primary key," +
            "translation text" +
            ")";

}
