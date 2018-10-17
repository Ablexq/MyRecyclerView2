package com.xq.divider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xq.divider.activity.BookRankActivity;
import com.xq.divider.activity.ColorDividerActivity;
import com.xq.divider.activity.DividerActivity;
import com.xq.divider.activity.HeaderActivity;
import com.xq.divider.activity.StickyHeaderActivity;
import com.xq.divider.activity.TimelineActivity;

public class DividerMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnDivider;
    Button mBtnDivider1;
    Button mBtnDivider2;
    Button mBtnDivider3;
    Button mBtnDivider4;
    Button mBtnDivider5;
    TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_divider);

        mBtnDivider = (Button) findViewById(R.id.btn_divider);
        mBtnDivider1 = (Button) findViewById(R.id.btn_divider1);
        mBtnDivider2 = (Button) findViewById(R.id.btn_divider2);
        mBtnDivider3 = (Button) findViewById(R.id.btn_divider3);
        mBtnDivider4 = (Button) findViewById(R.id.btn_divider4);
        mBtnDivider5 = (Button) findViewById(R.id.btn_divider5);
        mTv = (TextView) findViewById(R.id.tv_content);
        mBtnDivider.setOnClickListener(this);
        mBtnDivider1.setOnClickListener(this);
        mBtnDivider2.setOnClickListener(this);
        mBtnDivider3.setOnClickListener(this);
        mBtnDivider4.setOnClickListener(this);
        mBtnDivider5.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.btn_divider) {
            Intent intent = new Intent(DividerMainActivity.this, DividerActivity.class);
            startActivity(intent);


        } else if (i == R.id.btn_divider1) {
            Intent intent1 = new Intent(DividerMainActivity.this, ColorDividerActivity.class);
            startActivity(intent1);


        } else if (i == R.id.btn_divider2) {
            Intent intent2 = new Intent(DividerMainActivity.this, TimelineActivity.class);
            startActivity(intent2);


        } else if (i == R.id.btn_divider3) {
            Intent intent3 = new Intent(DividerMainActivity.this, BookRankActivity.class);
            startActivity(intent3);


        } else if (i == R.id.btn_divider4) {
            Intent intent4 = new Intent(DividerMainActivity.this, HeaderActivity.class);
            startActivity(intent4);

        } else if (i == R.id.btn_divider5) {
            Intent intent5 = new Intent(DividerMainActivity.this, StickyHeaderActivity.class);
            startActivity(intent5);
        }
    }
}
