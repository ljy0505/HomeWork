package com.wsy.everylesson.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.wsy.everylesson.R;
import com.wsy.everylesson.login.PhonePaw_Login;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonageFragment extends Fragment {


    private ImageView mUserInfoBg;
    private TextView mClassSizaMy;
    private TextView mClassNameMy;
    private TextView mClassDetailsMy;
    private ConstraintLayout mClassColMy;
    private TextView mOrderSizaMy;
    private TextView mOrderNameMy;
    private TextView mOrderDetailsMy;
    private ConstraintLayout mOrderColMy;
    private TextView mStudyGoldSizaMy;
    private TextView mStudyGoldNameMy;
    private TextView mStudyGoldDetailsMy;
    private ConstraintLayout mStudyGoldColMy;
    private ImageView mIcUnloginHeadMain;
    private ImageView mIconUserVipMain;
    private TextView mTvLogin;
    private ImageView mIconEditMain;
    private ImageView mIconInventeMain;
    private TextView mTvDiscounts;
    private ImageView mArrowRightCommon;
    private TextView mTvCourse;
    private ImageView mIconOrderMain;
    private TextView mWorkTvMe;
    private ImageView mIconConcernMain;
    private TextView mTeacherTvAttention;
    private ImageView mIconCollectMain;
    private TextView mCollectTvMy;
    private ImageView mIconDownloadMain;
    private TextView mDownloadTvMy;
    private TextView mFromTvOrder;
    private ImageView mIconOrder2Main;
    private TextView mOrderTvMy;
    private TextView mAddressTvShipping;
    private TextView mAccountTvMy;
    private ImageView mIconCouponMain;
    private TextView mTvCoupon;
    private ImageView mIconCardMain;
    private TextView mCardTvStudy;
    private ImageView mIconVipMain;
    private TextView mCardTvVip;
    private TextView mServiceTvAutomatic;
    private ImageView mIconNoticeMain;
    private TextView mNewsTvMy;
    private ImageView mIconFeedBackMain;
    private TextView mBackTvFeed;
    private ImageView mIconServiceMain;
    private TextView mLineTvService;
    private ImageView mIconSettingsMain;
    private TextView mTvSettings;

    public PersonageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_personage, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mUserInfoBg = (ImageView) itemView.findViewById(R.id.bg_user_info);
        mClassSizaMy = (TextView) itemView.findViewById(R.id.my_class_siza);
        mClassNameMy = (TextView) itemView.findViewById(R.id.my_class_name);
        mClassDetailsMy = (TextView) itemView.findViewById(R.id.my_class_details);
        mClassColMy = (ConstraintLayout) itemView.findViewById(R.id.my_class_col);
        mOrderSizaMy = (TextView) itemView.findViewById(R.id.my_order_siza);
        mOrderNameMy = (TextView) itemView.findViewById(R.id.my_order_name);
        mOrderDetailsMy = (TextView) itemView.findViewById(R.id.my_order_details);
        mOrderColMy = (ConstraintLayout) itemView.findViewById(R.id.my_order_col);
        mStudyGoldSizaMy = (TextView) itemView.findViewById(R.id.my_study_gold_siza);
        mStudyGoldNameMy = (TextView) itemView.findViewById(R.id.my_study_gold_name);
        mStudyGoldDetailsMy = (TextView) itemView.findViewById(R.id.my_study_gold_details);
        mStudyGoldColMy = (ConstraintLayout) itemView.findViewById(R.id.my_study_gold_col);
        mIcUnloginHeadMain = (ImageView) itemView.findViewById(R.id.main_ic_unlogin_head);
        mIconUserVipMain = (ImageView) itemView.findViewById(R.id.main_icon_user_vip);
        mTvLogin = (TextView) itemView.findViewById(R.id.login_tv);
        mIconEditMain = (ImageView) itemView.findViewById(R.id.main_icon_edit);
        mIconInventeMain = (ImageView) itemView.findViewById(R.id.main_icon_invente);
        mTvDiscounts = (TextView) itemView.findViewById(R.id.discounts_tv);
        mArrowRightCommon = (ImageView) itemView.findViewById(R.id.common_arrow_right);
        mTvCourse = (TextView) itemView.findViewById(R.id.course_tv);
        mIconOrderMain = (ImageView) itemView.findViewById(R.id.main_icon_order);
        mWorkTvMe = (TextView) itemView.findViewById(R.id.me_work_tv);
        mIconConcernMain = (ImageView) itemView.findViewById(R.id.main_icon_concern);
        mTeacherTvAttention = (TextView) itemView.findViewById(R.id.attention_teacher_tv);
        mIconCollectMain = (ImageView) itemView.findViewById(R.id.main_icon_collect);
        mCollectTvMy = (TextView) itemView.findViewById(R.id.my_collect_tv);
        mIconDownloadMain = (ImageView) itemView.findViewById(R.id.main_icon_download);
        mDownloadTvMy = (TextView) itemView.findViewById(R.id.my_download_tv);
        mFromTvOrder = (TextView) itemView.findViewById(R.id.order_from_tv);
        mIconOrder2Main = (ImageView) itemView.findViewById(R.id.main_icon_order2);
        mOrderTvMy = (TextView) itemView.findViewById(R.id.my_order_tv);
        mAddressTvShipping = (TextView) itemView.findViewById(R.id.shipping_address_tv);
        mAccountTvMy = (TextView) itemView.findViewById(R.id.my_account_tv);
        mIconCouponMain = (ImageView) itemView.findViewById(R.id.main_icon_coupon);
        mTvCoupon = (TextView) itemView.findViewById(R.id.coupon_tv);
        mIconCardMain = (ImageView) itemView.findViewById(R.id.main_icon_card);
        mCardTvStudy = (TextView) itemView.findViewById(R.id.study_card_tv);
        mIconVipMain = (ImageView) itemView.findViewById(R.id.main_icon_vip);
        mCardTvVip = (TextView) itemView.findViewById(R.id.vip_card_tv);
        mServiceTvAutomatic = (TextView) itemView.findViewById(R.id.automatic_service_tv);
        mIconNoticeMain = (ImageView) itemView.findViewById(R.id.main_icon_notice);
        mNewsTvMy = (TextView) itemView.findViewById(R.id.my_news_tv);
        mIconFeedBackMain = (ImageView) itemView.findViewById(R.id.main_icon_feed_back);
        mBackTvFeed = (TextView) itemView.findViewById(R.id.feed_back_tv);
        mIconServiceMain = (ImageView) itemView.findViewById(R.id.main_icon_service);
        mLineTvService = (TextView) itemView.findViewById(R.id.service_line_tv);
        mIconSettingsMain = (ImageView) itemView.findViewById(R.id.main_icon_settings);
        mTvSettings = (TextView) itemView.findViewById(R.id.settings_tv);
        initlistener();
    }

    private void initlistener() {
        mTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhonePaw_Login.class);
                startActivity(intent);
            }
        });
    }

}
