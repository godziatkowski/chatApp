package com.godziatkowski.service;

import com.godziatkowski.chatprotocol.ChannelData;
import com.godziatkowski.chatprotocol.ChannelMessages;
import com.godziatkowski.chatprotocol.IChatService;
import com.godziatkowski.chatprotocol.Message;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements IChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);

    private final IChannelService channelService;
    private final IUserService userService;

    @Autowired
    public ChatService(IChannelService channelService, IUserService userService) {
        this.channelService = channelService;
        this.userService = userService;
    }

    @Override
    public Long login(String username) {
        LOGGER.info("user logging in <{}>", username);
        return userService.login(username);
    }

    @Override
    public boolean logout(long userId) {
        LOGGER.info("user logging out <{}>", userId);
        channelService.removeUserFromChannels(userId);
        userService.logout(userId);
        return true;
    }

    @Override
    public ChannelData createChannel(String channelName, long userId) {
        LOGGER.info("User <{}> created channel <{}>", userId, channelName);
        return channelService.createNewChannel(channelName, userId);
    }

    @Override
    public boolean joinChannel(long channelId, long userId) {
        LOGGER.info("user <{}> joining channel <{}>", userId, channelId);
        channelService.joinChannel(channelId, userId);
        return true;
    }

    @Override
    public boolean leaveChannel(long channelId, long userId) {
        LOGGER.info("user <{}> leaving channel <{}>", userId, channelId);
        channelService.leaveChannel(channelId, userId);
        return true;
    }

    @Override
    public List<ChannelData> getChannels() {
        LOGGER.debug("Get for channels");
        return channelService.getChannels();
    }

    @Override
    public boolean sendMessage(long channelId, long authorId, Message message) {
        LOGGER.info("User <{}> (with id <{}>) send new message <{}> on channel <{}>", message.getAuthor(), authorId, message.getMessage(), channelId);
        channelService.messageOnChannel(channelId, authorId, message);
        return true;
    }

    @Override
    public List<ChannelMessages> getMyMessages(long userId) {
        LOGGER.info("Returning messages for user <{}>", userId);
        return userService.readMessagesForUser(userId);
    }

}
