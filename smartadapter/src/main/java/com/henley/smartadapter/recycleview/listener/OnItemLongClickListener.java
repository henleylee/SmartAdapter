package com.henley.smartadapter.recycleview.listener;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * {@link RecyclerView}的Item长点击事件监听
 *
 * @author Henley
 * @date 2017/8/1 17:01
 */
public interface OnItemLongClickListener {

    boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);

}
