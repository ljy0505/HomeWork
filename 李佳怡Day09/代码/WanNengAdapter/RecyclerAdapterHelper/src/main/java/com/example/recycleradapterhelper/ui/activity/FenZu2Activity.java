package com.example.recycleradapterhelper.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.adapter.Group1Adapter;
import com.example.recycleradapterhelper.adapter.Group2Adapter;
import com.example.recycleradapterhelper.entity.ChengYuanBean;
import com.example.recycleradapterhelper.entity.FenZuInfo1;
import com.example.recycleradapterhelper.entity.FenZuInfo2;

import java.util.ArrayList;
import java.util.List;

public class FenZu2Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenzu);
        init();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(FenZu2Activity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(FenZu2Activity.this, LinearLayoutManager.VERTICAL));
        List<FenZuInfo2> list = new ArrayList<FenZuInfo2>();
        for (int i = 0; i < 5; i++) {
            list.add(new FenZuInfo2(true, (i + 1) + "组"));
            for (int y = 0; y < 6; y++) {
                list.add(new FenZuInfo2(new ChengYuanBean("第" + (y + 1) + "个成员")));
            }
        }
        Group2Adapter adapter = new Group2Adapter(list);
        //组和成员的所有条目的点击事件
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("1212qqw", "条目" + position + "被点击了");
            }
        });
        mRecyclerView.setAdapter(adapter);
    }
}
