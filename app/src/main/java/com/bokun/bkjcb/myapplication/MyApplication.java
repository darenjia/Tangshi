package com.bokun.bkjcb.myapplication;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by DengShuai on 2018/3/14.
 * Description :
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
