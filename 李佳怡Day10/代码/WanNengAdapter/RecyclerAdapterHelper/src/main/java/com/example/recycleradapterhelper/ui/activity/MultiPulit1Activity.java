package com.example.recycleradapterhelper.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.adapter.MultiQuick1Adapter;
import com.example.recycleradapterhelper.api.Constants;
import com.example.recycleradapterhelper.entity.ArticalInfo1;
import com.example.recycleradapterhelper.util.GsonUtil;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MultiPulit1Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        init();

    }

    private void init() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MultiPulit1Activity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(MultiPulit1Activity.this, LinearLayoutManager.VERTICAL));

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS).
                readTimeout(10, TimeUnit.SECONDS);
        Request.Builder request = new Request.Builder().url(Constants.ARTICAL_URL);
        Call call = builder.build().newCall(request.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "获取数据失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonStr = response.body().string();
                ArticalInfo1 articalInfo = GsonUtil.str2Bean(jsonStr, ArticalInfo1.class);
                final ArticalInfo1.DataBean data = articalInfo.getData();
                final List<ArticalInfo1.DataBean.DatasBean> datas = data.getDatas();

                for (int i = 0; i < datas.size(); i++) {
                    ArticalInfo1.DataBean.DatasBean datasBean = datas.get(i);
                    if (i == 0) {
                        datasBean.setItemtType(ArticalInfo1.DataBean.DatasBean.MULTI_1);
                    } else if (i % 2 == 0) {
                        datasBean.setItemtType(ArticalInfo1.DataBean.DatasBean.MULTI_2);
                    } else {
                        datasBean.setItemtType(ArticalInfo1.DataBean.DatasBean.MULTI_3);
                    }

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MultiQuick1Adapter adapter = new MultiQuick1Adapter(MultiPulit1Activity.this, datas);
                        mRecyclerView.setAdapter(adapter);
                    }
                });
            }
        });


    }
}
