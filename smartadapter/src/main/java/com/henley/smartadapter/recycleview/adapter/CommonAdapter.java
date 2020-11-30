package com.henley.smartadapter.recycleview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.henley.smartadapter.common.IRecycleViewAdapter;
import com.henley.smartadapter.recycleview.holder.RecyclerViewHolder;
import com.henley.smartadapter.recycleview.listener.OnItemClickListener;
import com.henley.smartadapter.recycleview.listener.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * {@link RecyclerView}适配器
 *
 * @param <DataType> 数据类型的泛型
 * @author Henley
 * @date 2017/8/1 16:25
 */
public abstract class CommonAdapter<DataType> extends RecyclerView.Adapter<RecyclerViewHolder> implements IRecycleViewAdapter<DataType> {

    /** 上下文 */
    private Context mContext;
    /** 数据源 */
    private final List<DataType> mDatas = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public CommonAdapter(Collection<DataType> datas) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        this.mDatas.addAll(datas);
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public List<DataType> getDatas() {
        return mDatas;
    }

    @Override
    public void refresh(List<DataType> datas) {
        if (datas == null) {
            datas = new ArrayList<>(0);
        }
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    public void add(DataType data) {
        this.mDatas.add(data);
        this.notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public void add(int position, DataType data) {
        this.mDatas.add(position, data);
        this.notifyItemInserted(position);
    }

    @Override
    public void addAll(List<DataType> datas) {
        if (datas == null || datas.isEmpty()) {
            return;
        }
        int itemCount = getItemCount();
        this.mDatas.addAll(datas);
        this.notifyItemRangeInserted(itemCount, datas.size());
    }

    @Override
    public void remove(int position) {
        this.mDatas.remove(position);
        this.notifyItemRemoved(getItemCount() - 1);
    }

    @Override
    public void remove(DataType data) {
        int indexToRemove = mDatas.indexOf(data);
        this.mDatas.remove(indexToRemove);
        this.notifyItemRemoved(indexToRemove);
    }

    @Override
    public void removeAll(List<DataType> datas) {
        if (datas == null || datas.size() == 0) {
            return;
        }
        this.mDatas.removeAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    public void clear() {
        this.mDatas.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    @Override
    public DataType getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        int itemLayoutID = getItemLayoutID(viewType);
        return RecyclerViewHolder.createViewHolder(mContext, parent, itemLayoutID);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        final int finalPosition = holder.getAdapterPosition();
        setItemViewListener(holder, finalPosition);
        convert(holder.getViewHolder(), getItem(position), finalPosition);
    }

    /**
     * 设置ItemView的点击事件和长按事件监听
     *
     * @param holder   {@link RecyclerViewHolder}对象
     * @param position 点击的Item的索引
     */
    private void setItemViewListener(@NonNull final RecyclerViewHolder holder, final int position) {
        if (!areAllItemsEnabled() && !isEnabled(position)) {
            return;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, holder, position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    return mOnItemLongClickListener.onItemLongClick(v, holder, position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return getItemLayoutID();
    }

}
