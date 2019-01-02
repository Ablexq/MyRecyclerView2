package com.xq.loadmore;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


public class LoadMoreWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //普通的布局
    private final int TYPE_ITEM = 0;
    //脚布局
    private final int TYPE_FOOTER = 1;
    //当前加载状态，默认为加载完成
    private int loadState = 2;
    //正在加载
    public final int LOADING = 1;
    //加载完成
    public final int LOADING_COMPLETE = 2;
    //加载到底
    public final int LOADING_END = 3;

    //适配器
    private RecyclerView.Adapter mAdapter;

    public LoadMoreWrapper(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    //分布局
    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //进行判断显示类型，来创建还回不同的View
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_refresh_footer, parent, false);
            //还回的是脚布局
            return new FootViewHolder(view);
        } else {
            //还回的是内容的布局
            return mAdapter.onCreateViewHolder(parent, viewType);
        }
    }


    //绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            switch (loadState) {
                //表示正在加载
                case LOADING:
                    footViewHolder.pbLoading.setVisibility(View.VISIBLE);
                    footViewHolder.tvLoading.setVisibility(View.VISIBLE);
                    footViewHolder.llEnd.setVisibility(View.GONE);
                    break;

                //表示加载完成
                case LOADING_COMPLETE:
                    footViewHolder.pbLoading.setVisibility(View.INVISIBLE);
                    footViewHolder.tvLoading.setVisibility(View.INVISIBLE);
                    footViewHolder.llEnd.setVisibility(View.GONE);
                    break;

                //表示加载到底
                case LOADING_END:
                    footViewHolder.pbLoading.setVisibility(View.GONE);
                    footViewHolder.tvLoading.setVisibility(View.GONE);
                    footViewHolder.llEnd.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        } else {
            mAdapter.onBindViewHolder(holder, position);
        }

    }

    //总的条目数量
    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + 1;
    }

    //脚布局
    private class FootViewHolder extends RecyclerView.ViewHolder {
        ProgressBar pbLoading;
        TextView tvLoading;
        LinearLayout llEnd;

        public FootViewHolder(View itemView) {
            super(itemView);
            pbLoading = (ProgressBar) itemView.findViewById(R.id.pb_loading);
            tvLoading = (TextView) itemView.findViewById(R.id.tv_loading);
            llEnd = (LinearLayout) itemView.findViewById(R.id.ll_end);
        }
    }


    //当时网格布局的时候Foot需要居中显示
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    //如果是脚布局据合并单元格
                    return getItemViewType(position) == TYPE_FOOTER ? 1 : gridLayoutManager.getSpanCount();
                }
            });
        }
    }

    //设置上拉的时候改变对应的状态
    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }
}
