package com.xq.stickyheadersrecyclerview;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.xq.stickyheadersrecyclerview.adapter.LeftAdapter;
import com.xq.stickyheadersrecyclerview.adapter.RightAdapter;
import com.xq.stickyheadersrecyclerview.bean.Category;
import com.xq.stickyheadersrecyclerview.bean.Team;

import java.util.ArrayList;
import java.util.List;

/*
*  来自 https://github.com/heinika/ElmShopByRecyclerView
*   RecyclerView仿饿了吗点菜页面 https://www.jianshu.com/p/afb8ff16701e
*
*   模仿阿里巴巴做的日期选择页 https://github.com/YuanTiger/YTDateSelect
*   https://www.jianshu.com/p/e229dd9bdcde
*
* */
public class StickyRecyclerViewActivity extends AppCompatActivity implements LeftAdapter.OnItemClickListener {

    private RecyclerView recyclerviewLeft;
    private RecyclerView recyclerviewRight;
    private LinearLayoutManager mRightLayoutManager;
    private LinearLayoutManager mLeftLayoutManager;

    private List<Category> rightList;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private int oldSelectedPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_recyclerview);
        recyclerviewLeft = (RecyclerView) findViewById(R.id.recyclerview_left);
        recyclerviewRight = (RecyclerView) findViewById(R.id.recyclerview_right);
        initData();
        initViews();
    }


    private void initViews() {
        mLeftLayoutManager = new LinearLayoutManager(this);
        recyclerviewLeft.setLayoutManager(mLeftLayoutManager);
        leftAdapter = new LeftAdapter(this, rightList);
        leftAdapter.setOnItemClickListener(this);
        recyclerviewLeft.setAdapter(leftAdapter);

        mRightLayoutManager = new LinearLayoutManager(this);
        recyclerviewRight.setLayoutManager(mRightLayoutManager);
        rightAdapter = new RightAdapter(this, rightList);
        recyclerviewRight.setAdapter(rightAdapter);
        // Add the sticky headers decoration,给球队添加标题
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(rightAdapter);
        recyclerviewRight.addItemDecoration(headersDecor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerviewRight.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (needMove) {
                        needMove = false;
                        //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                        int n = movePosition - mRightLayoutManager.findFirstVisibleItemPosition();
                        if (0 <= n && n < recyclerviewRight.getChildCount()) {
                            //获取要置顶的项顶部离RecyclerView顶部的距离
                            int top = recyclerviewRight.getChildAt(n).getTop() - dip2px(StickyRecyclerViewActivity.this, 28);
                            //最后的移动
                            recyclerviewRight.scrollBy(0, top);
                        }
                    }
                    //第一个完全显示的item和最后一个item。
                    if (!isChangeByCategoryClick) {
                        int firstVisibleItem = mRightLayoutManager.findFirstCompletelyVisibleItemPosition();
                        int sort = rightAdapter.getSortType(firstVisibleItem);
                        changeSelected(sort);
                    } else {
                        isChangeByCategoryClick = false;
                    }
                }
            });
        } else {
            recyclerviewRight.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (needMove) {
                        needMove = false;
                        //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                        int n = movePosition - mRightLayoutManager.findFirstVisibleItemPosition();
                        if (0 <= n && n < recyclerviewRight.getChildCount()) {
                            //获取要置顶的项顶部离RecyclerView顶部的距离
                            int top = recyclerviewRight.getChildAt(n).getTop() - dip2px(StickyRecyclerViewActivity.this, 28);
                            //最后的移动
                            recyclerviewRight.scrollBy(0, top);
                        }
                    }
                    //第一个完全显示的item和最后一个item。
                    if (!isChangeByCategoryClick) {
                        int firstVisibleItem = mRightLayoutManager.findFirstCompletelyVisibleItemPosition();
                        int sort = rightAdapter.getSortType(firstVisibleItem);
                        changeSelected(sort);
                    } else {
                        isChangeByCategoryClick = false;
                    }
                }
            });
        }
    }

    private boolean needMove = false;
    private int movePosition;

    private void moveToPosition(int n) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = mRightLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mRightLayoutManager.findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            recyclerviewRight.scrollToPosition(n);
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = recyclerviewRight.getChildAt(n - firstItem).getTop();
            recyclerviewRight.scrollBy(0, top - dip2px(this, 28));
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            recyclerviewRight.scrollToPosition(n);
            movePosition = n;
            needMove = true;
        }
    }

    @Override
    public void onItemClick(int position) {
        changeSelected(position);
        moveToThisSortFirstItem(position);
        isChangeByCategoryClick = true;
    }

    private void moveToThisSortFirstItem(int position) {
        movePosition = 0;
        for (int i = 0; i < position; i++) {
            movePosition += rightAdapter.getCategoryList().get(i).getTeamList().size();
        }
        moveToPosition(movePosition);
    }

    private boolean isChangeByCategoryClick = false;

    private void changeSelected(int position) {
        rightList.get(oldSelectedPosition).setSeleted(false);
        rightList.get(position).setSeleted(true);
        //增加左侧联动
        recyclerviewLeft.scrollToPosition(position);
        oldSelectedPosition = position;
        leftAdapter.notifyDataSetChanged();
    }

    /**
     * 根据手机分辨率从dp转成px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void initData() {
        rightList = new ArrayList<>();
        List<Team> teamList1 = new ArrayList<>();
        teamList1.add(new Team("多特蒙德", "http://img1.imgtn.bdimg.com/it/u=1400488354,545185599&fm=21&gp=0.jpg"));
        teamList1.add(new Team("拜仁慕尼黑", "http://img5.imgtn.bdimg.com/it/u=1016826229,3053766616&fm=21&gp=0.jpg"));
        teamList1.add(new Team("沃尔夫斯堡", "http://img2.imgtn.bdimg.com/it/u=1102871345,1624426389&fm=15&gp=0.jpg"));
        teamList1.add(new Team("门兴", "http://c.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=5d24504035fae6cd18b9a3336eda6441/eaf81a4c510fd9f91d25e41e252dd42a2834a493.jpg"));
        Category c1 = new Category("德甲", teamList1);
        c1.setSeleted(true);

        List<Team> teamList2 = new ArrayList<>();
        teamList2.add(new Team("巴塞罗那", "http://www.sinaimg.cn/lf/sports/logo85/130.png"));
        teamList2.add(new Team("皇家马德里", "http://www.sinaimg.cn/lf/sports/logo85/157.png"));
        teamList2.add(new Team("马德里竞技", "http://www.sinaimg.cn/lf/sports/logo85/162.png"));
        Category c2 = new Category("西甲", teamList2);

        List<Team> teamList3 = new ArrayList<>();
        teamList3.add(new Team("尤文图斯", "http://www.sinaimg.cn/lf/sports/logo85/108.png"));
        teamList3.add(new Team("国际米兰", "http://www.sinaimg.cn/lf/sports/logo85/103.png"));
        teamList3.add(new Team("AC米兰", "http://www.sinaimg.cn/lf/sports/logo85/104.png"));
        teamList3.add(new Team("罗马", "http://www.sinaimg.cn/lf/sports/logo85/111.png"));
        Category c3 = new Category("意甲", teamList3);

        List<Team> teamList4 = new ArrayList<>();
        teamList4.add(new Team("曼联", "http://www.sinaimg.cn/lf/sports/logo85/52.png"));
        teamList4.add(new Team("曼城", "http://www.sinaimg.cn/lf/sports/logo85/216.png"));
        teamList4.add(new Team("切尔西","http://www.sinaimg.cn/lf/sports/logo85/60.png"));
        teamList4.add(new Team("阿森纳","http://www.sinaimg.cn/lf/sports/logo85/61.png"));
        teamList4.add(new Team("莱斯特成","http://www.sinaimg.cn/lf/sports/logo85/92.png"));
        Category c4 = new Category("英超", teamList4);

        List<Team> teamList5 = new ArrayList<>();
        teamList5.add(new Team("北京国安", "http://www.sinaimg.cn/ty/2015/0127/U6521P6DT20150127115830.png"));
        teamList5.add(new Team("广州恒大","http://www.sinaimg.cn/ty/2015/0127/U6521P6DT20150127124548.png"));
        teamList5.add(new Team("山东鲁能","http://www.sinaimg.cn/ty/2015/0127/U6521P6DT20150127115709.png"));
        teamList5.add(new Team("江苏苏宁","http://www.sinaimg.cn/ty/2016/0108/U6521P6DT20160108153302.png"));
        teamList5.add(new Team("上海上港","http://www.sinaimg.cn/ty/2015/0127/U6521P6DT20150127122231.png"));
        Category c5 = new Category("中超", teamList5);


        rightList.add(c1);
        rightList.add(c2);
        rightList.add(c3);
        rightList.add(c4);
        rightList.add(c5);
//        for (int i=0;i<10;i++){
//            List<Team> teamList = new ArrayList<>();
//            teamList.add(new Team("北京国安","http://www.sinaimg.cn/ty/2015/0127/U6521P6DT20150127115830.png"));
//            teamList.add(new Team("广州恒大","http://www.sinaimg.cn/ty/2015/0127/U6521P6DT20150127124548.png"));
//            teamList.add(new Team("山东鲁能","http://www.sinaimg.cn/ty/2015/0127/U6521P6DT20150127115709.png"));
//            teamList.add(new Team("江苏苏宁","http://www.sinaimg.cn/ty/2016/0108/U6521P6DT20160108153302.png"));
//            teamList.add(new Team("上海上港","http://www.sinaimg.cn/ty/2015/0127/U6521P6DT20150127122231.png"));
//            Category c = new Category("中超",teamList);
//            rightList.add(c);
//        }
    }
}


