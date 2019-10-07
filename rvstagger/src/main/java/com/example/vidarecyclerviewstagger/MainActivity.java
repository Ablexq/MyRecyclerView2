package com.example.vidarecyclerviewstagger;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageData> mData = new ArrayList<>();
    private Context context;
    private StaggeredGridLayoutManager layoutManager;
    private ImageAdapter imageAdapter;
    private RecyclerView mRecyclerView;

    private static int[] imgRes = new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,
            R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,
            R.drawable.img9,R.drawable.img10,R.drawable.img11,R.drawable.img12,
            R.drawable.img13,R.drawable.img14,R.drawable.img15,R.drawable.img16,R.drawable.img17};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        InitData();

        context = getApplicationContext();

        mRecyclerView = findViewById(R.id.recyclerview11);

        mRecyclerView.setPadding(8,8,8,8);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(8));
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        imageAdapter = new ImageAdapter(context,mData);
        mRecyclerView.setAdapter(imageAdapter);
    }

    public void InitData(){
        for(int i = 0;i < imgRes.length;i++){
            BitmapFactory.Options options = new BitmapFactory.Options();
            BitmapFactory.decodeResource(getResources(),imgRes[i],options);

            //获取图片的宽高
            int height = options.outHeight;
            int width = options.outWidth;

            Log.i("YYYY","图片的宽度:"+width+"图片的高度:"+height);
            ImageData imgData = new ImageData(imgRes[i],height,width);
            mData.add(imgData);
        }
    }
}
