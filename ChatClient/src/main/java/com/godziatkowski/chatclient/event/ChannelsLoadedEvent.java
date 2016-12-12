package com.godziatkowski.chatclient.event;

import com.godziatkowski.chatprotocol.ChannelData;
import java.util.List;
import org.springframework.context.ApplicationEvent;

public class ChannelsLoadedEvent extends ApplicationEvent {

    private final List<ChannelData> channels;

    public ChannelsLoadedEvent(Object source, List<ChannelData> channels) {
        super(source);
        this.channels = channels;
    }

    public List<ChannelData> getChannels() {
        return channels;
    }

}
