package com.wsy.everylesson.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.wsy.everylesson.R;

public class SearchActivity extends FragmentActivity {

    Fragment_Search dialogFragment;
    private TextView mListClass;
    private ImageView mIcSearchPublic;
    private TextView mTvClassify;
    private ConstraintLayout mColGroup;
    private ImageView mIcFilterUp1Common;
    private ImageView mIcFilterDown1Common;
    private TextView mTvSort;
    private TextView mTvScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_course);
        initView();

    }

    private void initView() {
        mListClass = (TextView) findViewById(R.id.class_list);
        mIcSearchPublic = (ImageView) findViewById(R.id.public_ic_search);
        mTvClassify = (TextView) findViewById(R.id.classify_tv);
        mColGroup = (ConstraintLayout) findViewById(R.id.group_col);
        mIcFilterUp1Common = (ImageView) findViewById(R.id.common_ic_filter_up1);
        mIcFilterDown1Common = (ImageView) findViewById(R.id.common_ic_filter_down1);
        mTvSort = (TextView) findViewById(R.id.sort_tv);
        mTvScreen = (TextView) findViewById(R.id.screen_tv);
        mIcFilterDown1Common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchDialog();
                mIcFilterDown1Common.setVisibility(View.GONE);
                mIcFilterUp1Common.setVisibility(View.VISIBLE);
            }
        });



    }

    private void showSearchDialog() {
        // Create and show the dialog. 
        if (dialogFragment == null) {
            dialogFragment = new Fragment_Search();
            dialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
            dialogFragment.show(getSupportFragmentManager(), "dialog");

        }else {
            mIcFilterDown1Common.setVisibility(View.VISIBLE);
            mIcFilterUp1Common.setVisibility(View.GONE);
        }
    }
}
