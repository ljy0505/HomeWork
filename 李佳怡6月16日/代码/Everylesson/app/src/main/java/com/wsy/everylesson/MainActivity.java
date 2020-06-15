package com.wsy.everylesson;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wsy.everylesson.fragment.AppointCourseFragment;
import com.wsy.everylesson.fragment.CourseFragment;
import com.wsy.everylesson.fragment.HomeFragment;
import com.wsy.everylesson.fragment.PersonageFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ViewPager mVpMain;
    private TabLayout mTabMain;
    private ArrayList<Fragment> fragList;
    private TabVpAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mVpMain = (ViewPager) findViewById(R.id.main_vp);
        mTabMain = (TabLayout) findViewById(R.id.main_tab);
        initTab();
    }

    private void initTab() {
        fragList = new ArrayList<>();
        fragList.add(new HomeFragment());
        fragList.add(new CourseFragment());
        fragList.add(new AppointCourseFragment());
        fragList.add(new PersonageFragment());
        adapter = new TabVpAdapter(getSupportFragmentManager(), fragList, this);
        mVpMain.setAdapter(adapter);
        mTabMain.setupWithViewPager(mVpMain);
        mTabMain.getTabAt(0).setText("首页").setIcon(R.drawable.home_selector);
        mTabMain.getTabAt(1).setText("课程").setIcon(R.drawable.class_selector);
        mTabMain.getTabAt(2).setText("约课记录").setIcon(R.drawable.invite_class_selector);
        mTabMain.getTabAt(3).setText("个人").setIcon(R.drawable.personage_selector);


    }


}
