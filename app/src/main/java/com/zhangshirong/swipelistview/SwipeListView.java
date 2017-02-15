package com.zhangshirong.swipelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by jarvis on 17-2-15.
 */

public class SwipeListView extends ListView{
    private ArrayList<SwipeListViewScroll> scrolls = new ArrayList<>();
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
    public void addSwipeListViewScroll(SwipeListViewScroll swipeListViewScroll){
        if(scrolls.indexOf(swipeListViewScroll) == -1){
            scrolls.add(swipeListViewScroll);
        }
    }
    public void removeSwipeListViewScroll(SwipeListViewScroll swipeListViewScroll){
        int id = scrolls.indexOf(swipeListViewScroll);
        if(id != -1){
            scrolls.remove(id);
        }
    }
    public void clearSwipeListViewScroll(){
        scrolls.clear();
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        closeAllSwipeListViewScroll();
        return super.onTouchEvent(ev);
    }
}
