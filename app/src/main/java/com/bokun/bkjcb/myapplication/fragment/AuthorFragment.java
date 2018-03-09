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
import android.widget.ImageView;
import android.widget.ListView;

import com.bokun.bkjcb.myapplication.AuthorActivity;
import com.bokun.bkjcb.myapplication.R;
import com.bokun.bkjcb.myapplication.adapter.SimpleAdapter;
import com.bokun.bkjcb.myapplication.bean.Author;
import com.bokun.bkjcb.myapplication.datebase.DataUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
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

public class AuthorFragment extends Fragment {

    private EditText editText;
    private Disposable disposable;
    private ArrayList<Author> authors;
    private ListView listView;
    private SimpleAdapter adapter;
    private static AuthorFragment fragment;
    private TagContainerLayout layout;
    private List<String> strings;
    private ImageView close;

    public static AuthorFragment getInstance() {
        if (fragment == null) {
            fragment = new AuthorFragment();
        }
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_author, null);
        editText = view.findViewById(R.id.edit_search);
        listView = view.findViewById(R.id.listView);
        layout = view.findViewById(R.id.tags);
        close = view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               editText.setText("");
               close.setVisibility(View.GONE);
            }
        });
        new LoadDataTask().execute();
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
                        if (s.toString().equals("")) {
                            close.setVisibility(View.GONE);
                            adapter.replaceData(new ArrayList<>());
                        } else {
                            close.setVisibility(View.VISIBLE);
                            e.onNext(s.toString().trim());
                        }
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
            authors = new ArrayList<>();
            adapter = new SimpleAdapter(authors, getContext(), 1);
            strings = DataUtil.getTags(getContext());
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AuthorActivity.ToAuthorActivity(getContext(), authors.get(position));
                }
            });
            layout.setTags(strings);
            layout.setOnTagClickListener(new TagView.OnTagClickListener() {
                @Override
                public void onTagClick(int position, String text) {
                    editText.setText(text);
                }

                @Override
                public void onTagLongClick(int position, String text) {

                }

                @Override
                public void onTagCrossClick(int position) {

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
                .map(new Function<String, ArrayList<Author>>() {
                    @Override
                    public ArrayList<Author> apply(String s) throws Exception {
                        return DataUtil.getAuthor(getContext(), s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Author>>() {
                    @Override
                    public void accept(ArrayList<Author> strings) throws Exception {
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
