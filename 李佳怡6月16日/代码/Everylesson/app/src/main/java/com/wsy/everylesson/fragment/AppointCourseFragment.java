package com.wsy.everylesson.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.wsy.everylesson.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppointCourseFragment extends Fragment {


    private TextView mListClass;
    private ImageView mBackIconCommon;
    private TextView mTvClassify;
    private TextView mTvSort;
    private TextView mTvScreen;
    private TabLayout mTabCourse;
    private ArrayList<Fragment> fragments;
    private AppointListFragment appointListFragment;
    private FragmentManager supportFragmentManager;
    private FrameLayout mFragCourse;

    public AppointCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_appointcourse, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
       /* mListClass = (TextView) itemView.findViewById(R.id.class_list);
        mBackIconCommon = (ImageView) itemView.findViewById(R.id.common_back_icon);
        mTvClassify = (TextView) itemView.findViewById(R.id.classify_tv);
        mTvSort = (TextView) itemView.findViewById(R.id.sort_tv);
        mTvScreen = (TextView) itemView.findViewById(R.id.screen_tv);*/
        mTabCourse = (TabLayout) itemView.findViewById(R.id.course_tab);
        mFragCourse = itemView.findViewById(R.id.course_frag);

        /*fragments = new ArrayList<>();
        appointListFragment = new AppointListFragment();
        fragments.add(appointListFragment);
        AppointListAdapter adapter = new AppointListAdapter(getActivity().getSupportFragmentManager(), fragments);
        mVpCourse.setAdapter(adapter);
        mTabCourse.setupWithViewPager(mVpCourse);
        mTabCourse.getTabAt(0).setText("待上课");
        mTabCourse.getTabAt(1).setText("已上课");
        mTabCourse.getTabAt(2).setText("已取消");*/
        mTabCourse.addTab(mTabCourse.newTab().setText("待上课"));
        /*mTabCourse.getTabAt(0).setText("待上课");
        mTabCourse.getTabAt(1).setText("已上课");
        mTabCourse.getTabAt(2).setText("已取消");*/
        mTabCourse.addTab(mTabCourse.newTab().setText("已上课"));
        mTabCourse.addTab(mTabCourse.newTab().setText("已取消"));
        appointListFragment = new AppointListFragment();
        supportFragmentManager = getActivity().getSupportFragmentManager();
        supportFragmentManager.beginTransaction().add(R.id.course_frag, appointListFragment).commit();
        mTabCourse.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    supportFragmentManager.beginTransaction().show( appointListFragment).commit();

                }else if (tab.getPosition()==1){
                    supportFragmentManager.beginTransaction().show( appointListFragment).commit();
                }else {
                    supportFragmentManager.beginTransaction().show( appointListFragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        /*mTabCourse.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    supportFragmentManager.beginTransaction().show(allFragment).hide(videoFragment).commit();
                    setType(0);

                }else {
                    supportFragmentManager.beginTransaction().show(allFragment).hide(videoFragment).commit();
                    setType(1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

    }

}
