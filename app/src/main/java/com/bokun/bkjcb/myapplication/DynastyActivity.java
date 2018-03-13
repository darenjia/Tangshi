package com.bokun.bkjcb.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.bokun.bkjcb.myapplication.bean.Dynasty;
import com.bokun.bkjcb.myapplication.datebase.DataUtil;
import com.bokun.bkjcb.myapplication.fragment.DynastyContent;

import java.util.ArrayList;

public class DynastyActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynasty);
        String name = getIntent().getStringExtra("author");
        int id = getIntent().getIntExtra("id",0);
        viewPager = findViewById(R.id.dynasty_viewpager);
        frameLayout = findViewById(R.id.dynasty_content);
        if (name==null&&id!=0){
            frameLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.GONE);
            Dynasty dynasty = DataUtil.getDynasty(this,id);
            setTitle(dynasty.getName()+"作品");
            DynastyContent frgment = new DynastyContent();
            frgment.setDynasty(dynasty);
            getSupportFragmentManager().beginTransaction().add(R.id.dynasty_content,frgment).commit();
        }else {
            setTitle(name+"作品");
            ArrayList<Dynasty> dynasties = DataUtil.getDynasties_detail(this, name);
            fragments= new ArrayList<>();
            for (int i = 0; i < dynasties.size(); i++) {
                DynastyContent frgment = new DynastyContent();
                frgment.setDynasty(dynasties.get(i));
                fragments.add(frgment);
            }
            viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        }
    }

    public static void ToDynastyActivity(Context context, String auhtor) {
        Intent intent = new Intent(context, DynastyActivity.class);
        intent.putExtra("author", auhtor);
        context.startActivity(intent);
    }
    public static void ToDynastyActivity(Context context, int id) {
        Intent intent = new Intent(context, DynastyActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}

