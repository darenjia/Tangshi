package com.bokun.bkjcb.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bokun.bkjcb.myapplication.R;
import com.bokun.bkjcb.myapplication.bean.Dynasty;

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
        XRichText view = (XRichText) inflater.inflate(R.layout.dynasty_description, null);

        if (type == 0) {
            view.text(dynasty.getTranslation());
        } else {
            /*RichText rich_des = RichText.fromHtml(dynasty.getDescription()).into(description);
            RichText rich_trans = RichText.fromHtml(dynasty.getTranslation()).into(translation);*/
            view.text(dynasty.getDescription());
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
