package com.example.everyclass;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.everyclass.fragment.ClassFragment;
import com.example.everyclass.fragment.PersonFragment;
import com.example.everyclass.fragment.ShouYeFragment;
import com.example.everyclass.fragment.YueKeClassFragment;

public class PageActivity extends AppCompatActivity implements RadioButton.OnCheckedChangeListener{

    private FrameLayout mFrame;
    /**
     * 首页
     */
    private RadioButton mMainShouye;
    /**
     * 课程
     */
    private RadioButton mMainClass;
    /**
     * 约课记录
     */
    private RadioButton mMainYueClass;
    /**
     * 个人
     */
    private RadioButton mMainPerson;
    private RadioGroup mRadioGroup;
    private FragmentManager fragmentManager;
    private ShouYeFragment shouYeFragment;
    private ClassFragment classFragment;
    private YueKeClassFragment tueKeClassFragment;
    private PersonFragment personFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        initView();
    }


    private void initView() {
        mFrame = (FrameLayout) findViewById(R.id.frame);
        mMainShouye = (RadioButton) findViewById(R.id.main_shouye);
        mMainClass = (RadioButton) findViewById(R.id.main_class);
        mMainYueClass = (RadioButton) findViewById(R.id.main_yue_class);
        mMainPerson = (RadioButton) findViewById(R.id.main_person);
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mMainShouye.setOnCheckedChangeListener(this);
        mMainClass.setOnCheckedChangeListener(this);
        mMainYueClass.setOnCheckedChangeListener(this);
        mMainPerson.setOnCheckedChangeListener(this);
        fragmentManager = getSupportFragmentManager();
        initFragments();
    }

    private void initFragments() {
        shouYeFragment = new ShouYeFragment();
        classFragment = new ClassFragment();
        tueKeClassFragment = new YueKeClassFragment();
        personFragment = new PersonFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, shouYeFragment).add(R.id.frame, classFragment)
                .add(R.id.frame, tueKeClassFragment)
                .add(R.id.frame, personFragment)
                .hide(classFragment)
                .hide(tueKeClassFragment)
                .hide(personFragment);
        fragmentTransaction.commit();
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (buttonView.getId()) {

                case R.id.main_shouye:
                    fragmentTransaction.show(shouYeFragment)
                            .hide(classFragment)
                            .hide(tueKeClassFragment)
                            .hide(personFragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.main_class:
                    fragmentTransaction.show(classFragment)
                            .hide(personFragment)
                            .hide(tueKeClassFragment)
                            .hide(shouYeFragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.main_yue_class:
                    fragmentTransaction.show(tueKeClassFragment)
                            .hide(classFragment)
                            .hide(personFragment)
                            .hide(shouYeFragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.main_person:
                    fragmentTransaction.show(personFragment)
                            .hide(classFragment)
                            .hide(tueKeClassFragment)
                            .hide(shouYeFragment);
                    fragmentTransaction.commit();
                    break;
            }
        }
    }
}
