package com.bokun.bkjcb.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bokun.bkjcb.myapplication.bean.Dynasty;
import com.bokun.bkjcb.myapplication.datebase.DataUtil;
import com.bokun.bkjcb.myapplication.util.JsonUtil;

import java.util.ArrayList;

import cn.droidlover.xrichtext.XRichText;

public class DynastyActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynasty);
        int id = getIntent().getIntExtra("id", 0);
        Dynasty dynasty = DataUtil.getDynasty(this, id);

        viewPager = findViewById(R.id.dynasty_viewpager);
        TextView title = findViewById(R.id.dynasty_name);
        TextView auhtor = findViewById(R.id.dynasty_author);
        LinearLayout linearLayout = findViewById(R.id.dynasty_contents);
        XRichText trans = findViewById(R.id.trans);
        XRichText des = findViewById(R.id.des);
        Typeface typeface1 = Typeface.createFromAsset(getAssets(),"fonts/xingkai.ttf");
        Typeface typeface2 = Typeface.createFromAsset(getAssets(),"fonts/peian.ttf");
        Typeface typeface3 = Typeface.createFromAsset(getAssets(),"fonts/kaiti.ttf");
        title.setText(dynasty.getName());
        title.setTypeface(typeface2);
        auhtor.setText(dynasty.getAuthor());
        trans.text(dynasty.getTranslation());
        trans.setTypeface(typeface1);
        des.text(dynasty.getDescription());
        des.setTypeface(typeface1);
        ArrayList<String> contents = JsonUtil.getDynastyContent(dynasty.getContent());
        TextView line;
        for (int i = 0; i < contents.size(); i++) {
            line = (TextView) View.inflate(this, R.layout.dynasty_line, null);
            line.setText(contents.get(i));
            line.setTypeface(typeface3);
            linearLayout.addView(line);
        }
        FloatingActionButton button = findViewById(R.id.change_page);
       /* fragments = new ArrayList<>();
        DynastyContent content = new DynastyContent();

        content.setDynasty(dynasty);
        content.setType(0);
        fragments.add(content);
        DynastyContent des = new DynastyContent();
        des.setDynasty(dynasty);
        des.setType(1);
        fragments.add(des);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                v.setVisibility(View.GONE);
            }
        });
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

