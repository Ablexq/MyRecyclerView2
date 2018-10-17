package com.xq.headerfooter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/*
* RecyclerView的使用（3）之添加Header和Footer
* https://blog.csdn.net/leejizhou/article/details/50742544
* */
public class HeaderFooterActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HeaderBottomAdapter adapter;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_header_footer);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        //List布局
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter = new HeaderBottomAdapter(this));
    }

    public void onclick(View view) {
        int i = view.getId();
        if (i == R.id.btn_switch1) {
            //List布局
            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(adapter = new HeaderBottomAdapter(this));

        } else if (i == R.id.btn_switch2) {
            //Grid布局
            gridLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(gridLayoutManager);//这里用线性宫格显示 类似于grid view
            mRecyclerView.setAdapter(adapter = new HeaderBottomAdapter(this));


            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // 找每行个数的几个数的最小公倍数getSpanCount()，然后分别计算几种类型的占比：spancount/每行个数=spansize
                    return (adapter.isHeaderView(position) || adapter.isBottomView(position)) ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }

}