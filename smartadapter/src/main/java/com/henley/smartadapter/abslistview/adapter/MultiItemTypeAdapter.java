package com.henley.smartadapter.abslistview.adapter;

import android.widget.AbsListView;

import androidx.annotation.NonNull;

import com.henley.smartadapter.common.ViewHolder;
import com.henley.smartadapter.delegate.IAdapterDelegate;
import com.henley.smartadapter.delegate.ItemViewDelegate;
import com.henley.smartadapter.delegate.ItemViewDelegateManager;

import java.util.Collection;

/**
 * {@link AbsListView}适配器(用于多种ItemView类型)
 *
 * @param <DataType> 数据类型的泛型
 * @author Henley
 * @date 2017/8/2 11:17
 */
public class MultiItemTypeAdapter<DataType> extends CommonAdapter<DataType> implements IAdapterDelegate<DataType> {

    private final ItemViewDelegateManager<DataType> mItemViewDelegateManager = new ItemViewDelegateManager<>();

    public MultiItemTypeAdapter(Collection<DataType> datas) {
        super(datas);
    }

    @Override
    public void addItemViewDelegate(@NonNull ItemViewDelegate<DataType> delegate) {
        mItemViewDelegateManager.addDelegate(delegate);
    }

    @Override
    public void addItemViewDelegate(int viewType, @NonNull ItemViewDelegate<DataType> delegate) {
        mItemViewDelegateManager.addDelegate(viewType, delegate);
    }

    @Override
    public ItemViewDelegateManager<DataType> removeDelegate(int itemType) {
        return mItemViewDelegateManager.removeDelegate(itemType);
    }

    @Override
    public ItemViewDelegateManager<DataType> removeDelegate(@NonNull ItemViewDelegate<DataType> delegate) {
        return mItemViewDelegateManager.removeDelegate(delegate);
    }

    @Override
    public boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (useItemViewDelegateManager()) {
            return mItemViewDelegateManager.getItemViewType(getItem(position), position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        if (useItemViewDelegateManager()) {
            return mItemViewDelegateManager.getItemViewDelegateCount();
        }
        return super.getViewTypeCount();
    }

    @Override
    public int getItemLayoutID(int viewType) {
        if (useItemViewDelegateManager()) {
            return mItemViewDelegateManager.getItemViewLayoutId(viewType);
        }
        return getItemLayoutID();
    }

    @Override
    public int getItemLayoutID() {
        return 0;
    }

    @Override
    public void convert(@NonNull ViewHolder holder, DataType data, int position) {
        mItemViewDelegateManager.convert(holder, data, position);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();
    }
}
