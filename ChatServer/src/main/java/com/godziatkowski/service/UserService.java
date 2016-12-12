package com.godziatkowski.service;

import com.godziatkowski.chatprotocol.ChannelMessages;
import com.godziatkowski.chatprotocol.Message;
import com.godziatkowski.model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public long login(String username) {
        User user = new User(username);
        users.put(user.getId(), user);
        LOGGER.debug("Created user <{}>, <{}>", user.getId(), user.getUsername());
        return user.getId();
    }

    @Override
    public void logout(long id) {
        users.remove(id);
        User.removeId(id);
        LOGGER.debug("Removed user <{}>", id);
    }

    @Override
    public void newMessageForUser(long userId, long channelId, Message message) {
        LOGGER.debug("New message for user <{}> on channel <{}>", userId, channelId);
        users.get(userId).newMessage(channelId, message);
    }

    @Override
    public List<ChannelMessages> readMessagesForUser(long userId) {
        LOGGER.debug("Reading messages for user <{}>", userId);
        return users.get(userId).readMessages();
    }

    @Override
    public void addUserChannel(long userId, long channelId) {
        LOGGER.debug("User <{}> joined channel <{}>", userId, channelId);
        users.get(userId).addUserChannel(channelId);
    }

    @Override
    public void removeUserChannel(long userId, long channelId) {
        LOGGER.debug("Removing channel <{}> for user <{}>", channelId, userId);
        users.get(userId).removeUserChannel(channelId);
    }

    @Override
    public Set<Long> getUserChannels(long userId) {
        return users.get(userId).getUserChannels();
    }

    @Override
    public Set<Long> findInactiveUsers() {
        return users.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isInactive())
                .map(entry -> entry.getKey())
                .collect(Collectors.toSet());
    }

}
