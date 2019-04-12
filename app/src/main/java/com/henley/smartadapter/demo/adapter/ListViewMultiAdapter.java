package com.henley.smartadapter.demo.adapter;

import com.henley.smartadapter.abslistview.adapter.MultiItemTypeAdapter;
import com.henley.smartadapter.demo.ChatMessage;
import com.henley.smartadapter.demo.delegate.MessageReceiveItemDelagate;
import com.henley.smartadapter.demo.delegate.MessageSendItemDelagate;

import java.util.Collection;

/**
 * @author Henley
 * @date 2017/8/2 14:39
 */
public class ListViewMultiAdapter extends MultiItemTypeAdapter<ChatMessage> {

    public ListViewMultiAdapter(Collection<ChatMessage> datas) {
        super(datas);
        addItemViewDelegate(new MessageSendItemDelagate());
        addItemViewDelegate(new MessageReceiveItemDelagate());
    }

}
