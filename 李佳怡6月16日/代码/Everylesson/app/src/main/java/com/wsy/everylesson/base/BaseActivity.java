package com.wsy.everylesson.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wsy.everylesson.util.ToastUtil;

import butterknife.ButterKnife;


//模板方法模式：父类定义代码的执行流程，把一些无法决定的东西放到这里完成
//相同的代码抽取到父类中
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
public P mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        //绑定ButterKnife
        ButterKnife.bind(this);
        mPresenter=initPresenter();
        if (mPresenter!=null){
            mPresenter.attachView(this);
        }
        initView();
        initDate();
    }

    protected abstract P initPresenter();

    protected abstract void initView();

    protected abstract void initDate();


    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消v和p关联
        //打断网络请求+订阅关系
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToastShort(msg);
    }
}
