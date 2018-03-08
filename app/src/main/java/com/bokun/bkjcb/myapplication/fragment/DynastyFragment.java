package com.bokun.bkjcb.myapplication.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.bokun.bkjcb.myapplication.DynastyActivity;
import com.bokun.bkjcb.myapplication.R;
import com.bokun.bkjcb.myapplication.adapter.SimpleAdapter;
import com.bokun.bkjcb.myapplication.bean.Dynasty;
import com.bokun.bkjcb.myapplication.datebase.DataUtil;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DengShuai on 2018/3/7.
 * Description :
 */

public class DynastyFragment extends Fragment {

    private EditText editText;
    private Disposable disposable;
    private ArrayList<Dynasty> dynasties;
    private ListView listView;
    private SimpleAdapter adapter;
    private static DynastyFragment fragment;

    public static DynastyFragment getInstance() {
        if (fragment == null) {
            fragment = new DynastyFragment();
        }
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_author, null);
        editText = view.findViewById(R.id.edit_search);
        listView = view.findViewById(R.id.listView);
        new DynastyFragment.LoadDataTask().execute();
        return view;
    }

    private Observable<String> createTextChangeObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> e) throws Exception {
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        e.onNext(s.toString().trim());
                    }
                });
            }
        }).debounce(500, TimeUnit.MILLISECONDS);
    }

    class LoadDataTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            dynasties = DataUtil.getDynasty(getContext());
            adapter = new SimpleAdapter(dynasties, getContext(), 0);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DynastyActivity.ToDynastyActivity(getContext(), dynasties.get(position).getId());
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Observable<String> observable = createTextChangeObservable();
        disposable = observable
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<String, ArrayList<Dynasty>>() {
                    @Override
                    public ArrayList<Dynasty> apply(String s) throws Exception {
                        return DataUtil.getDynasty(getContext(), s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Dynasty>>() {
                    @Override
                    public void accept(ArrayList<Dynasty> strings) throws Exception {
                        adapter.replaceData(strings);
                    }
                });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
