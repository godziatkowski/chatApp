package com.godziatkowski.service;

import com.godziatkowski.chatprotocol.ChannelData;
import com.godziatkowski.chatprotocol.Message;
import java.util.List;

public interface IChannelService {

    void createNewChannel(String channelName, long creatorId);

    void joinChannel(long channelId, long userId);

    void leaveChannel(long channelId, long userId);

    void messageOnChannel(long chanelId, long authorId, Message message);
    
    List<ChannelData> getChannels();

    void removeUserFromChannels(long userId);
}
