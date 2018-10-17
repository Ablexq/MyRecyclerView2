package com.xq.onclick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

//RecyclerView的优化
//https://blog.csdn.net/zhq217217/article/details/79082093
public class OnclickMainActivity extends AppCompatActivity {
    private RecyclerView mRec;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_onclick);
        mRec = (RecyclerView) findViewById(R.id.activity_main);
        mAdapter = new MyAdapter();
        mRec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRec.setHasFixedSize(true);
        mRec.setAdapter(mAdapter);
    }
}
