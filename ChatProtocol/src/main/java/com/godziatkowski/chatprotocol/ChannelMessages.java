package com.godziatkowski.chatprotocol;

import java.io.Serializable;
import java.util.List;

public class ChannelMessages implements Serializable{

    private final Long channelId;
    private final List<Message> messages;

    public ChannelMessages(Long channelId, List<Message> messages) {
        this.channelId = channelId;
        this.messages = messages;
    }

    public Long getChannelId() {
        return channelId;
    }

    public List<Message> getMessages() {
        return messages;
    }

}
