package com.godziatkowski.service;

import com.godziatkowski.chatprotocol.ChannelData;
import com.godziatkowski.model.Channel;
import com.godziatkowski.chatprotocol.Message;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelService implements IChannelService {

    private final IUserService userService;
    private final ConcurrentMap<Long, Channel> channels = new ConcurrentHashMap<>();

    @Autowired
    public ChannelService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void createNewChannel(String channelName, long creatorId) {
        Channel channel = new Channel(channelName, creatorId);
        channels.put(channel.getId(), channel);
    }

    @Override
    public void joinChannel(long channelId, long userId) {
        channels.get(channelId).addUserToChannel(userId);
    }

    @Override
    public void leaveChannel(long channelId, long userId) {
        channels.get(channelId).removeUserFromChannel(userId);
        if (channels.get(channelId).isEmpty()) {
            channels.remove(channelId);
        }
    }

    @Override
    public void messageOnChannel(long channelId, long authorId, Message message) {
        channels.get(channelId)
                .getUsersOnChannel()
                .forEach(userId -> {
                    if (authorId != userId) {
                        userService.newMessageForUser(userId, channelId, message);
                    }
                });
    }

    @Override
    public List<ChannelData> getChannels() {
        return channels.values()
                .stream()
                .map(channel -> new ChannelData(channel.getId(), channel.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void removeUserFromChannels(long userId) {
        userService.getUserChannels(userId)
                .stream()
                .forEach(channelId -> {
                    leaveChannel(channelId, userId);
                });
    }

}
