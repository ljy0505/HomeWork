package com.wsy.everylesson.fragment;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.wsy.everylesson.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends Fragment implements View.OnClickListener {


    private TextView mListClass;
    private ImageView mIcSearchPublic;
    private TextView mTvClassify;
    private ImageView mIcFilterUp1Common;
    private ImageView mIcFilterDown1Common;
    private TextView mTvSort;
    private ImageView mIcFilterUp2Common;
    private ImageView mIcFilterDown2Common;
    private TextView mTvScreen;
    private ImageView mIcFilterUp3Common;
    private ImageView mIcFilterDown3Common;
    private ConstraintLayout mColGroup;
    private ConstraintLayout mListColsClass;

    public CourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_course, container, false);
//        startActivity(new Intent(getActivity(),SearchActivity.class));
        initView(inflate);
        return inflate;

    }

    private void initView(@NonNull final View itemView) {

        mListClass = (TextView) itemView.findViewById(R.id.class_list);
        mIcSearchPublic = (ImageView) itemView.findViewById(R.id.public_ic_search);
        mTvClassify = (TextView) itemView.findViewById(R.id.classify_tv);
        mIcFilterUp1Common = (ImageView) itemView.findViewById(R.id.common_ic_filter_up1);
        mIcFilterUp1Common.setOnClickListener(this);
        mIcFilterDown1Common = (ImageView) itemView.findViewById(R.id.common_ic_filter_down1);
        mIcFilterDown1Common.setOnClickListener(this);
        mTvSort = (TextView) itemView.findViewById(R.id.sort_tv);
        mIcFilterUp2Common = (ImageView) itemView.findViewById(R.id.common_ic_filter_up2);
        mIcFilterUp2Common.setOnClickListener(this);
        mIcFilterDown2Common = (ImageView) itemView.findViewById(R.id.common_ic_filter_down2);
        mIcFilterDown2Common.setOnClickListener(this);
        mTvScreen = (TextView) itemView.findViewById(R.id.screen_tv);
        mIcFilterUp3Common = (ImageView) itemView.findViewById(R.id.common_ic_filter_up3);
        mIcFilterUp3Common.setOnClickListener(this);
        mIcFilterDown3Common = (ImageView) itemView.findViewById(R.id.common_ic_filter_down3);
        mIcFilterDown3Common.setOnClickListener(this);
        mColGroup = (ConstraintLayout) itemView.findViewById(R.id.group_col);
        mListColsClass = (ConstraintLayout) itemView.findViewById(R.id.class_list_cols);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_ic_filter_up1:
                mIcFilterDown1Common.setVisibility(View.VISIBLE);
                mIcFilterUp1Common.setVisibility(View.GONE);

                break;
            case R.id.common_ic_filter_down1:
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_classify, null);
                showPop(view);
                mIcFilterDown1Common.setVisibility(View.GONE);
                mIcFilterUp1Common.setVisibility(View.VISIBLE);

                break;
            case R.id.common_ic_filter_up2:

                break;
            case R.id.common_ic_filter_down2:
                View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.pop_sort, null);
                showPop(view2);
                mIcFilterDown2Common.setVisibility(View.GONE);
                mIcFilterUp2Common.setVisibility(View.VISIBLE);
                break;
            case R.id.common_ic_filter_up3:

                break;
            case R.id.common_ic_filter_down3:
                View view3 = LayoutInflater.from(getActivity()).inflate(R.layout.pop_screen, null);
                showPop(view3);
                mIcFilterDown3Common.setVisibility(View.GONE);
                mIcFilterUp3Common.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private void showPop(View view) {
        //弹出一个框
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_classify, null);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //点击框外面消失
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);

        //若不写，则不可再popWindow上输入内容
        //设置焦点
        popupWindow.setFocusable(true);

        RadioButton chuyi = view.findViewById(R.id.chuyi);
        chuyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                chuyi.setBackground(R.drawable.radiobutton_sel);
                Toast.makeText(getActivity(), "点击了初一", Toast.LENGTH_SHORT).show();
            }
        });
        //设置全屏阴影
        backgroundAlpha(0.5f);
        //pop进出动画
//        popupWindow.setAnimationStyle(R.style.PopAnimation);
        if (!popupWindow.isShowing()) {
            //相当于按钮，做出位置移动
            popupWindow.showAsDropDown(mColGroup, 0, 0);
            //相当于父容器，做出位置移动
//            popupWindow.showAtLocation(mRlPop, Gravity.BOTTOM, 0, 0);
        }
        
        //pop消失监听
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mIcFilterDown1Common.setVisibility(View.VISIBLE);
                mIcFilterUp1Common.setVisibility(View.GONE);
                mIcFilterDown2Common.setVisibility(View.VISIBLE);
                mIcFilterUp2Common.setVisibility(View.GONE);
                mIcFilterDown3Common.setVisibility(View.VISIBLE);
                mIcFilterUp3Common.setVisibility(View.GONE);
                popupWindow.dismiss();//点击其他地方popWindow消失，不会出现只要点击条目就弹出pop
                backgroundAlpha(1f);
            }
        });
    }

    private void backgroundAlpha(float v) {
        //获取屏幕桌面属性
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        //属性赋值
        attributes.alpha = v;
        //设置属性
        getActivity().getWindow().setAttributes(attributes);
    }
}
