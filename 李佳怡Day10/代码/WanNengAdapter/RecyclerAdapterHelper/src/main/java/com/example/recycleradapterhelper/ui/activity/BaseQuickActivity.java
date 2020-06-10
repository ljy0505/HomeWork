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
import com.example.recycleradapterhelper.adapter.MyBaseNetImgAdapter;
import com.example.recycleradapterhelper.adapter.MyBaseNetQuiclAdapyer;
import com.example.recycleradapterhelper.adapter.MyBaseQuiclAdapyer;
import com.example.recycleradapterhelper.api.Constants;
import com.example.recycleradapterhelper.entity.ArticalInfo1;
import com.example.recycleradapterhelper.entity.GongZhongHaoInfo;
import com.example.recycleradapterhelper.util.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BaseQuickActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private int currentCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basequickadapter);
        //加载本地字符串数据
        //initStringData();

        //加载网络字符串数据
        //initNetData();
        //加载带图片和字符串的网络数据
        initNetImgData();
    }

    private void initNetImgData() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(BaseQuickActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(BaseQuickActivity.this, LinearLayoutManager.VERTICAL));

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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final MyBaseNetImgAdapter adapter = new MyBaseNetImgAdapter(BaseQuickActivity.this, datas);
                        mRecyclerView.setAdapter(adapter);
                       /* adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Log.e("tag",position+"===点击了==");

                            }
                        });
                        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                                Log.e("tag",position+"===被长按了==");
                                return false;
                            }
                        });*/

                        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                Log.e("TAG", "图片" + position + "被点击了");
                                ArticalInfo1.DataBean.DatasBean datasBean = datas.get(position);
                                if (view.getId() == R.id.item_tv) {
                                    //TODO 如何获取点击的文本字符串
                                    String desc = datasBean.getDesc();
                                    Log.e("TAG", "点击的文本字符串是：" + desc);
                                } else {
                                    //TODO 如何获取点击的图片的地址
                                    String envelopePic = datasBean.getEnvelopePic();
                                    Log.e("TAG", "点击的图片的地址是：" + envelopePic);
                                }

                            }
                        });

                        //TODO 设置图片和文本的长按事件  和上面的短按事件一样
                        adapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
                            @Override
                            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                                ArticalInfo1.DataBean.DatasBean datasBean = datas.get(position);
                                if (view.getId() == R.id.item_tv) {
                                    //TODO 如何获取点击的文本字符串
                                    String desc = datasBean.getDesc();
                                    Log.e("TAG", "长按的文本字符串是：" + desc);
                                } else {
                                    //TODO 如何获取点击的图片的地址
                                    String envelopePic = datasBean.getEnvelopePic();
                                    Log.e("TAG", "长按的图片的地址是：" + envelopePic);
                                }
                                return false;
                            }
                        });
                        //TODO 给条目添加动画  默认的动画是渐变动画
                        //adapter.openLoadAnimation();
                        //adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                        // adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
                        // adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
                    /*    adapter.openLoadAnimation(new BaseAnimation() {
                            @Override
                            public Animator[] getAnimators(View view) {
                                return new Animator[]{
                                        ObjectAnimator.ofFloat(view, "translationX", 0, 200),
                                        ObjectAnimator.ofFloat(view, "translationY", 0, 200),
                                };
                            }
                        });*/
                        //设置重复执行动画
                        adapter.isFirstOnly(false);


                        View header_view = View.inflate(BaseQuickActivity.this, R.layout.item_header, null);
                        adapter.addHeaderView(header_view);

                        View footer_view = View.inflate(BaseQuickActivity.this, R.layout.item_footer, null);
                        adapter.addFooterView(footer_view);


             /*           //移除头部view
                        adapter.removeHeaderView(header_view);
                        //移除脚部view
                        adapter.removeFooterView(footer_view);
                        adapter.removeAllHeaderView();
                        adapter.removeAllFooterView();

              */

               /*   TODO 加载更多      adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                currentCount = adapter.getData().size();
                                Log.e("1212122", "加载更多........"+currentCount+"======");

                            }
                        });
*/

                    }

                });

            }
        });

    }

    private void initNetData() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(BaseQuickActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(BaseQuickActivity.this, LinearLayoutManager.VERTICAL));

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS).
                readTimeout(10, TimeUnit.SECONDS);
        Request.Builder request = new Request.Builder().url(Constants.GONGZHONGHAO_URL);
        Call call = builder.build().newCall(request.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "获取数据失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonStr = response.body().string();
                GongZhongHaoInfo gongZhongHaoInfo = GsonUtil.str2Bean(jsonStr, GongZhongHaoInfo.class);
                final List<GongZhongHaoInfo.DataBean> list = gongZhongHaoInfo.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyBaseNetQuiclAdapyer adapter = new MyBaseNetQuiclAdapyer(list);
                        mRecyclerView.setAdapter(adapter);
                    }
                });

            }
        });


    }

    private void initStringData() {
        List<String> list = new ArrayList<String>();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(BaseQuickActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(BaseQuickActivity.this, LinearLayoutManager.VERTICAL));
        for (int i = 0; i < 100; i++) {
            list.add("数据" + i);
        }
        MyBaseQuiclAdapyer adapter = new MyBaseQuiclAdapyer(list);
        mRecyclerView.setAdapter(adapter);
    }
}
