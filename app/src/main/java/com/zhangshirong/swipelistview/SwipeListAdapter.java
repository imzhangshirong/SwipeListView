package com.zhangshirong.swipelistview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by jarvis on 17-8-26.
 */

public class SwipeListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
    protected View bindView(int position, View view){
        ViewGroup viewGroup = (ViewGroup) view;
        SwipeListViewScroll swipeListViewScroll = (SwipeListViewScroll) viewGroup.getChildAt(0);
        swipeListViewScroll.setIndex(position);
        return view;
    }
}
