package com.example.recycleradapterhelper.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.recycleradapterhelper.R;
import com.example.recycleradapterhelper.adapter.RefreshAndLoadMoreAdapter;
import com.example.recycleradapterhelper.api.Constants;
import com.example.recycleradapterhelper.callback.CallBack;
import com.example.recycleradapterhelper.engine.DataServer;
import com.example.recycleradapterhelper.entity.DataInfo;
import com.example.recycleradapterhelper.ui.widget.CustomLoadMoreView;

import java.util.List;

public class RefreshAndroidLoadMoreActivity<T> extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    //当前页码
    private int mPage = 0;
    private int mCurrentCounter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        init();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RefreshAndroidLoadMoreActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(RefreshAndroidLoadMoreActivity.this, LinearLayoutManager.VERTICAL));

        //第一页的数据
        DataServer.getData("https://wanandroid.com/article/listproject/" + mPage + "/json",
                new CallBack<Object>() {
                    @Override
                    public <T> void onScuess(T t) {
                        if (t instanceof DataInfo) {
                            DataInfo dataInfo = (DataInfo) t;
                            final List<DataInfo.DataBean.DatasBean> datas = dataInfo.getData().getDatas();
                            //主线程运行的代码块
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    final RefreshAndLoadMoreAdapter adapter =
                                            new RefreshAndLoadMoreAdapter(RefreshAndroidLoadMoreActivity.this, datas);
                                    mRecyclerView.setAdapter(adapter);

                                    //自定义加载进度条(正在加载的进度条，加载成功的进度条，加载失败的进度条)
                                    adapter.setLoadMoreView(new CustomLoadMoreView());
                                    //加载更多  滑动到底部 加载第二页....第三页......第n页......
                                    adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                                        @Override
                                        public void onLoadMoreRequested() {
                                            //如果累计的条目数 >= 总条目数
                                            if (mCurrentCounter >= adapter.getItemCount()) {
                                                //说明没有数据了的标识
                                                adapter.loadMoreEnd();
                                            } else {
                                                //有数据 继续加载  获取完数据之后往适配器里面继续添加
                                                mPage++;
                                                DataServer.getData("https://wanandroid.com/article/listproject/" + mPage + "/json", new CallBack<Object>() {
                                                    @Override
                                                    public <T> void onScuess(T t) {
                                                        if (t instanceof DataInfo) {
                                                            final DataInfo dataInfo = (DataInfo) t;
                                                            final List<DataInfo.DataBean.DatasBean> datas = dataInfo.getData().getDatas();
                                                            //主线程更新UI的代码块
                                                            mRecyclerView.post(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    //将第二页，第三页......第n页的数据都累加到适配器里面
                                                                    adapter.addData(datas);
                                                                    //累加的条目个数
                                                                    mCurrentCounter = dataInfo.getData().getSize();

                                                                    //第二页....第三页.....第n页加载成功的标识.....
                                                                    adapter.loadMoreComplete();
                                                                }
                                                            });
                                                        }
                                                    }

                                                    @Override
                                                    public void onFile(String error) {
                                                        Log.e("TAG", "数据加载失败...." + error);
                                                        mRecyclerView.post(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                //第二页....第三页.....第n页加载失败的标识.....
                                                                adapter.loadMoreFail();
                                                            }
                                                        });

                                                    }
                                                });

                                            }


                                        }
                                    }, mRecyclerView);

                                    //false 关闭上拉加载，true代表打开上拉加载
                                    adapter.setEnableLoadMore(false);

                                    //开启下拉刷新
                                    adapter.setUpFetchEnable(true);
                                    //设置开始加载的位置为第0个条目
                                    adapter.setStartUpFetchPosition(0);
                                    adapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
                                        @Override
                                        public void onUpFetch() {
                                            mRecyclerView.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    //TODO 明天说
                                                    // startUpFetch(adapter);
                                                }
                                            }, 200);
                                        }
                                    });


                                }
                            });
                        }

                    }

                    @Override
                    public void onFile(String error) {
                        Log.e("TAG", "数据加载失败...." + error);
                    }
                });


    }

    //开始下拉刷新（加载下一页数据）
    private void startUpFetch(final RefreshAndLoadMoreAdapter adapter) {
        mPage++;
        Log.e("assasas", "刷新完成了。。-----。。。。");

        adapter.setUpFetchEnable(true);
        //开始下拉刷新的标识
        adapter.setUpFetching(true);

        DataServer.getData("https://wanandroid.com/article/listproject/" + mPage + "/json", new CallBack<Object>() {
            @Override
            public <T> void onScuess(T t) {
                Log.e("assasas", "刷新完成了。。--1111---。。。。");
                if (t instanceof DataInfo) {
                    final DataInfo dataInfo = (DataInfo) t;
                    final List<DataInfo.DataBean.DatasBean> datas = dataInfo.getData().getDatas();
                    mRecyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("assasas", "刷新完成了。。。2222。。。");
                            adapter.addData(0, datas);
                            adapter.setUpFetching(false);
                            adapter.setUpFetchEnable(false);
                        }
                    }, 300);

                }
            }

            @Override
            public void onFile(String error) {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("assasas", "刷新失败了。。。。。。");
                        adapter.setUpFetching(false);
                        adapter.setUpFetchEnable(false);
                    }
                }, 300);

            }
        });

    }

}
