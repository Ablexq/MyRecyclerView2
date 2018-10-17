package com.xq.onclick;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.AViewHolder> {
    private ArrayList<String> mDatas;

    public MyAdapter() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            mDatas.add("我是" + i + "条数据");
        }
    }

    @Override
    public AViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false), mDatas);
    }

    @Override
    public void onBindViewHolder(AViewHolder holder, int position) {
        //设置数据，绑定tag
        holder.mBtn.setText(mDatas.get(position));
        holder.mBtn.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class AViewHolder extends RecyclerView.ViewHolder {
        Button mBtn;
        /**
         * 将数据通过构造器传递进来
         */
        ArrayList<String> mDatas;

        public AViewHolder(View itemView, ArrayList<String> data) {
            super(itemView);
            this.mDatas = data;
            mBtn = (Button) itemView.findViewById(R.id.item_button);
            mBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();
                    Log.e("TAG", "onClick: " + mDatas.get(position));
                    v.getContext().startActivity(new Intent(v.getContext(), SecondActivity.class));
                }
            });
        }
    }
}
