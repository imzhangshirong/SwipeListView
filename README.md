# SwipeListView
## 仿qq列表滑动效果，适应效果强，可进行自定义或动态调整抽屉内容。

<video id="video" controls="" preload="none">
      <source id="mp4" src="device-2017-02-15-034240.mp4" type="video/mp4">
</video>

### SwipeListView 公开方法
- void addSwipeListViewScroll(SwipeListViewScroll swipeListViewScroll)
- void removeSwipeListViewScroll(SwipeListViewScroll swipeListViewScroll)
- void clearSwipeListViewScroll()

### SwipeListViewScroll 公开方法
- boolean isOperating()
- boolean isOpen()
- void setSwipe(SwipeRefreshLayout swipe)
- void open()
- void close()

### 使用说明
- 1、建立列表项Layout文件，基本要求如下（简单举例，详细参考Demo）<br><pre>
&lt;SwipeListViewScroll&gt;<br>
    &lt;LinearLayout&gt;<br>
        &lt;LinearLayout&gt;<br>
            &lt;!--&gt;列表主题内容&lt;--&gt;
        &lt;/LinearLayout&gt;<br>
        &lt;LinearLayout&gt;<br>
            &lt;!--&gt;操作的滑动抽屉菜单按钮&lt;--&gt;
        &lt;/LinearLayout&gt;<br>
    &lt;/LinearLayout&gt;<br>
&lt;/SwipeListViewScroll&gt;</pre>
- 2、在Activity Layout里使用SwipeListView
- 3、设置ListAdapter