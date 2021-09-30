package com.henley.smartadapter.demo.delegate;

import androidx.annotation.NonNull;

import com.henley.smartadapter.common.ViewHolder;
import com.henley.smartadapter.delegate.ItemViewDelegate;
import com.henley.smartadapter.demo.ChatMessage;
import com.henley.smartadapter.demo.R;

/**
 * @author Henley
 * @date 2017/8/2 14:28
 */
public class MessageCommonItemDelagate implements ItemViewDelegate<ChatMessage> {

    @Override
    public int getItemLayoutID() {
        return R.layout.item_chat_msg_common;
    }

    @Override
    public boolean isForViewType(ChatMessage data, int position) {
        return true;
    }

    @Override
    public void convert(@NonNull ViewHolder holder, ChatMessage data, int position) {
        holder.setText(R.id.chat_common_content, data.getContent());
        holder.setText(R.id.chat_common_name, data.getName());
        holder.setImageResource(R.id.chat_common_icon, data.getIcon());
    }
}
