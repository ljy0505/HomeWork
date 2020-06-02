package com.example.lianpro1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.lianpro1.adapter.ViewPagerAdapter;
import com.example.lianpro1.fragment.OneFragment;
import com.example.lianpro1.fragment.ThreeFragment;
import com.example.lianpro1.fragment.TwoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private DrawerLayout mDl;
    private Toolbar mTl;
    private List<Fragment> list;
    private List<String> list1;
    private TabLayout mTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mDl = (DrawerLayout) findViewById(R.id.dl);
        mTl = (Toolbar) findViewById(R.id.tl);
        mTb = (TabLayout) findViewById(R.id.tb);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDl, mTl, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        mTl.setNavigationIcon(R.mipmap.by);
        mTl.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDl.openDrawer(GravityCompat.START);
            }
        });

        //Fragment
        list = new ArrayList();
        list.add(new OneFragment());
        list.add(new TwoFragment());
        list.add(new ThreeFragment());
        //标题集合
        list1 = new ArrayList<>();
        list1.add("消息");
        list1.add("联系人");
        list1.add("动态");

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list, list1);
        mVp.setAdapter(viewPagerAdapter);
        mTb.setupWithViewPager(mVp);
        mTb.getTabAt(0).setIcon(R.mipmap.by);
        mTb.getTabAt(1).setIcon(R.mipmap.by);
        mTb.getTabAt(2).setIcon(R.mipmap.by);
        mTb.setSelectedTabIndicatorHeight(0);
    }
}
