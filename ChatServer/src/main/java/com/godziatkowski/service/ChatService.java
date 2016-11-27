package com.godziatkowski.service;

import com.godziatkowski.chatprotocol.ChannelData;
import com.godziatkowski.chatprotocol.IChatService;
import com.godziatkowski.chatprotocol.Message;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements IChatService {

    private final IChannelService channelService;
    private final IUserService userService;

    @Autowired
    public ChatService(IChannelService channelService, IUserService userService) {
        this.channelService = channelService;
        this.userService = userService;
    }

    @Override
    public Long login(String username) {
        return userService.login(username);
    }

    @Override
    public void logout(long userId) {
        channelService.removeUserFromChannels(userId);
        userService.logout(userId);
    }

    @Override
    public void joinChannel(long channelId, long userId) {
        channelService.joinChannel(channelId, userId);
    }

    @Override
    public void leaveChannel(long channelId, long userId) {
        channelService.leaveChannel(channelId, userId);
    }

    @Override
    public List<ChannelData> getChannels() {
        return channelService.getChannels();
    }

    @Override
    public void sendMessage(long channelId, long authorId, Message message) {
        channelService.messageOnChannel(channelId, authorId, message);
    }

    @Override
    public Map<Long, List<Message>> getMyMessages(long userId) {
        return userService.readMessagesForUser(userId);
    }

    @Override
    public void createChannel(String channelName, long userId) {
        channelService.createNewChannel(channelName, userId);
    }

}
