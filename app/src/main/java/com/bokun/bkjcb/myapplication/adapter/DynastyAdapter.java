package com.bokun.bkjcb.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bokun.bkjcb.myapplication.R;
import com.bokun.bkjcb.myapplication.bean.Dynasty;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xrichtext.XRichText;

/**
 * Created by DengShuai on 2018/3/8.
 * Description :
 */

public class DynastyAdapter extends BaseAdapter {

    ArrayList list;
    Context context;
    int type;

    public DynastyAdapter(ArrayList<?> list, Context context, int type) {
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
        View view;
        XRichText title;
        XRichText content;
        if (convertView==null){
            convertView =  View.inflate(context,R.layout.search_item,null);
            view = convertView.findViewById(R.id.dynasty_search_item);
            convertView.setTag(view);
        }else {
             view = (View) convertView.getTag();
        }
        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);
        title.text(((Dynasty)list.get(position)).getName());
        content.text(((Dynasty)list.get(position)).getContent());
        return view;
    }

    public void replaceData(List<?> strings) {
        list.clear();
        list.addAll(strings);
        notifyDataSetChanged();
    }
}