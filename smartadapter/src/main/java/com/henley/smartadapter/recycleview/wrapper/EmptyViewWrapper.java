package com.henley.smartadapter.recycleview.wrapper;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.henley.smartadapter.recycleview.holder.RecyclerViewHolder;

/**
 * 可以设置无数据{@link View}的适配器包装类
 *
 * @author Henley
 * @date 2017/8/3 13:39
 */
public class EmptyViewWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TYPE_EMPTY = Integer.MAX_VALUE - 1;
    private View mEmptyView;
    private int mEmptyLayoutId;
    private RecyclerView.Adapter mInnerAdapter;

    public EmptyViewWrapper(@NonNull RecyclerView.Adapter innerAdapter) {
        this.mInnerAdapter = innerAdapter;
    }

    /**
     * 设置无数据{@link View}
     *
     * @param emptyView 无数据{@link View}对象
     */
    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
        notifyDataSetChanged();
    }

    /**
     * 设置无数据{@link View}的资源ID
     *
     * @param layoutId 无数据{@link View}的资源ID
     */
    public void setEmptyView(@LayoutRes int layoutId) {
        mEmptyLayoutId = layoutId;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (isEmpty()) {
            return 1;
        }
        return mInnerAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isEmpty()) {
            return ITEM_TYPE_EMPTY;
        }
        return mInnerAdapter.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (isEmpty()) {
            RecyclerViewHolder holder;
            if (mEmptyView != null) {
                holder = RecyclerViewHolder.createViewHolder(parent.getContext(), mEmptyView);
            } else {
                holder = RecyclerViewHolder.createViewHolder(parent.getContext(), parent, mEmptyLayoutId);
            }
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isEmpty()) {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        WrapperHelper.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperHelper.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (isEmpty()) {
                    return gridLayoutManager.getSpanCount();
                }
                if (oldLookup != null) {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        mInnerAdapter.onViewAttachedToWindow(holder);
        if (isEmpty()) {
            WrapperHelper.setFullSpan(holder);
        }
    }

    private boolean isEmpty() {
        return (mEmptyView != null || mEmptyLayoutId != 0) && mInnerAdapter.getItemCount() == 0;
    }
}
