package com.bokun.bkjcb.myapplication.niceapp.ui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bokun.bkjcb.myapplication.DynastyActivity;
import com.bokun.bkjcb.myapplication.R;
import com.bokun.bkjcb.myapplication.bean.Dynasty;
import com.bokun.bkjcb.myapplication.datebase.DataUtil;
import com.bokun.bkjcb.myapplication.niceapp.bean.Card;
import com.bokun.bkjcb.myapplication.niceapp.ui.adapter.DynastyAdapter;
import com.bokun.bkjcb.myapplication.niceapp.ui.widget.HtmlTextView;
import com.bokun.bkjcb.myapplication.niceapp.utils.AppUtils;


public class CardFragment extends AbsBaseFragment {
    protected Card mCard;


    protected TextView mAuthorText;
    protected ImageView mBottomEdgeImageView;
    protected TextView mBravoNumText;
    protected RelativeLayout mCardLayout;
    protected ListView mCoverImageView;
    protected HtmlTextView mDigestText;
    protected TextView mSubTitleText;
    protected TextView mTitleText;

    public static CardFragment getInstance(Card card) {
        CardFragment localCardFragment = new CardFragment();
        Bundle localBundle = new Bundle();
        localBundle.putSerializable("card", card);
        localCardFragment.setArguments(localBundle);
        return localCardFragment;
    }


    protected View initViews(LayoutInflater paramLayoutInflater) {
        View view = paramLayoutInflater.inflate(R.layout.fragment_card, null);
        mCardLayout = ((RelativeLayout) view.findViewById(R.id.box_card));
        mBottomEdgeImageView = ((ImageView) view.findViewById(R.id.image_bottom_edge));
        mCoverImageView = ((ListView) view.findViewById(R.id.image_cover));
        mTitleText = ((TextView) view.findViewById(R.id.text_title));
        mSubTitleText = ((TextView) view.findViewById(R.id.text_subtitle));
        mDigestText = ((HtmlTextView) view.findViewById(R.id.text_digest));
        mAuthorText = ((TextView) view.findViewById(R.id.text_author));
        mBravoNumText = ((TextView) view.findViewById(R.id.text_bravos));

        mTitleText.setText(this.mCard.getTitle());
        mTitleText.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/xingkai.ttf"));
        mSubTitleText.setText(this.mCard.getSubTitle());
        this.mBravoNumText.setText("  " + this.mCard.getUpNum());
        this.mDigestText.setText(mCard.getDigest());
        this.mAuthorText.setText(Html.fromHtml("<B>" + this.mCard.getAuthorName() + "</B>"));
        mCoverImageView.setAdapter(new DynastyAdapter(getContext(), DataUtil.getDynasties(getContext(), mCard.getTitle())));

        initAndDisplayCoverImage();

        return view;
    }

    protected void initAndDisplayCoverImage() {
        int coverWidth = AppUtils.getScreenDisplayMetrics(getActivity()).widthPixels - 2 * getResources().getDimensionPixelSize(R.dimen.card_margin);
        int coverHeight = (int) (180.0F * (coverWidth / 320.0F));
        ViewGroup.LayoutParams localLayoutParams = this.mCoverImageView.getLayoutParams();
        localLayoutParams.height = Float.valueOf(coverHeight).intValue();
        //加载图片
        int picResource = AppUtils.getDrawableIdByName(getActivity(), mCard.getCoverImgerUrl());
        mCoverImageView.setBackgroundResource(picResource);
    }

    protected void initData() {
        this.mCard = (Card) getArguments().getSerializable("card");
    }

    protected void initActions(View paramView) {
        mCoverImageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dynasty dynasty = (Dynasty) mCoverImageView.getAdapter().getItem(position);
                DynastyActivity.ToDynastyActivity(getContext(), mCard.getTitle());
            }
        });
    }

    public void onDestroy() {
        this.mCoverImageView.setAdapter(null);
        super.onDestroy();
    }

    public void onDestroyView() {
        this.mCoverImageView.setAdapter(null);
        super.onDestroyView();
    }
}