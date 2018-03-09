package com.bokun.bkjcb.myapplication.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bokun.bkjcb.myapplication.R;
import com.bokun.bkjcb.myapplication.bean.Dynasty;
import com.bokun.bkjcb.myapplication.util.JsonUtil;

import java.util.ArrayList;

import cn.droidlover.xrichtext.XRichText;

/**
 * Created by DengShuai on 2018/3/6.
 * Description :
 */

public class DynastyContent extends Fragment {
    private Dynasty dynasty;
    private int type;//1诗0,注释


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.dynasty_fragment_item, null);

        TextView title = view.findViewById(R.id.dynasty_name);
        TextView auhtor = view.findViewById(R.id.dynasty_author);
        LinearLayout linearLayout = view.findViewById(R.id.dynasty_contents);
        XRichText trans =view. findViewById(R.id.trans);
        XRichText des = view.findViewById(R.id.des);
        Typeface typeface1 = Typeface.createFromAsset(getContext().getAssets(),"fonts/songti.TTF");
        Typeface typeface2 = Typeface.createFromAsset(getContext().getAssets(),"fonts/peian.ttf");
        Typeface typeface3 = Typeface.createFromAsset(getContext().getAssets(),"fonts/kaiti.ttf");
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
            line = (TextView) View.inflate(getContext(), R.layout.dynasty_line, null);
            line.setText(contents.get(i));
            line.setTypeface(typeface3);
            linearLayout.addView(line);
        }
        return view;
    }

    public void setDynasty(Dynasty dynasty) {
        this.dynasty = dynasty;
    }

    public void setType(int type) {
        this.type = type;
    }
}
