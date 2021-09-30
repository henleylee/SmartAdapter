package com.henley.smartadapter.abslistview.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.henley.smartadapter.common.ViewHolder;

/**
 * {@link AbsListView}的ViewHolder实现类
 *
 * @author Henley
 * @date 2017/8/1 15:46
 */
public final class AbsListViewHolder {

    private final View convertView;
    private final ViewHolder viewHolder;

    /**
     * 返回一个{@link ViewHolder}对象
     */
    public static AbsListViewHolder getViewHolder(@Nullable View convertView, @NonNull ViewGroup parent, @LayoutRes int layoutId) {
        if (convertView == null) {
            return new AbsListViewHolder(parent, layoutId);
        } else {
            return (AbsListViewHolder) convertView.getTag();
        }
    }

    private AbsListViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        this.convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        this.convertView.setTag(this);
        this.viewHolder = ViewHolder.create(convertView, this);
    }

    public View getConvertView() {
        return convertView;
    }

    public ViewHolder getViewHolder() {
        return viewHolder;
    }
}
