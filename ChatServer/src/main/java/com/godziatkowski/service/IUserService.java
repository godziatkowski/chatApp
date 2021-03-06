package com.godziatkowski.service;

import com.godziatkowski.chatprotocol.ChannelMessages;
import com.godziatkowski.chatprotocol.Message;
import java.util.List;
import java.util.Set;

public interface IUserService {

    long login(String username);

    void logout(long id);

    void newMessageForUser(long userId, long channelId, Message message);

    List<ChannelMessages> readMessagesForUser(long userId);
    
    void addUserChannel(long userId, long channelId);
    
    void removeUserChannel(long userId, long channelId);
    
    Set<Long> getUserChannels(long userId);

    Set<Long> findInactiveUsers();
    
}
