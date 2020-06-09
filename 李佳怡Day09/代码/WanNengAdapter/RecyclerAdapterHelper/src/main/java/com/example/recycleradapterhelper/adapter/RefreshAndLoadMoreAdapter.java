package com.example.recycleradapterhelper.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.entity.DataInfo;

import java.util.List;

public class RefreshAndLoadMoreAdapter extends BaseQuickAdapter<DataInfo.DataBean.DatasBean, BaseViewHolder> {
    private Context mContext;

    public RefreshAndLoadMoreAdapter(Context context, @Nullable List<DataInfo.DataBean.DatasBean> data) {
        super(R.layout.item_refresh, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataInfo.DataBean.DatasBean item) {
        Glide.with(mContext).load(item.getEnvelopePic()).
                into((ImageView) helper.getView(R.id.item_refresh_iv));
         helper.setText(R.id.item_refresh_tv,item.getDesc());
    }
}
