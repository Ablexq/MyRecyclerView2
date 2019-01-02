package com.xq.loadmore;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by csc on 2018/4/3.
 * explain：在Adapter中设置好FooterView之后，我们需要判断一下什么时候显示出来，
 * 这时就需要对RecyclerView设置一下滑动监听，当滑动到最后一条Item的时候，显示加载更多UI(Footer)
 * 并且开始请求下一个列表数据
 */

abstract class EndLessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    //用来记录是否正在向上滑动
    private boolean isSlidingUpward = false;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();

        //当前不滑动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            //获取到最后一个完全显示的ItemPosition
            int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
            //现在总条目数
            int itemCount = manager.getItemCount();
            //判断是否滑动到了最后一个Item，并且向上滑动
            if (lastItemPosition == (itemCount - 1) && isSlidingUpward) {
                //加载更多
                onLoadMore();
            }
        }
    }

    //判断RecyclerView是否是向上滑动

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //大于0表示正在向上滑动，小于0表示停止或向下滑动
        isSlidingUpward = dy > 0;


        System.out.println("dy===============" + dy);

    }

    //加载更多的数据回调
    public abstract void onLoadMore();
}
