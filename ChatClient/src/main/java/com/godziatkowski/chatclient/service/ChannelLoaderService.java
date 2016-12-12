package com.godziatkowski.chatclient.service;

import com.godziatkowski.chatclient.event.ChannelsLoadedEvent;
import com.godziatkowski.chatclient.event.MessageOnChannelEvent;
import com.godziatkowski.chatprotocol.ChannelData;
import com.godziatkowski.chatprotocol.Message;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ChannelLoaderService {
    
    private final ApplicationEventPublisher publisher;
    private final CommunicationService communicationService;

    @Autowired
    public ChannelLoaderService(CommunicationService communicationService, ApplicationEventPublisher publisher) {
        this.communicationService = communicationService;
        this.publisher = publisher;
    }
    
    @Scheduled(fixedRate = 3000)
    private void readMessages() {
        if (Statics.getLoggedUser() != null) {
            List<ChannelData> channels = communicationService.loadChannels();
            ChannelsLoadedEvent channelsLoadedEvent = new ChannelsLoadedEvent(this, channels);
            publisher.publishEvent(channelsLoadedEvent);
        }
    }
}
