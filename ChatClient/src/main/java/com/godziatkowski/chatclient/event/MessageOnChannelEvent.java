package com.godziatkowski.chatclient.event;

import com.godziatkowski.chatprotocol.Message;
import java.util.List;
import org.springframework.context.ApplicationEvent;

public class MessageOnChannelEvent extends ApplicationEvent {

    private final List<Message> messages;
    private final long channelId;

    public MessageOnChannelEvent(Object source, long channelId, List<Message> messages) {
        super(source);
        this.channelId = channelId;
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public long getChannelId() {
        return channelId;
    }

}
