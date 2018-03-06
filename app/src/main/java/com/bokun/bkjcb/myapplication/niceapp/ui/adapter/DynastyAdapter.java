package com.bokun.bkjcb.myapplication.niceapp.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bokun.bkjcb.myapplication.R;
import com.bokun.bkjcb.myapplication.bean.Dynasty;

import java.util.ArrayList;

/**
 * Created by DengShuai on 2018/3/6.
 * Description :
 */

public class DynastyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Dynasty> dynasties;

    public DynastyAdapter(Context context, ArrayList<Dynasty> dynasties) {
        this.context = context;
        this.dynasties = dynasties;
    }

    @Override
    public int getCount() {
        return dynasties.size();
    }

    @Override
    public Object getItem(int position) {
        return dynasties.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.dynasty_item, null);
            textView = convertView.findViewById(R.id.title);
            convertView.setTag(textView);

        } else {
            textView = (TextView) convertView.getTag();
        }
        textView.setText(dynasties.get(position).getName());
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/songti.TTF"));
        return convertView;
    }
}
