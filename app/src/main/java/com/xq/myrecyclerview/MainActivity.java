package com.xq.myrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.louisgeek.louisstickylistheadersdemo.StickyListHeadersActivity;
import com.xq.smartrefresh.SmartRefreshActivity;
import com.xq.stickyheadersrecyclerview.StickyRecyclerViewActivity;
import com.zhy.sample.demo_recyclerview.HomeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViewById(R.id.btn1).setOnClickListener(this);
        this.findViewById(R.id.btn2).setOnClickListener(this);
        this.findViewById(R.id.btn3).setOnClickListener(this);
        this.findViewById(R.id.btn4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this, SmartRefreshActivity.class));
                break;

            case R.id.btn2:
                startActivity(new Intent(MainActivity.this, StickyRecyclerViewActivity.class));
                break;

            case R.id.btn3:
                startActivity(new Intent(MainActivity.this, StickyListHeadersActivity.class));
                break;

            case R.id.btn4:
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                break;
        }
    }
}
