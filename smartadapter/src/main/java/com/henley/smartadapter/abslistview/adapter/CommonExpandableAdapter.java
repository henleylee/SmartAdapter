package com.henley.smartadapter.abslistview.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;

import com.henley.smartadapter.abslistview.holder.AbsListViewHolder;
import com.henley.smartadapter.common.IAdapter;
import com.henley.smartadapter.common.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link ExpandableListView}适配器
 *
 * @author Henley
 * @date 2017/9/22 15:20
 */
public abstract class CommonExpandableAdapter<GroupType, DataType> extends BaseExpandableListAdapter implements IAdapter<List<DataType>> {

    /** 上下文 */
    private Context mContext;
    /** 头部数据源 */
    private final List<GroupType> mGroups = new ArrayList<>();
    /** Item数据源 */
    private final List<List<DataType>> mDatas = new ArrayList<>();
    /** Group是否展开状态 */
    private final SparseBooleanArray mGroupExpandedMap = new SparseBooleanArray();

    public CommonExpandableAdapter(List<List<DataType>> datas) {
        if (datas == null) {
            datas = new ArrayList<>(0);
        }
        this.mDatas.addAll(datas);
    }

    public CommonExpandableAdapter(Map<GroupType, List<DataType>> map) {
        if (map == null) {
            map = new HashMap<>(0);
        }
        this.mGroups.addAll(map.keySet());
        this.mDatas.addAll(map.values());
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public List<List<DataType>> getDatas() {
        return mDatas;
    }

    public void refresh(Map<GroupType, List<DataType>> map) {
        this.mGroups.clear();
        this.mDatas.clear();
        this.mGroups.addAll(map.keySet());
        this.mDatas.addAll(map.values());
        notifyDataSetChanged();
    }

    @Override
    public void refresh(List<List<DataType>> datas) {
        if (datas == null) {
            datas = new ArrayList<>(0);
        }
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    public void add(List<DataType> data) {
        this.mDatas.add(data);
        this.notifyDataSetChanged();
    }

    @Override
    public void add(int position, List<DataType> data) {
        this.mDatas.add(position, data);
        this.notifyDataSetChanged();
    }

    @Override
    public void addAll(List<List<DataType>> datas) {
        if (datas == null || datas.isEmpty()) {
            return;
        }
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    public void remove(int position) {
        this.mDatas.remove(position);
        this.notifyDataSetChanged();
    }

    @Override
    public void remove(List<DataType> data) {
        this.mDatas.remove(data);
        this.notifyDataSetChanged();
    }

    @Override
    public void removeAll(List<List<DataType>> datas) {
        if (datas == null || datas.size() == 0) {
            return;
        }
        this.mDatas.removeAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    public void clear() {
        this.mGroups.clear();
        this.mDatas.clear();
        this.notifyDataSetChanged();
    }

    public GroupType getGroupData(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return getGroup(groupPosition) == null ? 0 : getGroup(groupPosition).size();
    }

    @Override
    public List<DataType> getGroup(int groupPosition) {
        return mDatas.get(groupPosition);
    }

    @Override
    public DataType getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition) == null ? null : getGroup(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        int groupLayoutID = getGroupLayoutID(getGroupType(groupPosition));
        AbsListViewHolder viewHolder = AbsListViewHolder.getViewHolder(convertView, parent, groupLayoutID);
        mGroupExpandedMap.put(groupPosition, isExpanded);
        convertGroup(viewHolder.getViewHolder(), getGroupData(groupPosition), getGroup(groupPosition), groupPosition, isExpanded);
        return viewHolder.getConvertView();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        int childLayoutID = getItemLayoutID(getChildType(groupPosition, childPosition));
        AbsListViewHolder viewHolder = AbsListViewHolder.getViewHolder(convertView, parent, childLayoutID);
        convertChild(viewHolder.getViewHolder(), getChild(groupPosition, childPosition), groupPosition, childPosition);
        return viewHolder.getConvertView();
    }

    /**
     * 返回指定Group视图类型对应的视图资源ID
     *
     * @param groupType Group视图类型
     */
    protected int getGroupLayoutID(int groupType) {
        return getGroupLayoutID();
    }

    /**
     * 返回Group视图资源ID
     */
    protected abstract int getGroupLayoutID();

    /**
     * 返回指定Child视图类型对应的视图资源ID
     *
     * @param childType Child视图类型
     */
    @Override
    public int getItemLayoutID(int childType) {
        return getItemLayoutID();
    }

    /**
     * 返回Child视图资源ID
     */
    @Override
    public abstract int getItemLayoutID();

    @Override
    public final void convert(@NonNull ViewHolder holder, List<DataType> childs, int groupPosition) {

    }

    /**
     * Group视图数据和事件绑定
     *
     * @param holder        ViewHolder对象
     * @param group         Group数据
     * @param childs        Group对应的Child数据
     * @param groupPosition Group索引
     */
    protected abstract void convertGroup(@NonNull ViewHolder holder, GroupType group, List<DataType> childs, int groupPosition, boolean isExpanded);

    /**
     * Child视图数据和事件绑定
     *
     * @param holder        ViewHolder对象
     * @param child         Child数据
     * @param groupPosition Group索引
     * @param childPosition Child索引
     */
    protected abstract void convertChild(@NonNull ViewHolder holder, DataType child, int groupPosition, int childPosition);

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
        mGroupExpandedMap.put(groupPosition, true);
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
        mGroupExpandedMap.put(groupPosition, false);
    }

    /**
     * 判断指定位置的Group是否展开
     *
     * @param groupPosition Group索引
     */
    public boolean isGroupExpanded(int groupPosition) {
        return mGroupExpandedMap.get(groupPosition);
    }

}
