package com.wsy.everylesson.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {
    private ArrayList<BaseModel> mBaseModels;
    public BasePresenter() {
        initModel();
    }
//专门用来初始化M层的方法
    protected abstract void initModel();

    public V mView;

    //V和P层的关联
    public  void attachView(V view){
        this.mView = view;
    }
    //取消关联
    public  void detachView(){
        mView = null;////取消vhep关联
        if (mBaseModels!=null){
            for (int i = 0; i < mBaseModels.size(); i++) {
                //通知m层取消网络请求+打断订阅关系
                mBaseModels.get(i).destroy();
            }
        }
    }

    public void addModel(BaseModel model){
        if (mBaseModels==null){
            mBaseModels=new ArrayList<>();
        }
        mBaseModels.add(model);
    }


}
