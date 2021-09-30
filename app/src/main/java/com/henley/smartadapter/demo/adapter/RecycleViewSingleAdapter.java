package com.henley.smartadapter.demo.adapter;

import androidx.annotation.NonNull;

import com.henley.smartadapter.common.ViewHolder;
import com.henley.smartadapter.demo.ChatMessage;
import com.henley.smartadapter.demo.R;
import com.henley.smartadapter.recyclerview.adapter.CommonAdapter;

import java.util.Collection;

/**
 * @author Henley
 * @date 2017/8/2 15:08
 */
public class RecycleViewSingleAdapter extends CommonAdapter<ChatMessage> {


    public RecycleViewSingleAdapter(Collection<ChatMessage> datas) {
        super(datas);
    }


    @Override
    public int getItemLayoutID() {
        return R.layout.item_chat_msg_common;
    }

    @Override
    public void convert(@NonNull ViewHolder holder, ChatMessage data, int position) {
        holder.setText(R.id.chat_common_content, data.getContent());
        holder.setText(R.id.chat_common_name, data.getName());
        holder.setImageResource(R.id.chat_common_icon, data.getIcon());
    }
}
