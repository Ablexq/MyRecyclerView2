

[csdn博客专栏：玩转RecyclerView的方方面面](https://blog.csdn.net/column/details/13868.html)


[关于 RecyclerView 优化的一些建议](https://blog.csdn.net/yyh352091626/article/details/80064586)

# 滚动不加载图片

[滚动RecyclerView加载图片时的流畅度优化](https://blog.csdn.net/ysy950803/article/details/71909944)


# 点击事件

[揭开RecyclerView的神秘面纱(二)：处理RecyclerView的点击事件](https://blog.csdn.net/zchlww/article/details/51525579)

方法一：onBindViewHolder中利用View.onClickListener及onLongClickListener


方法二：利用RecyclerView.OnItemTouchListener

# 局部刷新

```
//刷新所有
public final void notifyDataSetChanged();


//单个刷新
//position数据发生了改变，那调用这个方法，就会回调对应position的onBindViewHolder()方法了
public final void notifyItemChanged(int position);


//单个添加
//在第position位置被插入了一条数据的时候可以使用这个方法刷新，
//注意这个方法调用后会有插入的动画，这个动画可以使用默认的，也可以自己定义
public final void notifyItemInserted(int position);


//单个删除
//第position个被删除的时候刷新，同样会有动画
public final void notifyItemRemoved(int position);


//移动位置
//列表fromPosition位置的数据移到toPosition位置时调用，伴有动画效果
//从fromPosition移动到toPosition为止的时候可以使用这个方法刷新
public final void notifyItemMoved(int fromPosition, int toPosition);
```

```
//批量刷新
//刷新从positionStart开始itemCount数量的item了（这里的刷新指回调onBindViewHolder()方法）
public final void notifyItemRangeChanged(int positionStart, int itemCount);


//批量添加
//列表从positionStart位置到itemCount数量的列表项批量添加数据时调用，伴有动画效果
public final void notifyItemRangeInserted(int positionStart, int itemCount);


//批量删除
//列表从positionStart位置到itemCount数量的列表项批量删除数据时调用，伴有动画效果
public final void notifyItemRangeRemoved(int positionStart, int itemCount);
```



# GridLayoutManager setSpanSizeLookup()方法

[12_GridLayoutManager setSpanSizeLookup()方法](https://www.jianshu.com/p/29465cce1131)



# onCreateViewHolder与onBindViewHolder多次调用避免创建对象



# 自定义分割线ItemDecoration与StickyHeader

```
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    //撑开 ItemView 上、下、左、右四个方向的空间
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    //在 ItemView 内容之下绘制图形
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    //在 ItemView 内容之上绘制图形
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }


}

```

[RecyclerView 之 ItemDecoration 讲解及高级特性实践](https://blog.csdn.net/briblue/article/details/70161917)

[RecyclerView探索之通过ItemDecoration实现StickyHeader效果](https://blog.csdn.net/briblue/article/details/70211942)


# 自定义LayoutManager

[RecyclerView自定义LayoutManager,打造不规则布局](https://blog.csdn.net/qibin0506/article/details/52676670)

[打造属于你的LayoutManager](https://blog.csdn.net/huachao1001/article/details/51594004#rd)

[把RecyclerView撸成 马 蜂 窝(自定义LayoutManager)](https://chacojack.github.io/2016/09/14/%E6%8A%8ARecyclerView%E5%81%9A%E6%88%90-%E9%A9%AC-%E8%9C%82-%E7%AA%9D/)

[自定义LayoutManager 实现弧形以及滑动放大效果RecyclerView](https://www.jianshu.com/p/7bb7556bbe10)

[关于Android RecyclerView的那些开源LayoutManager](https://zhuanlan.zhihu.com/p/26003028)

# 自定义RecyclerView动画

[自定义RecyclerView动画——实现remove飞出效果](https://www.jianshu.com/p/d2cc0c4fbded)

[github：recyclerview star数最多的动画库](https://github.com/wasabeef/recyclerview-animators)

[自定义RecyclerView.ItemAnimator其实很简单（上）](https://www.jianshu.com/p/2a82b0341138)

[自定义RecyclerView.ItemAnimator其实很简单（下）](https://www.jianshu.com/p/b9aef3597f2d)


# 侧滑ItemTouchHelper

[github : star数最多的demo](https://github.com/iPaulPro/Android-ItemTouchHelper-Demo)



```
recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

    int itemCount;
    int childCount;
    int firstVisibleItemPosition;
    int lastVisibleItemPosition;
    int firstCompletelyVisibleItemPosition;
    int lastCompletelyVisibleItemPosition;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        switch (newState) {
            case RecyclerView.SCROLL_STATE_DRAGGING://被拖动
                System.out.println("1====onScrollStateChanged===SCROLL_STATE_DRAGGING============");
                break;
            case RecyclerView.SCROLL_STATE_SETTLING://自由滑行
                System.out.println("2====onScrollStateChanged===SCROLL_STATE_SETTLING===========");
                break;
            case RecyclerView.SCROLL_STATE_IDLE://静止
                System.out.println("3====onScrollStateChanged===SCROLL_STATE_IDLE==========");
                break;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
//                dx > 0 时为手指向左滚动,列表滚动显示右面的内容
//                dx < 0 时为手指向右滚动,列表滚动显示左面的内容
//                dy > 0 时为手指向上滚动,列表滚动显示下面的内容
//                dy < 0 时为手指向下滚动,列表滚动显示上面的内容

        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        itemCount = layoutManager.getItemCount();//总共
        System.out.println("onScrolled==itemCount==================" + itemCount);

        childCount = layoutManager.getChildCount();//可见
        System.out.println("onScrolled==childCount==================" + childCount);

        firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();//当前第一个可见Item的position
        lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();//当前最后一个可见Item的position
        firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();//当前第一个完全可见Item的position
        lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();//当前最后一个完全可见Item的position
        System.out.println("1=onScrolled==firstVisibleItemPosition=========" + firstVisibleItemPosition);
        System.out.println("2=onScrolled==lastVisibleItemPosition=========" + lastVisibleItemPosition);
        System.out.println("3=onScrolled==firstCompletelyVisibleItemPosition=========" + firstCompletelyVisibleItemPosition);
        System.out.println("4=onScrolled==lastCompletelyVisibleItemPosition=========" + lastCompletelyVisibleItemPosition);
    }
});
```


[Android开发之一些好用的RecyclerView轮子](https://www.jianshu.com/p/a96e009e367c)


自定义layoutmanager

[DingMouRen/LayoutManagerGroup](https://github.com/DingMouRen/LayoutManagerGroup)

ViewDragHelper 与 viewgroup 侧滑

[Android ViewDragHelper完全解析 自定义ViewGroup神器](https://blog.csdn.net/lmj623565791/article/details/46858663)

[ViewDragHelper实战 自己打造Drawerlayout](https://blog.csdn.net/lmj623565791/article/details/47396187)


SnapHelper 与 RecyclerView 滚动 模仿viewpager


ItemTouchHelper 与 RecyclerView 侧滑换位


ItemDecoration
LayoutManager
RecyclerView.Adapter
DiffUtil
SimpleOnItemTouchListener
SmoothScroller
ItemAnimator


# Dagger2

对象管理，降低耦合

# mvp

内存泄漏：AutoDispose RxLifecycle

# Arouter
