package com.xq.onclick;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/17.
 */

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.SecondViewHolder> {
    private ArrayList<interfaceType> mDatas;
    private DataBean mDataBean;

    public SecondAdapter() {
        mDatas = new ArrayList<>();
        ArrayList<DataBean.ParentDataBean> parentDataBeen = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<DataBean.ParentDataBean.ChildDataBean> childDataBeen = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                childDataBeen.add(new DataBean.ParentDataBean.ChildDataBean("我是父类" + i + "中子类" + j + "条数据"));
            }
            parentDataBeen.add(new DataBean.ParentDataBean("父类第" + i + "条", childDataBeen));
        }
        mDataBean = new DataBean(parentDataBeen);
        mDatas.addAll(mDataBean.getParent());
    }

    @Override
    public SecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new SecondViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parent, parent, false), viewType);
            default:
                return new SecondViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child, parent, false), viewType);
        }
    }

    @Override
    public void onBindViewHolder(SecondViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                DataBean.ParentDataBean parentDataBean = (DataBean.ParentDataBean) mDatas.get(position);
                holder.mTvParent.setText(parentDataBean.getName());
                if (parentDataBean.isOpen) {
                    holder.mDivide.setVisibility(View.GONE);
                } else {
                    holder.mDivide.setVisibility(View.VISIBLE);
                }
                break;
            default:
                DataBean.ParentDataBean.ChildDataBean childDataBean = (DataBean.ParentDataBean.ChildDataBean) mDatas.get(position);
                if (position == mDatas.size() - 1 || mDatas.get(position + 1) instanceof DataBean.ParentDataBean) {
                    holder.mDivide.setVisibility(View.VISIBLE);
                } else {
                    holder.mDivide.setVisibility(View.GONE);
                }
                holder.mTvChild.setText(childDataBean.getChildName());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).itemType();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public interfaceType getData(int position) {
        return mDatas.get(position);
    }

    public void closeParent(int position, RecyclerView.ViewHolder viewHolder) {
        ArrayList<DataBean.ParentDataBean.ChildDataBean> childs = ((DataBean.ParentDataBean) mDatas.get(position)).getChilds();
        onBindViewHolder((SecondViewHolder) viewHolder, position);
        mDatas.removeAll(childs);
        notifyItemRangeRemoved(position + 1, childs.size());
    }

    public void openParent(int position, RecyclerView.ViewHolder viewHolder) {
        ArrayList<DataBean.ParentDataBean.ChildDataBean> childs = ((DataBean.ParentDataBean) mDatas.get(position)).getChilds();
        onBindViewHolder((SecondViewHolder) viewHolder, position);
        mDatas.addAll(position + 1, childs);
        notifyItemRangeInserted(position + 1, childs.size());
    }

    static class SecondViewHolder extends RecyclerView.ViewHolder {
        TextView mTvParent, mTvChild;
        View mDivide;

        public SecondViewHolder(View itemView, int type) {
            super(itemView);
            switch (type) {
                case 0:
                    mTvParent = (TextView) itemView.findViewById(R.id.tv_parent);
                    break;
                default:
                    mTvChild = (TextView) itemView.findViewById(R.id.tv_child);
                    break;
            }
            mDivide = itemView.findViewById(R.id.divide);
        }
    }
}
