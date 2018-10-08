package com.xq.stickyheadersrecyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.xq.stickyheadersrecyclerview.R;
import com.xq.stickyheadersrecyclerview.bean.Category;
import com.xq.stickyheadersrecyclerview.bean.Team;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    private List<Category> categoryList;
    private List<Team> teamList = new ArrayList<>();
    private Context mContext;

    public RightAdapter(Context context, List<Category> categoryList) {
        mContext = context;
        setCategoryList(categoryList);
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
        for (int i = 0; i < categoryList.size(); i++) {
            if (teamList != null) {
                teamList.addAll(categoryList.get(i).getTeamList());
            }
        }
        notifyDataSetChanged();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    /**
     * 返回值相同会被默认为同一项
     *
     * @param position
     * @return
     */
    @Override
    public long getHeaderId(int position) {
        return getSortType(position);
    }
    //这个方法是用来固定头部的，有着相同的HeaderId的Item的头部将会是同一个，
    // 这个ID可以自己设置 getSortType (position)是自定义的方法，
    // 用于根据子ITEM的position来获取服务器返回的List的父项位置。
    // 根据这个特性同时用来当HeaderID。如果有不是数据里的特殊的头部，置顶之类的，可以设置-1之类的。

    //获取当前球队的类型
    public int getSortType(int position) {
        int sort = -1;
        int sum = 0;
        for (int i = 0; i < categoryList.size(); i++) {
            if (position >= sum) {
                sort++;
            } else {
                return sort;
            }
            sum += categoryList.get(i).getTeamList().size();
        }
        return sort;
    }

    /**
     * ===================================================================================================
     * header的ViewHolder
     * ===================================================================================================
     */
    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_team_list, viewGroup, false);
        return new HeaderViewHolder(view);
    }

    //和普通的一样，绑定数据，要注意的是这个position是子项的，
    // 因为此控件机制是添加分割线addItemDecoration，所以添加头部后不会影响原位置。
    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        TextView textView = (TextView) viewHolder.itemView;
        textView.setText(categoryList.get(getSortType(position)).getSortName());
        textView.setBackgroundColor(getRandomColor());
    }


    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    private int getRandomColor() {
        SecureRandom rgen = new SecureRandom();
        return Color.HSVToColor(150, new float[]{
                rgen.nextInt(359), 1, 1
        });
    }

    /**
     * ==================================================================================================
     * 以下为contentViewHolder
     * ==================================================================================================
     */
    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContentViewHolder viewHolder = (ContentViewHolder) holder;
        viewHolder.textView.setText(teamList.get(position).getName());
        Glide.with(mContext)
                .load(teamList.get(position).getImagePath())
                .placeholder(R.drawable.icon_logo_image_default)
                .centerCrop()
                .into(viewHolder.imageViewTeam);
    }

    private class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageViewTeam;

        public ContentViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview_teamname);
            imageViewTeam = (ImageView) itemView.findViewById(R.id.imageview_team);
        }
    }
}