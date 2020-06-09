package com.example.recycleradapterhelper.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.recycleradapterhelper.R;

import java.util.List;

public class MyBaseQuiclAdapyer extends BaseQuickAdapter<String, BaseViewHolder> {
    public MyBaseQuiclAdapyer(@Nullable List<String> data) {
        super(R.layout.activity_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_tv, item);
    }
}
