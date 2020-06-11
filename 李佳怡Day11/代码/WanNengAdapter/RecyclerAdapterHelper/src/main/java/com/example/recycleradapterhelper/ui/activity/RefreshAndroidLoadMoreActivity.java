package com.example.recycleradapterhelper.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

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
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.header.DropboxHeader;
import com.scwang.smartrefresh.header.FunGameBattleCityHeader;
import com.scwang.smartrefresh.header.FunGameHitBlockHeader;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.header.StoreHouseHeader;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.header.fungame.FunGameHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.impl.RefreshFooterWrapper;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

public class RefreshAndroidLoadMoreActivity<T> extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    //当前页码
    private int mPage = 0;
    private int mCurrentCounter;
    private SmartRefreshLayout mSmartRefreshLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        init();
    }

    private void init() {
        mSmartRefreshLayout = findViewById(R.id.refreshlayout);
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
/*

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
                                            Log.e("qwqwqwqw", "开始刷新.00000..." + mPage);
                                            // startLastPageFetach(adapter);
                                            // startRefreshData();
                                        }

                                    });
*/
     /*
                                    //TODO 下拉刷新
                                    mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                                        @Override
                                        public void onRefresh(RefreshLayout refreshLayout) {
                                            Log.e("TAG", "下拉刷新");
                                            //2秒之后完成刷新
                                            //   refreshLayout.finishRefresh(2000);
                                            //第一个参数是2秒之后完成刷新   第二个参数是否加载成功
                                            //refreshLayout.finishRefresh(2000, true);
                                            startPullRefresh(adapter, refreshLayout);

                                        }
                                    });


                                    //上拉加载方法1
                                    mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                                        @Override
                                        public void onLoadMore(RefreshLayout refreshLayout) {
                                            Log.e("TAG", "上拉加载");
                                            // refreshLayout.finishLoadMore(2000, true, false);

                                            startPullRefresh(adapter, refreshLayout);

                                        }
                                    });



      */

                                    //飞机在飞的效果
                                    mSmartRefreshLayout.setRefreshHeader(new TaurusHeader(RefreshAndroidLoadMoreActivity.this));

                                    //默认效果
                                    // mSmartRefreshLayout.setRefreshHeader(new BezierRadarHeader(RefreshAndroidLoadMoreActivity.this));

                                    //小圆球转圈效果
                                    //  mSmartRefreshLayout.setRefreshHeader(new BezierCircleHeader(RefreshAndroidLoadMoreActivity.this));
                                    //热气球效果
                                    //  mSmartRefreshLayout.setRefreshHeader(new DeliveryHeader(RefreshAndroidLoadMoreActivity.this));

                                    //太阳效果
                                    // mSmartRefreshLayout.setRefreshHeader(new PhoenixHeader(RefreshAndroidLoadMoreActivity.this));

                                    //盒子里面放东西的效果
                                    // mSmartRefreshLayout.setRefreshHeader(new DropboxHeader(RefreshAndroidLoadMoreActivity.this));

                                    //鼻涕效果
                                    //  mSmartRefreshLayout.setRefreshHeader(new WaterDropHeader(RefreshAndroidLoadMoreActivity.this));

                                    //水滴滴下来刷新的效果
                                    // mSmartRefreshLayout.setRefreshHeader(new WaveSwipeHeader(RefreshAndroidLoadMoreActivity.this));

                                    //经典效果
                                    // mSmartRefreshLayout.setRefreshHeader(new ClassicsHeader(RefreshAndroidLoadMoreActivity.this));

                                    //坦克小游戏效果
                                    //  mSmartRefreshLayout.setRefreshHeader(new FunGameBattleCityHeader(RefreshAndroidLoadMoreActivity.this));

                                    //乒乓球游戏效果
                                    //  mSmartRefreshLayout.setRefreshHeader(new FunGameHitBlockHeader(RefreshAndroidLoadMoreActivity.this));


                                    //三个橙色小球效果
                                    //mSmartRefreshLayout.setRefreshFooter(new BallPulseFooter(RefreshAndroidLoadMoreActivity.this));


                                    //经典效果
                                    mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(RefreshAndroidLoadMoreActivity.this));


                                    //这个方法既包含了下拉刷新也包含了上拉加载
                                    mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                                        @Override
                                        public void onLoadMore(RefreshLayout refreshLayout) {
                                            refreshLayout.finishLoadMore(4000, true, false);
                                        }

                                        @Override
                                        public void onRefresh(RefreshLayout refreshLayout) {
                                            refreshLayout.finishRefresh(2000, true);
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

    //下拉刷新的方法
    private void startPullRefresh(final RefreshAndLoadMoreAdapter adapter, final RefreshLayout refreshLayout) {
        mPage++;
        DataServer.getData("https://wanandroid.com/article/listproject/" + mPage + "/json", new CallBack<Object>() {
            @Override
            public <T> void onScuess(T t) {
                if (t instanceof DataInfo) {
                    final DataInfo dataInfo = ((DataInfo) t);
                    final List<DataInfo.DataBean.DatasBean> datas = dataInfo.getData().getDatas();
                    mRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {

                            //下拉刷新添加数据的方法
                            // adapter.addData(0, datas);

                            //上拉加载的方法
                            adapter.addData(datas);

                            //下拉刷新数据成功的标识
                            //refreshLayout.finishRefresh(true);

                            //上拉加载数据成功的标识
                            refreshLayout.finishLoadMore(true);
                            //当滑动到最后一页的时候，不能再下拉了，已经没有数据了
                            //判断当前页码>=总页码
                            if ((mPage + 1) >= dataInfo.getData().getPageCount()) {
                                //停止刷新  下拉刷新的结束的标识
                                //refreshLayout.finishRefresh(false);

                                refreshLayout.finishLoadMore(false);
                                Toast.makeText(RefreshAndroidLoadMoreActivity.this, "没有更多数据了", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFile(String error) {
                Log.e("TAG", error + "加载失败");
                refreshLayout.finishRefresh(false);
            }
        });


    }

    private RefreshAndLoadMoreAdapter mMoreAdapter;

    //刷新最新数据
    private void startRefreshData() {
        DataServer.getData("https://wanandroid.com/article/listproject/" + mPage + "/json", new CallBack<Object>() {
            @Override
            public <T> void onScuess(T t) {
                if (t instanceof DataInfo) {
                    final DataInfo dataInfo = (DataInfo) t;
                    final List<DataInfo.DataBean.DatasBean> datas = dataInfo.getData().getDatas();

                    mRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            //重新创建一个新的适配器
                            mMoreAdapter = new RefreshAndLoadMoreAdapter(RefreshAndroidLoadMoreActivity.this, datas);
                            mMoreAdapter.setUpFetching(false);
                        }
                    });
                }
            }

            @Override
            public void onFile(String error) {
                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mMoreAdapter != null)
                            mMoreAdapter.setUpFetching(false);
                    }
                });
            }
        });


    }


    //下拉刷新  (页码增加 )
    private void startLastPageFetach(final RefreshAndLoadMoreAdapter adapter) {
        mPage++;
        DataServer.getData("https://wanandroid.com/article/listproject/" + mPage + "/json", new CallBack<Object>() {
            @Override
            public <T> void onScuess(T t) {
                if (t instanceof DataInfo) {
                    final DataInfo dataInfo = (DataInfo) t;
                    final List<DataInfo.DataBean.DatasBean> datas = dataInfo.getData().getDatas();
                    mRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addData(0, datas);
                            adapter.setUpFetching(false);
                        }
                    });
                }
            }

            @Override
            public void onFile(String error) {
                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        //停止刷新的标识
                        adapter.setUpFetching(false);
                    }
                });
            }
        });


    }


}
