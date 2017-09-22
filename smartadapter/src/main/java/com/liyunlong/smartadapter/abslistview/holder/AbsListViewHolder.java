package com.liyunlong.smartadapter.abslistview.holder;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.liyunlong.smartadapter.common.ViewHolder;

/**
 * {@link AbsListView}的ViewHolder实现类
 *
 * @author liyunlong
 * @date 2017/8/1 15:46
 */
public final class AbsListViewHolder {

    private final View mConvertView;
    private final ViewHolder mHolder;

    private AbsListViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
        this.mConvertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
        this.mHolder = ViewHolder.creat(mConvertView);
    }

    /**
     * 返回一个ViewHolder对象
     *
     * @param convertView
     * @param parent
     * @param layoutId
     * @return
     */
    public static AbsListViewHolder getViewHolder(View convertView, ViewGroup parent, @LayoutRes int layoutId) {
        if (convertView == null) {
            return new AbsListViewHolder(parent, layoutId);
        } else {
            return (AbsListViewHolder) convertView.getTag();
        }
    }

    public View getConvertView() {
        return mConvertView;
    }

    public ViewHolder getrViewHolder() {
        return mHolder;
    }
}
