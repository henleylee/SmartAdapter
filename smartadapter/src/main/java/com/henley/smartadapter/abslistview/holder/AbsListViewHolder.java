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

    private final View mConvertView;
    private final ViewHolder mHolder;

    /**
     * 返回一个ViewHolder对象
     *
     * @param convertView
     * @param parent
     * @param layoutId
     * @return
     */
    public static AbsListViewHolder getViewHolder(@Nullable View convertView, @NonNull ViewGroup parent, @LayoutRes int layoutId) {
        if (convertView == null) {
            return new AbsListViewHolder(parent, layoutId);
        } else {
            return (AbsListViewHolder) convertView.getTag();
        }
    }

    private AbsListViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        this.mConvertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
        this.mHolder = ViewHolder.creat(mConvertView);
    }

    public View getConvertView() {
        return mConvertView;
    }

    public ViewHolder getViewHolder() {
        return mHolder;
    }
}
