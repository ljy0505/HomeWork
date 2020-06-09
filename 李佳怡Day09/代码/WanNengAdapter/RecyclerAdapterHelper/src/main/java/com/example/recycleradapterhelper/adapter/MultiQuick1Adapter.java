package com.example.recycleradapterhelper.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.entity.ArticalInfo1;

import java.util.List;

public class MultiQuick1Adapter extends BaseMultiItemQuickAdapter<ArticalInfo1.DataBean.DatasBean, BaseViewHolder> {
    private Context mContext;

    public MultiQuick1Adapter(Context context, List<ArticalInfo1.DataBean.DatasBean> data) {
        super(data);
        addItemType(ArticalInfo1.DataBean.DatasBean.MULTI_1, R.layout.multi_item1);
        addItemType(ArticalInfo1.DataBean.DatasBean.MULTI_2, R.layout.multi_item2);
        addItemType(ArticalInfo1.DataBean.DatasBean.MULTI_3, R.layout.multi_item3);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticalInfo1.DataBean.DatasBean datasBean) {
        switch (datasBean.getItemType()) {
            //只有一张图片
            case ArticalInfo1.DataBean.DatasBean
                    .MULTI_1:
                Glide.with(mContext).load(datasBean.getEnvelopePic()).
                        into((ImageView) helper.getView(R.id.multi1_iv));
                break;

            //左边文字，右边图片
            case ArticalInfo1.DataBean.DatasBean.MULTI_2:
                helper.setText(R.id.multi2_tv, datasBean.getDesc());
                Glide.with(mContext).load(datasBean.getEnvelopePic()).
                        into((ImageView) helper.getView(R.id.multi2_iv));

                break;

            //左边图片右边文字
            case ArticalInfo1.DataBean.DatasBean
                    .MULTI_3:
                helper.setText(R.id.multi3_tv, datasBean.getDesc());
                Glide.with(mContext).load(datasBean.getEnvelopePic()).
                        into((ImageView) helper.getView(R.id.multi3_iv));
                break;

        }

    }
}
