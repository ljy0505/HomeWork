package com.example.recycleradapterhelper.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.entity.ArticalInfo1;
import com.example.recycleradapterhelper.entity.ArticalInfo2;

import java.util.List;

/**
 * 第二种多布局的适配器
 */
public class MultiQuick2Adapter extends BaseQuickAdapter<ArticalInfo2.DataBean.DatasBean, BaseViewHolder> {
    private Context mContext;

    public MultiQuick2Adapter(Context context, List<ArticalInfo2.DataBean.DatasBean> data) {
        super(data);
        //设置和type变量类型关联
        setMultiTypeDelegate(new MultiTypeDelegate<ArticalInfo2.DataBean.DatasBean>() {
            @Override
            protected int getItemType(ArticalInfo2.DataBean.DatasBean datasBean) {
                return datasBean.getType();
            }
        });

        //将不同的type类型和不同的布局关联
        getMultiTypeDelegate().registerItemType(ArticalInfo2.DataBean.DatasBean.MULTI_1, R.layout.multi_item1);
        getMultiTypeDelegate().registerItemType(ArticalInfo2.DataBean.DatasBean.MULTI_2, R.layout.multi_item2);
        getMultiTypeDelegate().registerItemType(ArticalInfo2.DataBean.DatasBean.MULTI_3, R.layout.multi_item3);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticalInfo2.DataBean.DatasBean datasBean) {
        switch (datasBean.getType()) {
            //只有一张图片
            case ArticalInfo2.DataBean.DatasBean
                    .MULTI_1:
                Glide.with(mContext).load(datasBean.getEnvelopePic()).
                        into((ImageView) helper.getView(R.id.multi1_iv));
                break;

            //左边文字，右边图片
            case ArticalInfo2.DataBean.DatasBean.MULTI_2:
                helper.setText(R.id.multi2_tv, datasBean.getDesc());
                Glide.with(mContext).load(datasBean.getEnvelopePic()).
                        into((ImageView) helper.getView(R.id.multi2_iv));

                break;

            //左边图片右边文字
            case ArticalInfo2.DataBean.DatasBean
                    .MULTI_3:
                helper.setText(R.id.multi3_tv, datasBean.getDesc());
                Glide.with(mContext).load(datasBean.getEnvelopePic()).
                        into((ImageView) helper.getView(R.id.multi3_iv));
                break;

        }

    }
}
