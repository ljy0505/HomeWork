package com.example.recycleradapterhelper.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.adapter.Group1Adapter;
import com.example.recycleradapterhelper.entity.FenZuInfo1;

import java.util.ArrayList;
import java.util.List;

public class FenZu1Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenzu);
        init();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(FenZu1Activity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(FenZu1Activity.this, LinearLayoutManager.VERTICAL));

        List<FenZuInfo1> list = new ArrayList<FenZuInfo1>();
        for (int i = 0; i < 5; i++) {
            //添加组对象
            list.add(new FenZuInfo1(true, (i + 1) + "组"));
            for (int x = 0; x < 20; x++) {
                //添加成员对象
                list.add(new FenZuInfo1("第" + (x + 1) + "成员"));
            }
        }

        Group1Adapter adapter = new Group1Adapter(list);
        mRecyclerView.setAdapter(adapter);
    }
}
