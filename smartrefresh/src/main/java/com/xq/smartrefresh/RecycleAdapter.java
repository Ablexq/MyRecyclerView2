package com.xq.smartrefresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private List<String> mDatas = new ArrayList<>();
    private LayoutInflater inflater;


    public RecycleAdapter(Context mContext, List<String> mDatas) {
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_item);
        }

    }

    public void loadMore(List<String> addList) {
        //增加数据
        int position = mDatas.size();
        mDatas.addAll(position, addList);
        notifyDataSetChanged();
    }

    public void refresh(List<String> addList) {
        if (mDatas != null && mDatas.size() > 0) {
            mDatas.clear();
        }
        if (mDatas != null) {
            mDatas.addAll(addList);
        }
        notifyDataSetChanged();
    }

}
