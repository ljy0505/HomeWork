package com.example.recycleradapterhelper.ui.activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.adapter.DrageAdapter;

import java.util.ArrayList;
import java.util.List;

public class DragActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        init();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(DragActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(DragActivity.this, LinearLayoutManager.VERTICAL));
        List<String> datas = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            datas.add("条目" + i);
        }
        DrageAdapter adapter = new DrageAdapter(datas);
        mRecyclerView.setAdapter(adapter);


        //万能适配器里面的实现类
        ItemDragAndSwipeCallback callBack = new ItemDragAndSwipeCallback(adapter);
        //RecyclerView自带的条目触摸帮助类
        ItemTouchHelper helper = new ItemTouchHelper(callBack);
        //和RecyclerView关联
        helper.attachToRecyclerView(mRecyclerView);


        //开启拖拽
        adapter.enableDragItem(helper);
        //条目拖拽监听
        adapter.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i) {
                Log.e("GASGA", "条目" + i + "开始拖拽");
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder1, int i1) {
                Log.e("GASGA", "条目" + i + "正在拖拽");
            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i) {
                Log.e("GASGA", "条目" + i + "停止拖拽");
            }
        });


        //开启侧滑删除
        adapter.enableSwipeItem();
        adapter.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int i) {
                Log.e("TAGGA", "条目" + i + "开始删除.......");
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int i) {
                Log.e("TAGGA", "被清除了");
            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int i) {
                Log.e("TAGGA", "条目" + i + "被删除了.......");
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float v, float v1, boolean b) {
                Log.e("TAGGA", "正在被删除.......");
            }
        });

    }
}
