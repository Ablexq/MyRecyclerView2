package com.xq.myrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.louisgeek.louisstickylistheadersdemo.StickyListHeadersActivity;
import com.xq.divider.DividerMainActivity;
import com.xq.headerfooter.HeaderFooterActivity;
import com.xq.loadmore.LoadMoreActivity;
import com.xq.notify.NotifyMainActivity;
import com.xq.onclick.OnclickMainActivity;
import com.xq.smartrefresh.SmartRefreshActivity;
import com.xq.stickyheadersrecyclerview.StickyRecyclerViewActivity;
import com.xq.swipe.SwipeDragMainActivity;
import com.zhy.sample.demo_recyclerview.HomeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn10;
    private Button mBtn11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViewById(R.id.btn1).setOnClickListener(this);
        this.findViewById(R.id.btn2).setOnClickListener(this);
        this.findViewById(R.id.btn3).setOnClickListener(this);
        this.findViewById(R.id.btn4).setOnClickListener(this);
        this.findViewById(R.id.btn5).setOnClickListener(this);
        this.findViewById(R.id.btn6).setOnClickListener(this);
        this.findViewById(R.id.btn7).setOnClickListener(this);
        this.findViewById(R.id.btn8).setOnClickListener(this);
        this.findViewById(R.id.btn9).setOnClickListener(this);
        btn10 = (Button) findViewById(R.id.btn10);
        btn10.setOnClickListener(this);
        mBtn11 = (Button) findViewById(R.id.btn11);
        mBtn11.setOnClickListener(this);
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

            case R.id.btn5:
                startActivity(new Intent(MainActivity.this, OnclickMainActivity.class));
                break;

            case R.id.btn6:
                startActivity(new Intent(MainActivity.this, NotifyMainActivity.class));
                break;

            case R.id.btn7:
                startActivity(new Intent(MainActivity.this, HeaderFooterActivity.class));
                break;

            case R.id.btn8:
                startActivity(new Intent(MainActivity.this, DividerMainActivity.class));
                break;

            case R.id.btn9:
                startActivity(new Intent(MainActivity.this, SwipeDragMainActivity.class));
                break;
            case R.id.btn10:
                startActivity(new Intent(MainActivity.this, LoadMoreActivity.class));
                break;

            case R.id.btn11:
                startActivity(new Intent(MainActivity.this, com.example.vidarecyclerviewstagger.MainActivity.class));
                break;
        }
    }
}
