package com.liyunlong.smartadapter.demo.adapter;

import com.liyunlong.smartadapter.abslistview.adapter.CommonAdapter;
import com.liyunlong.smartadapter.common.ViewHolder;
import com.liyunlong.smartadapter.demo.ChatMessage;
import com.liyunlong.smartadapter.demo.R;

import java.util.Collection;

/**
 * @author liyunlong
 * @date 2017/8/2 15:06
 */
public class ListViewSingleAdapter extends CommonAdapter<ChatMessage> {

    public ListViewSingleAdapter(Collection<ChatMessage> datas) {
        super(datas);
    }


    @Override
    public int getItemLayoutID() {
        return R.layout.item_chat_msg_common;
    }

    @Override
    public void convert(ViewHolder holder, ChatMessage data, int position) {
        holder.setText(R.id.chat_common_content, data.getContent());
        holder.setText(R.id.chat_common_name, data.getName());
        holder.setImageResource(R.id.chat_common_icon, data.getIcon());
    }

}
