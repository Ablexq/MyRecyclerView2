package com.xq.onclick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/1/17.
 */

public class SecondActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SecondAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_onclick);
        recyclerView = (RecyclerView) findViewById(R.id.activity_main);
        mAdapter = new SecondAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                Log.e("TAG", "onItemClick: "+mAdapter.getItemCount());
                Log.e("TAG", "onItemClick: "+position);
                switch (mAdapter.getItemViewType(position)) {
                    case 0:
                        DataBean.ParentDataBean data = (DataBean.ParentDataBean) mAdapter.getData(position);
                        if(data.isOpen){
                            data.setOpen(false);
                            mAdapter.closeParent(position,viewHolder);
                        }else{
                            data.setOpen(true);
                            mAdapter.openParent(position,viewHolder);
                        }
                        break;
                    default:
                        Toast.makeText(SecondActivity.this, ((DataBean.ParentDataBean.ChildDataBean) mAdapter.getData(position)).getChildName(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
