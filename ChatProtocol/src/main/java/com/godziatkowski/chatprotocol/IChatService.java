package com.godziatkowski.chatprotocol;

import java.util.List;
import java.util.Map;

public interface IChatService {
    Long login(String username);
    
    void logout(long userId);
    
    void createChannel( String channelName, long userId);
    
    void joinChannel( long channelId, long userId);
    
    void leaveChannel(long channelId, long userId);
    
    List<ChannelData> getChannels();
    
    void sendMessage(long channelId, long authorId, Message message);
    
    Map<Long, List<Message>> getMyMessages(long userId);
    
}
