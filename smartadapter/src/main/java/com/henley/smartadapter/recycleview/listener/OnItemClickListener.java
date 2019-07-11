package com.henley.smartadapter.recycleview.listener;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * {@link RecyclerView}的Item点击事件监听
 *
 * @author Henley
 * @date 2017/8/1 17:00
 */
public interface OnItemClickListener {

    void onItemClick(@NonNull View view, @NonNull RecyclerView.ViewHolder holder, int position);
}
