package com.liyunlong.smartadapter.demo.adapter;

import com.liyunlong.smartadapter.demo.ChatMessage;
import com.liyunlong.smartadapter.demo.delegate.MessageReceiveItemDelagate;
import com.liyunlong.smartadapter.demo.delegate.MessageSendItemDelagate;
import com.liyunlong.smartadapter.recycleview.adapter.MultiItemTypeAdapter;

import java.util.Collection;

/**
 * @author liyunlong
 * @date 2017/8/2 14:41
 */
public class RecycleViewMultiAdapter extends MultiItemTypeAdapter<ChatMessage> {

    public RecycleViewMultiAdapter(Collection<ChatMessage> datas) {
        super(datas);
        addItemViewDelegate(new MessageSendItemDelagate());
        addItemViewDelegate(new MessageReceiveItemDelagate());
    }
}
