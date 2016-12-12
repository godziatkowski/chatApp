package com.godziatkowski.chatclient.service;

import com.godziatkowski.chatclient.event.MessageOnChannelEvent;
import com.godziatkowski.chatprotocol.ChannelMessages;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageLoaderService {

    private final ApplicationEventPublisher publisher;
    private final CommunicationService communicationService;

    @Autowired
    public MessageLoaderService(CommunicationService communicationService, ApplicationEventPublisher publisher) {
        this.communicationService = communicationService;
        this.publisher = publisher;
    }

    @Scheduled(fixedRate = 1000)
    private void readMessages() {
        if (Statics.getLoggedUser() != null && !Statics.getJoinedChannels().isEmpty()) {
            List<ChannelMessages> messagesPerChannel = communicationService.readMessages();
            messagesPerChannel
                    .forEach(channelMessages -> {
                        MessageOnChannelEvent messageOnChannel = new MessageOnChannelEvent(this, channelMessages.getChannelId(), channelMessages.getMessages());
                        publisher.publishEvent(messageOnChannel);
                    });
        }
    }

}
