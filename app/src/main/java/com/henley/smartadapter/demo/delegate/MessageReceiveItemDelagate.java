package com.henley.smartadapter.demo.delegate;

import com.henley.smartadapter.common.ViewHolder;
import com.henley.smartadapter.delegate.ItemViewDelegate;
import com.henley.smartadapter.demo.ChatMessage;
import com.henley.smartadapter.demo.R;

/**
 * @author Henley
 * @date 2017/8/2 14:35
 */
public class MessageReceiveItemDelagate implements ItemViewDelegate<ChatMessage> {

    @Override
    public int getItemLayoutID() {
        return R.layout.item_chat_msg_receive;
    }

    @Override
    public boolean isForViewType(ChatMessage data, int position) {
        return data.getMessageType() == ChatMessage.TYPE_MESSAGE_RECIEVE;
    }

    @Override
    public void convert(ViewHolder holder, ChatMessage data, int position) {
        holder.setText(R.id.chat_from_content, data.getContent());
        holder.setText(R.id.chat_from_name, data.getName());
        holder.setImageResource(R.id.chat_from_icon, data.getIcon());
    }
}
