package com.godziatkowski.service;

import com.godziatkowski.chatprotocol.ChannelData;
import com.godziatkowski.chatprotocol.Message;
import com.godziatkowski.model.Channel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelService implements IChannelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelService.class);
    private final IUserService userService;
    private final Map<Long, Channel> channels = new HashMap<>();

    @Autowired
    public ChannelService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ChannelData createNewChannel(String channelName, long creatorId) {
        Channel channel = new Channel(channelName, creatorId);
        channels.put(channel.getId(), channel);
        LOGGER.debug("Channel <{}> with id <{}> created", channel.getName(), channel.getId());
        return channel.toChannelData();
    }

    @Override
    public void joinChannel(long channelId, long userId) {
        channels.get(channelId).addUserToChannel(userId);
        LOGGER.debug("User with id <{}> joined channel with id <{}>", userId, channelId);
    }

    @Override
    public void leaveChannel(long channelId, long userId) {
        channels.get(channelId).removeUserFromChannel(userId);
        LOGGER.debug("User with id <{}> left channel with id <{}>", userId, channelId);
    }

    @Override
    public void messageOnChannel(long channelId, long authorId, Message message) {
        LOGGER.debug("New message on channel <{}> send by <{}>", channelId, authorId);
        channels.get(channelId)
                .getUsersOnChannel()
                .forEach(userId -> {
                    LOGGER.debug("New message for user <{}>", userId);
                    userService.newMessageForUser(userId, channelId, message);
                });
    }

    @Override
    public List<ChannelData> getChannels() {
        LOGGER.debug("Get for channels - returning ChannelData of <{}> channels", channels.size());
        return channels.values()
                .stream()
                .map(Channel::toChannelData)
                .collect(Collectors.toList());
    }

    @Override
    public void removeUserFromChannels(long userId) {
        LOGGER.debug("Removing user <{}> from channels", userId);
        userService.getUserChannels(userId)
                .stream()
                .forEach(channelId -> {
                    leaveChannel(channelId, userId);
                });
    }

    @Override
    public Set<Long> findEmptyChannels() {
        return channels.values()
                .stream()
                .filter(channel -> channel.getUsersOnChannel().isEmpty())
                .map(Channel::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public void closeChannel(Long channelId) {
        LOGGER.info("Clossing channel <{}>", channelId);
        channels.remove(channelId);
    }

}
