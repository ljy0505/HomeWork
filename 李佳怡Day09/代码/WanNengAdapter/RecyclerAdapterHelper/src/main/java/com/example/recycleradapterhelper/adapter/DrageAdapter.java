package com.example.recycleradapterhelper.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.recycleradapterhelper.R;

import java.util.List;

public class DrageAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {

    public DrageAdapter(List<String> data) {
        super(R.layout.item_chengyuan, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_chengyuan_tv, item);
    }
}
