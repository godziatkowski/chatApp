    package com.godziatkowski.chatprotocol;

import java.util.List;

public interface IChatService {
    Long login(String username);
    
    boolean logout(long userId);
    
    ChannelData createChannel( String channelName, long userId);
    
    boolean joinChannel( long channelId, long userId);
    
    boolean leaveChannel(long channelId, long userId);
    
    List<ChannelData> getChannels();
    
    boolean sendMessage(long channelId, long authorId, Message message);
    
    List<ChannelMessages> getMyMessages(long userId);
    
}
