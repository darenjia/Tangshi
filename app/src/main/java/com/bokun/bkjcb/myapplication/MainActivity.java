package com.bokun.bkjcb.myapplication;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bokun.bkjcb.myapplication.bean.Author;
import com.bokun.bkjcb.myapplication.bean.Dynasty;
import com.bokun.bkjcb.myapplication.datebase.DataUtil;
import com.bokun.bkjcb.myapplication.util.FileUtil;
import com.bokun.bkjcb.myapplication.util.JsonUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = findViewById(R.id.list);
        new AsyncTask<Void,Void,Void>(){
            ArrayList<Dynasty> dynasties;
            ArrayList<Author> authors;
            @Override
            protected Void doInBackground(Void... voids) {
                dynasties=JsonUtil.getDynasty(FileUtil.readFile(MainActivity.this,"唐诗.txt"));
                authors=JsonUtil.getAuthor(FileUtil.readFile(MainActivity.this,"author.txt"));
                DataUtil.insertAuthors(authors,MainActivity.this);
                DataUtil.insertDynasties(dynasties,MainActivity.this);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                MyAdapter adapter = new MyAdapter(dynasties);
                listView.setAdapter(adapter);
            }
        }.execute();
    }
    class MyAdapter extends BaseAdapter{
        ArrayList<Dynasty> authors;
        MyAdapter(ArrayList<Dynasty> authors){
           this.authors = authors;
        }
        @Override
        public int getCount() {
            return authors.size();
        }

        @Override
        public Object getItem(int position) {
            return authors.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(MainActivity.this);
            Dynasty author = authors.get(position);
            textView.setText(author.getName());
            Canvas canvas = new Canvas();
            canvas.drawText("李白",0,0,new Paint());
            return textView;
        }
    }
}
