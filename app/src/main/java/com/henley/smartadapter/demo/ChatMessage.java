package com.henley.smartadapter.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henley
 * @date 2017/8/2 14:14
 */
public class ChatMessage {

    private int icon;
    private String name;
    private String content;
    private String createDate;
    private int messageType;

    public final static int TYPE_MESSAGE_RECIEVE = 0;
    public final static int TYPE_MESSAGE_SEND = 1;

    public ChatMessage(int icon, String name, String content, String createDate, int messageType) {
        this.icon = icon;
        this.name = name;
        this.content = content;
        this.createDate = createDate;
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "ChatMessage [icon=" + icon + ", name=" + name + ", content="
                + content + ", createDate=" + createDate + ", messageType = " + getMessageType() + "]";
    }

    public static final List<ChatMessage> MOCK_DATAS = new ArrayList<>();

    static {
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header2, "header2", "where are you?", null, TYPE_MESSAGE_RECIEVE));
        MOCK_DATAS.add(new ChatMessage(R.drawable.header1, "header1", "where are you?", null, TYPE_MESSAGE_SEND));
    }
}
