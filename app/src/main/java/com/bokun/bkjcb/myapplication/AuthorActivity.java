package com.bokun.bkjcb.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bokun.bkjcb.myapplication.bean.Author;
import com.bumptech.glide.Glide;

import me.codeboy.android.aligntextview.AlignTextView;

public class AuthorActivity extends AppCompatActivity {

    private Author author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        author = (Author) getIntent().getSerializableExtra("author");
        TextView author_name = findViewById(R.id.author_name);
        ImageView author_icon = findViewById(R.id.author_icon);
        AlignTextView author_des = findViewById(R.id.author_des);
        FloatingActionButton author_about = findViewById(R.id.author_about);
        author_name.setText(author.getName());
        Glide.with(this).load(author.getImage()).into(author_icon);
        author_des.setText(author.getDescription());
        author_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DynastyActivity.ToDynastyActivity(AuthorActivity.this, author.getName());
            }
        });
    }

    public static void ToAuthorActivity(Context context, Author author) {
        Intent intent = new Intent(context, AuthorActivity.class);
        intent.putExtra("author", author);
        context.startActivity(intent);
    }
}