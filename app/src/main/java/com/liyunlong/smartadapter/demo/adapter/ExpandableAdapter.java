package com.liyunlong.smartadapter.demo.adapter;

import android.graphics.Color;

import com.liyunlong.smartadapter.abslistview.adapter.CommonExpandableAdapter;
import com.liyunlong.smartadapter.common.ViewHolder;
import com.liyunlong.smartadapter.demo.CardLicense;
import com.liyunlong.smartadapter.demo.R;

import java.util.List;
import java.util.Map;

/**
 * @author liyunlong
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
    protected int getChildLayoutID() {
        return R.layout.item_cardlicense_child;
    }

    @Override
    protected void convertGroup(ViewHolder holder, String group, List<CardLicense> childs, int groupPosition) {
        holder.setText(R.id.province, group);
        holder.setText(R.id.child_count, getChildrenCount(groupPosition) + "种");
    }

    @Override
    protected void convertChild(ViewHolder holder, CardLicense child, int groupPosition, int childPosition) {
        holder.setText(R.id.textview, child.getCity() + "(" + child.getCode() + ")");
        holder.setTextColor(R.id.textview, Color.DKGRAY);
    }
}
