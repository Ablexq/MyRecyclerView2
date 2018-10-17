

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



# 分割线

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



