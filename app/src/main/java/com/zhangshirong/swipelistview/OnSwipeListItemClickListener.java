package com.zhangshirong.swipelistview;

import android.view.View;

/**
 * Created by jarvis on 17-8-26.
 */

public interface OnSwipeListItemClickListener {
    public void OnClick(View view, int index);
    public boolean OnLongClick(View view, int index);
    public void OnControlClick(int rid,View view,int index);
}
