package com.bokun.bkjcb.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bokun.bkjcb.myapplication.R;
import com.bokun.bkjcb.myapplication.bean.Author;
import com.bokun.bkjcb.myapplication.bean.Dynasty;

import java.util.ArrayList;

/**
 * Created by DengShuai on 2018/3/8.
 * Description :
 */

public class SimpleAdapter extends BaseAdapter {

    ArrayList list;
    Context context;
    int type;

    public SimpleAdapter(ArrayList<?> list, Context context,int type) {
        this.list = list;
        this.context = context;
        this.type=type;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView==null){
            convertView =  View.inflate(context,R.layout.adapter_layout,null);
             textView = convertView.findViewById(R.id.author);
            convertView.setTag(textView);
        }else {
             textView = (TextView) convertView.getTag();
        }
        if (type==1){
            textView.setText(((Author)list.get(position)).getName());
        }else {
            textView.setText(((Dynasty)list.get(position)).getName());
        }
        return textView;
    }

    public void replaceData(ArrayList<?> strings) {
        list.clear();
        list.addAll(strings);
        notifyDataSetChanged();
    }
}
