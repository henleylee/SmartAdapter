package com.henley.smartadapter.recyclerview.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.henley.smartadapter.common.ViewHolder;

/**
 * {@link RecyclerView}的{@link RecyclerView.ViewHolder}实现类
 *
 * @author Henley
 * @date 2017/8/1 16:26
 */
public final class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private final ViewHolder viewHolder;

    public static RecyclerViewHolder createViewHolder(@NonNull Context context, @NonNull View itemView) {
        return new RecyclerViewHolder(context, itemView);
    }

    public static RecyclerViewHolder createViewHolder(@NonNull Context context, @NonNull ViewGroup parent, @LayoutRes int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new RecyclerViewHolder(context, itemView);
    }

    private RecyclerViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
        this.viewHolder = ViewHolder.create(itemView, this);
    }

    public Context getContext() {
        return context;
    }

    public View getItemView() {
        return itemView;
    }

    public ViewHolder getViewHolder() {
        return viewHolder;
    }

}
