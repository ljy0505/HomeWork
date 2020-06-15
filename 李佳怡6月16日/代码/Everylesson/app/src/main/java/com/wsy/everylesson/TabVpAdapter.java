package com.wsy.everylesson;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class TabVpAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragList;
    private Context context;

    public TabVpAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragList, Context context) {
        super(fm);
        this.fragList = fragList;
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }
}
