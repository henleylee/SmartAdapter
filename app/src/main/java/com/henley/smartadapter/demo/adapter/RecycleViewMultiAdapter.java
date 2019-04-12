package com.henley.smartadapter.demo.adapter;

import com.henley.smartadapter.demo.ChatMessage;
import com.henley.smartadapter.demo.delegate.MessageReceiveItemDelagate;
import com.henley.smartadapter.demo.delegate.MessageSendItemDelagate;
import com.henley.smartadapter.recycleview.adapter.MultiItemTypeAdapter;

import java.util.Collection;

/**
 * @author Henley
 * @date 2017/8/2 14:41
 */
public class RecycleViewMultiAdapter extends MultiItemTypeAdapter<ChatMessage> {

    public RecycleViewMultiAdapter(Collection<ChatMessage> datas) {
        super(datas);
        addItemViewDelegate(new MessageSendItemDelagate());
        addItemViewDelegate(new MessageReceiveItemDelagate());
    }
}
