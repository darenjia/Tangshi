package com.bokun.bkjcb.myapplication;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bokun.bkjcb.myapplication.bean.Author;
import com.bokun.bkjcb.myapplication.bean.Dynasty;
import com.bokun.bkjcb.myapplication.datebase.DataUtil;
import com.bokun.bkjcb.myapplication.fragment.AuthorFragment;
import com.bokun.bkjcb.myapplication.fragment.DynastyFragment;
import com.bokun.bkjcb.myapplication.niceapp.ui.fragment.CardViewPagerFragment;
import com.bokun.bkjcb.myapplication.util.FileUtil;
import com.bokun.bkjcb.myapplication.util.JsonUtil;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CardViewPagerFragment fragment;
    private AuthorFragment authorFragment;
    private DynastyFragment dynastyFragment;
    private Toolbar toolbar;
    private long time;
    private DrawerLayout drawer;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RichText.initCacheDir(getCacheDir());
        FrameLayout layout = findViewById(R.id.frame_layout);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        new AsyncTask<Void, Void, Void>() {
            ArrayList<Dynasty> dynasties;
            ArrayList<Author> authors;

            @Override
            protected Void doInBackground(Void... voids) {
                if (!DataUtil.isInsert(Main2Activity.this)) {
                    dynasties = JsonUtil.getDynasty(FileUtil.readFile(Main2Activity.this, "唐诗.txt"));
                    authors = JsonUtil.getAuthor(FileUtil.readFile(Main2Activity.this, "author.txt"));
                    DataUtil.insertAuthors(authors, Main2Activity.this);
                    DataUtil.insertDynasties(dynasties, Main2Activity.this);
                    Log.i("Deng", "hah");
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {


                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
             /*   transaction.add(R.id.frame_layout, authorFragment);
                transaction.add(R.id.frame_layout, dynastyFragment);*/
                transaction.add(R.id.frame_layout, fragment);
                transaction.commit();
            }
        };
        authorFragment = AuthorFragment.getInstance();

        dynastyFragment = DynastyFragment.getInstance();
        fragment = CardViewPagerFragment.getInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_layout, authorFragment);
        transaction.add(R.id.frame_layout, dynastyFragment);
        transaction.add(R.id.frame_layout, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (System.currentTimeMillis() - time > 1000) {
                time = System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    android.os.Process.killProcess(android.os.Process.myPid());
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_camera) {
            transaction.hide(authorFragment);
            transaction.hide(dynastyFragment);
            transaction.show(fragment);
            toolbar.setTitle("首页");

        } else if (id == R.id.nav_gallery) {
            transaction.hide(fragment);
            transaction.hide(dynastyFragment);
            transaction.show(authorFragment);
            toolbar.setTitle("作者");
        } else if (id == R.id.nav_slideshow) {
            transaction.hide(fragment);
            transaction.hide(authorFragment);
            transaction.show(dynastyFragment);
            toolbar.setTitle("查询");
        } else if (id == R.id.nav_share) {
            DialogPlus dialog = DialogPlus.newDialog(this)
                    .setContentHolder(new ViewHolder(R.layout.content))
                    .setGravity(Gravity.BOTTOM)
                    .setExpanded(true, 400)  // This will enable the expand feature, (similar to android L share dialog)
                    .create();
            dialog.show();
        } else if (id == R.id.nav_send) {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
