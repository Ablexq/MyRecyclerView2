package com.xq.smartrefresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedList;
import java.util.List;

public class SmartRefreshActivity extends AppCompatActivity {

    private List<String> mDatas;
    private RecyclerView recyclerView;
    private RecycleAdapter recycleAdapter;
    private Handler mHandler;
    private SmartRefreshLayout smartRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_refresh);

        mHandler = new Handler();
        initData();
        initView();
        aboutAapter();
    }

    private void aboutAapter() {
        recycleAdapter = new RecycleAdapter(this, mDatas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recyclerView.setAdapter(recycleAdapter);
        //设置 Header 为 贝塞尔雷达 样式
        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshlayout) {
                //延时展示，延时2秒
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
                        recycleAdapter.refresh(mDatas);
                        refreshlayout.finishRefresh();
                    }
                }, 2000);

            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshlayout) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData();
                        recycleAdapter.loadMore(mDatas);
                        refreshlayout.finishLoadMore();
                    }
                }, 2000);

            }
        });

        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.autoRefresh();
    }

    private void initData() {
        mDatas = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            mDatas.add("init 第" + i + "条数据");
        }
    }

    private void loadData() {
        mDatas = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            mDatas.add("load 第" + i + "条数据");
        }
    }

    private void refreshData() {
        mDatas = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            mDatas.add("refresh 第" + i + "条数据");
        }
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.srl_fresh);
    }
}
