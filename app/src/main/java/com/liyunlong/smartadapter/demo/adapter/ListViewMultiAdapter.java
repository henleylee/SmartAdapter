package com.liyunlong.smartadapter.demo.adapter;

import com.liyunlong.smartadapter.abslistview.adapter.MultiItemTypeAdapter;
import com.liyunlong.smartadapter.demo.ChatMessage;
import com.liyunlong.smartadapter.demo.delegate.MessageReceiveItemDelagate;
import com.liyunlong.smartadapter.demo.delegate.MessageSendItemDelagate;

import java.util.Collection;

/**
 * @author liyunlong
 * @date 2017/8/2 14:39
 */
public class ListViewMultiAdapter extends MultiItemTypeAdapter<ChatMessage> {

    public ListViewMultiAdapter(Collection<ChatMessage> datas) {
        super(datas);
        addItemViewDelegate(new MessageSendItemDelagate());
        addItemViewDelegate(new MessageReceiveItemDelagate());
    }

}
