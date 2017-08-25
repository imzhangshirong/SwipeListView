package com.zhangshirong.swipelistview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jarvis on 17-2-15.
 */

public class SwipeListView extends ListView{
    private ArrayList<SwipeListViewScroll> scrolls = new ArrayList<>();
    protected int[] controlIds;
    protected OnSwipeListItemClickListener listener;
    public SwipeListView(Context context) {
        super(context);
        setMotionEventSplittingEnabled(false);
    }

    public SwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setMotionEventSplittingEnabled(false);
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setMotionEventSplittingEnabled(false);
    }
    public void closeAllSwipeListViewScroll(){
        try {
            for(int a=0;a<scrolls.size();a++){
                if(scrolls.get(a)!=null){
                    scrolls.get(a).close();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void addSwipeListViewScroll(SwipeListViewScroll swipeListViewScroll){
        if(scrolls.indexOf(swipeListViewScroll) == -1){
            scrolls.add(swipeListViewScroll);
        }
    }
    protected void removeSwipeListViewScroll(SwipeListViewScroll swipeListViewScroll){
        int id = scrolls.indexOf(swipeListViewScroll);
        if(id != -1){
            scrolls.remove(id);
        }
    }
    protected void clearSwipeListViewScroll(){
        scrolls.clear();
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        closeAllSwipeListViewScroll();
        return super.onTouchEvent(ev);
    }

    public void setListener(OnSwipeListItemClickListener onClickListener,int[] controlIds){
        this.controlIds = controlIds;
        listener = onClickListener;
    }
}
