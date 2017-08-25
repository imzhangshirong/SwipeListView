package com.zhangshirong.swipelistview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by jarvis on 17-2-15.
 */

public class SwipeListViewScroll extends HorizontalScrollView{
    private boolean isOperating = false;
    private boolean isOpen = false;
    private boolean canSwipe = true;
    private SwipeRefreshLayout swipe = null;
    private SwipeListView swipeListView = null;
    private int last;
    private View contentView;
    private boolean willClose=false;
    private int moveWidth = (int) (20*getResources().getDisplayMetrics().density);
    private int width = 0;
    private int index = -1;

    public SwipeListViewScroll(Context context) {
        super(context);
    }

    public SwipeListViewScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeListViewScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public boolean isOpen(){
        return isOpen;
    }
    public boolean isOperating(){
        return isOperating;
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            width=r-l;
            initLayout(width);
            measureChildren(width,b-t);//重新设置了子元素LayoutParams，更新子元素
        }
        super.onLayout(changed, l, t, r, b);
    }

    public void setCanSwipe(boolean canSwipe){
        this.canSwipe = canSwipe;
        if(!canSwipe && isOpen)close();
    }
    private void initLayout(final int width){
        this.setHorizontalScrollBarEnabled(false);
        this.setVerticalScrollBarEnabled(false);
        this.setFocusableInTouchMode(true);
        this.setFocusable(true);
        try{
            swipeListView = (SwipeListView) this.getParent().getParent();
            swipeListView.addSwipeListViewScroll(this);
            LinearLayout layout = (LinearLayout) getChildAt(0);
            contentView = layout.getChildAt(0);
            contentView.setFocusable(true);
            contentView.setClickable(true);
            contentView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(swipeListView.listener!=null)swipeListView.listener.OnClick(SwipeListViewScroll.this,index);
                }
            });
            contentView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(swipeListView.listener!=null){
                        return swipeListView.listener.OnLongClick(SwipeListViewScroll.this,index);
                    }
                    return false;
                }
            });
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) contentView.getLayoutParams();
            lp.width = width;
            contentView.setLayoutParams(lp);
            if(swipeListView.controlIds != null){
                for(int i = 0;i<swipeListView.controlIds.length;i++){
                    final int key = swipeListView.controlIds[i];
                    View view = layout.findViewById(key);
                    view.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(swipeListView.listener!=null){
                                swipeListView.listener.OnControlClick(key,v,index);
                            }
                        }
                    });
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void setIndex(int index){
        this.index = index;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        //this.scrollTo(0,0);
        super.onDraw(canvas);
    }
    public void setSwipe(SwipeRefreshLayout swipe){
        this.swipe = swipe;
    }
    public void close(){
        this.smoothScrollTo(0,0);
        isOpen = false;
    }
    public void open(){
        if(!canSwipe){
            isOpen = false;
            return;
        }
        if(contentView == null)return;
        try{
            if(swipeListView!=null)swipeListView.closeAllSwipeListViewScroll();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        this.smoothScrollTo(contentView.getWidth(),0);
        isOpen = true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(!canSwipe)return false;
        boolean sys = super.onTouchEvent(ev);//先执行系统要处理的，否则无法滚动
        int scrollX = this.getScrollX();
        if(ev.getAction() != MotionEvent.ACTION_MOVE){
            if(scrollX < moveWidth){
                close();
            }
            else{
                if(willClose){
                    close();
                }
                else{
                    open();
                }
            }
        }
        if(Math.abs(last - scrollX) >= 0){
            if(willClose){
                if(last < scrollX)willClose = false;
            }
            else{
                if(last > scrollX)willClose = true;
            }
            last = scrollX;
        }
        if(swipe != null){
            if(ev.getAction() != MotionEvent.ACTION_MOVE){
                swipe.setEnabled(true);
            }
            else{
                swipe.setEnabled(false);
            }
        }
        if(!isOperating){
            isOperating = true;
        }
        if(ev.getAction() != MotionEvent.ACTION_MOVE){
            isOperating = false;
            last = scrollX;
        }
        return sys;

    }
}
