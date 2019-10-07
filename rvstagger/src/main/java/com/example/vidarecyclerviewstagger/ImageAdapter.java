package com.example.vidarecyclerviewstagger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private ArrayList<ImageData> mData;

    public ImageAdapter(Context context, ArrayList<ImageData> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageViewHolder holder = new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item11, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        final ImageData data = mData.get(position);

        //获取item宽度，计算图片等比例缩放后的高度，为imageView设置参数
        ImageView imageView = holder.imageView;
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            if (layoutParams != null) {
                float itemWidth = (ScreenUtils.getScreenWidth(context) - 16 * 3) >> 1;
                layoutParams.width = (int) itemWidth;
                float scale = (itemWidth + 0f) / data.getWidth();
                layoutParams.height = (int) (data.getHeight() * scale);
                imageView.setLayoutParams(layoutParams);

                Glide.with(context).
                        load(data.getResourceId()).
                        override(layoutParams.width, layoutParams.height).
                        into(imageView);
            }
        }
    }


    @Override
    public int getItemCount() {
        if (mData == null) return 0;
        else return mData.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image11);
        }
    }
}
