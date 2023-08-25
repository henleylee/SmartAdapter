package com.henley.smartadapter.recyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.henley.smartadapter.common.IRecycleViewAdapter;
import com.henley.smartadapter.recyclerview.holder.RecyclerViewHolder;
import com.henley.smartadapter.recyclerview.listener.OnItemClickListener;
import com.henley.smartadapter.recyclerview.listener.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * {@link RecyclerView}适配器
 *
 * @param <DataType> 数据类型的泛型
 * @author Henley
 * @date 2017/8/1 16:25
 */
public abstract class CommonAdapter<DataType> extends RecyclerView.Adapter<RecyclerViewHolder> implements IRecycleViewAdapter<DataType> {

    /** 上下文 */
    private Context context;
    /** 数据源 */
    private final List<DataType> datas = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public CommonAdapter(Collection<DataType> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.datas.addAll(data);
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public List<DataType> getDatas() {
        return datas;
    }

    @Override
    public void refresh(List<DataType> data) {
        if (data == null) {
            data = new ArrayList<>(0);
        }
        this.datas.clear();
        this.datas.addAll(data);
        this.notifyDataSetChanged();
    }

    @Override
    public void add(DataType data) {
        if (this.datas.add(data)) {
            this.notifyItemInserted(getItemCount() - 1);
        }
    }

    @Override
    public void add(int position, DataType data) {
        this.datas.add(position, data);
        this.notifyItemInserted(position);
    }

    @Override
    public void addAll(List<DataType> data) {
        if (data == null || data.isEmpty()) {
            return;
        }
        if (this.datas.addAll(data)) {
            int itemCount = getItemCount();
            this.notifyItemRangeInserted(itemCount, data.size());
        }
    }

    @Override
    public void remove(int position) {
        if (position >= 0 && position <= getItemCount() - 1) {
            this.datas.remove(position);
            this.notifyItemRemoved(position);
        }
    }

    @Override
    public void remove(DataType data) {
        int indexToRemove = datas.indexOf(data);
        if (indexToRemove != -1) {
            this.datas.remove(indexToRemove);
            this.notifyItemRemoved(indexToRemove);
        }
    }

    @Override
    public void removeAll(List<DataType> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        int size = datas.size();
        this.datas.removeAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        int size = datas.size();
        this.datas.clear();
        notifyItemRangeRemoved(0, size);
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
        this.onItemClickListener = listener;
    }

    @Override
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }

    @Override
    public DataType getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        int itemLayoutID = getItemLayoutID(viewType);
        return RecyclerViewHolder.createViewHolder(context, parent, itemLayoutID);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        final int finalPosition = holder.getBindingAdapterPosition();
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
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, holder, position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    return onItemLongClickListener.onItemLongClick(v, holder, position);
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
