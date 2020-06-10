package com.example.recycleradapterhelper.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.entity.ArticalInfo1;

import java.util.List;

public class MyBaseNetImgAdapter extends BaseQuickAdapter<ArticalInfo1.DataBean.DatasBean, BaseViewHolder> {
    private Context mContext;

    public MyBaseNetImgAdapter(Context context, @Nullable List<ArticalInfo1.DataBean.DatasBean> data) {
        super(R.layout.list_item_img, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticalInfo1.DataBean.DatasBean item) {
        helper.setText(R.id.item_tv, item.getDesc());
        String envelopePic = item.getEnvelopePic();
        //设置条目中图片点击事件
        helper.addOnClickListener(R.id.item_iv);
        //设置条目中textView的点击事件
        helper.addOnClickListener(R.id.item_tv);

        //设置条目文本的长按事件
        helper.addOnLongClickListener(R.id.item_tv);

        //设置条目图片的长按事件
        helper.addOnLongClickListener(R.id.item_iv);

        //加载网络图片
        Glide.with(mContext).load(envelopePic).into((ImageView) helper.getView(R.id.item_iv));

     /*   int layoutPosition = helper.getLayoutPosition();
        Log.e("TAG", layoutPosition + "===");*/


    }
}
