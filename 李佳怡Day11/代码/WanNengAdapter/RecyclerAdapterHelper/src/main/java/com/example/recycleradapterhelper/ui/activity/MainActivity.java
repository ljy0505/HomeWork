package com.example.recycleradapterhelper.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        mRecyclerView = findViewById(R.id.recyclerView);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            list.add("数据" + i);
        }
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, list);
        mRecyclerView.setAdapter(adapter);

    }
}
