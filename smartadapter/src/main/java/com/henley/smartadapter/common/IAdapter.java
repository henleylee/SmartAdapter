package com.henley.smartadapter.common;

import android.content.Context;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import java.util.List;

/**
 * Adapter数据操作接口
 *
 * @author Henley
 * @date 2017/8/1 17:21
 */
public interface IAdapter<DataType> {

    /**
     * 返回{@link Context}对象
     */
    Context getContext();

    /**
     * 返回数据源
     */
    List<DataType> getDatas();

    /**
     * 刷新数据源
     */
    void refresh(List<DataType> datas);

    /**
     * 添加数据
     */
    void add(DataType data);

    /**
     * 添加数据到指定位置
     */
    void add(int position, DataType data);

    /**
     * 添加数据集
     */
    void addAll(List<DataType> datas);

    /**
     * 移除指定位置的数据
     */
    void remove(int position);

    /**
     * 移除指定数据
     */
    void remove(DataType data);

    /**
     * 移除指定数据集
     */
    void removeAll(List<DataType> datas);

    /**
     * 清空数据源
     */
    void clear();

    /**
     * 返回Item布局资源ID(用于多类型ItemView)
     */
    @LayoutRes
    int getItemLayoutID(int viewType);

    /**
     * 返回Item布局资源ID(用于单类型ItemView)
     */
    @LayoutRes
    int getItemLayoutID();

    /**
     * 数据和事件绑定
     */
    void convert(@NonNull ViewHolder holder, DataType data, int position);

}
