package com.henley.smartadapter.recycleview.listener;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * {@link RecyclerView}的Item长点击事件监听
 *
 * @author Henley
 * @date 2017/8/1 17:01
 */
public interface OnItemLongClickListener {

    boolean onItemLongClick(@NonNull View view, @NonNull RecyclerView.ViewHolder holder, int position);

}
