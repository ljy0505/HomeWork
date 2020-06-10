package com.example.recycleradapterhelper.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.entity.FenZuInfo1;

import java.util.List;

public class Group1Adapter extends BaseSectionQuickAdapter<FenZuInfo1, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     */
    public Group1Adapter(List<FenZuInfo1> list) {
        super(R.layout.item_chengyuan, R.layout.item_group, list);

    }

    //设置成员
    @Override
    protected void convert(BaseViewHolder helper, FenZuInfo1 item) {
        helper.setText(R.id.item_chengyuan_tv, item.t);
    }

    //设置组
    @Override
    protected void convertHead(BaseViewHolder helper, FenZuInfo1 item) {
        helper.setText(R.id.item_zu_tv, item.header);
    }
}
