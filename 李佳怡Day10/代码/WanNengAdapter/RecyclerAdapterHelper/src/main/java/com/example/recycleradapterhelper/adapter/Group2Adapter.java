package com.example.recycleradapterhelper.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.entity.ChengYuanBean;
import com.example.recycleradapterhelper.entity.FenZuInfo1;
import com.example.recycleradapterhelper.entity.FenZuInfo2;

import java.util.List;

public class Group2Adapter extends BaseSectionQuickAdapter<FenZuInfo2, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     */
    public Group2Adapter(List<FenZuInfo2> list) {
        super(R.layout.item_chengyuan, R.layout.item_group, list);

    }

    //设置成员
    @Override
    protected void convert(BaseViewHolder helper, FenZuInfo2 item) {
        ChengYuanBean bean = item.t;
        helper.setText(R.id.item_chengyuan_tv, bean.getmName());
    }

    //设置组
    @Override
    protected void convertHead(final BaseViewHolder helper, FenZuInfo2 item) {
        helper.setText(R.id.item_zu_tv, item.header);

    }
}
