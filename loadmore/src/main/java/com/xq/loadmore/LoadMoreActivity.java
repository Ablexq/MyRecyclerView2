package com.xq.loadmore;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/*
*
* RecyclerView加载更多（有Foot）
* https://blog.csdn.net/csclmf/article/details/79807802
*
* */
public class LoadMoreActivity extends Activity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<String> lists = new ArrayList<>();

    //数据的总页数
    private int totalPage = 5;
    //当前加载的页数
    private int currentPage = 0;
    private LoadMoreWrapper loadMoreWrapper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        myAdapter = new MyAdapter();

        loadMoreWrapper = new LoadMoreWrapper(myAdapter);

        recyclerView.setAdapter(loadMoreWrapper);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        initData();
        myAdapter.appendData(lists);


        //给RecyclerView设置加载更多监听
        recyclerView.addOnScrollListener(new EndLessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                /***************显示正在加载数据的footer****************/
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);

                //判断加载的页数 等于 总的页数
                if (totalPage == currentPage) {
                    /***********************表示数据加载完成************************/
                    loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
                } else {
                    //表示去加载更多的数据

                    //模拟一个耗时操作
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    getMoreData();

                                    /*******************隐藏显示正在加载条目的Footer布局UI************/
                                    loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
                                }
                            });
                        }
                    }, 2000);

                }
            }
        });

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            int itemCount;
//            int childCount;
//            int firstVisibleItemPosition;
//            int lastVisibleItemPosition;
//            int firstCompletelyVisibleItemPosition;
//            int lastCompletelyVisibleItemPosition;
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                switch (newState) {
//                    case RecyclerView.SCROLL_STATE_DRAGGING://被拖动
//                        System.out.println("1====onScrollStateChanged===SCROLL_STATE_DRAGGING============");
//                        break;
//                    case RecyclerView.SCROLL_STATE_SETTLING://自由滑行
//                        System.out.println("2====onScrollStateChanged===SCROLL_STATE_SETTLING===========");
//                        break;
//                    case RecyclerView.SCROLL_STATE_IDLE://静止
//                        System.out.println("3====onScrollStateChanged===SCROLL_STATE_IDLE==========");
//                        break;
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
////                dx > 0 时为手指向左滚动,列表滚动显示右面的内容
////                dx < 0 时为手指向右滚动,列表滚动显示左面的内容
////                dy > 0 时为手指向上滚动,列表滚动显示下面的内容
////                dy < 0 时为手指向下滚动,列表滚动显示上面的内容
//
//                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//
//                itemCount = layoutManager.getItemCount();//总共
//                System.out.println("onScrolled==itemCount==================" + itemCount);
//
//                childCount = layoutManager.getChildCount();//可见
//                System.out.println("onScrolled==childCount==================" + childCount);
//
//                firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();//当前第一个可见Item的position
//                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();//当前最后一个可见Item的position
//                firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();//当前第一个完全可见Item的position
//                lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();//当前最后一个完全可见Item的position
//                System.out.println("1=onScrolled==firstVisibleItemPosition=========" + firstVisibleItemPosition);
//                System.out.println("2=onScrolled==lastVisibleItemPosition=========" + lastVisibleItemPosition);
//                System.out.println("3=onScrolled==firstCompletelyVisibleItemPosition=========" + firstCompletelyVisibleItemPosition);
//                System.out.println("4=onScrolled==lastCompletelyVisibleItemPosition=========" + lastCompletelyVisibleItemPosition);
//            }
//        });
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            lists.add("item11" + i);
        }
    }

    //设置一个添加假数据
    public void getMoreData() {
        currentPage++;
        for (int i = 0; i < 20; i++) {
            lists.add("我是加载更多的数据:" + i + ",当前的页数是:" + currentPage);
        }
    }

}
