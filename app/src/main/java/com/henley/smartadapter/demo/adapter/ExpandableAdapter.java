package com.henley.smartadapter.demo.adapter;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.henley.smartadapter.abslistview.adapter.CommonExpandableAdapter;
import com.henley.smartadapter.common.ViewHolder;
import com.henley.smartadapter.demo.CardLicense;
import com.henley.smartadapter.demo.R;

import java.util.List;
import java.util.Map;

/**
 * @author Henley
 * @date 2017/9/22 17:13
 */
public class ExpandableAdapter extends CommonExpandableAdapter<String, CardLicense> {

    public ExpandableAdapter(List<List<CardLicense>> datas) {
        super(datas);
    }

    public ExpandableAdapter(Map<String, List<CardLicense>> map) {
        super(map);
    }

    @Override
    protected int getGroupLayoutID() {
        return R.layout.item_cardlicense_group;
    }

    @Override
    public int getItemLayoutID() {
        return R.layout.item_cardlicense_child;
    }

    @Override
    protected void convertGroup(@NonNull ViewHolder holder, String group, List<CardLicense> childs, int groupPosition, boolean isExpanded) {
        holder.setText(R.id.province, group);
        holder.setText(R.id.child_count, getChildrenCount(groupPosition) + "种");
    }

    @Override
    protected void convertChild(@NonNull ViewHolder holder, CardLicense child, int groupPosition, int childPosition) {
        holder.setText(R.id.textview, child.getCity() + "(" + child.getCode() + ")");
        holder.setTextColor(R.id.textview, Color.DKGRAY);
    }
}
