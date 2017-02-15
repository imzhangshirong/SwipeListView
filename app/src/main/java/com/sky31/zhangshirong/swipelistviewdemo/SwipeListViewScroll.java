package com.sky31.zhangshirong.swipelistviewdemo;

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
    private SwipeRefreshLayout swipe = null;
    private int last;
    private View control;
    private boolean willClose=false;
    private int moveWidth = (int) (20*getResources().getDisplayMetrics().density);
    private int width =0;

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


    private void initLayout(final int width){
        this.setHorizontalScrollBarEnabled(false);
        this.setVerticalScrollBarEnabled(false);
        this.setFocusableInTouchMode(true);
        this.setFocusable(true);
        try{
            SwipeListView swipeListView = (SwipeListView) this.getParent().getParent();
            swipeListView.addSwipeListViewScroll(this);
            LinearLayout layout = (LinearLayout) getChildAt(0);
            control = layout.getChildAt(0);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) control.getLayoutParams();
            lp.width = width;
            control.setLayoutParams(lp);
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
        if(control == null)return;
        try{
            SwipeListView swipeListView = (SwipeListView) this.getParent().getParent();
            swipeListView.closeAllSwipeListViewScroll();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        this.smoothScrollTo(control.getWidth(),0);
        isOpen = true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
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
