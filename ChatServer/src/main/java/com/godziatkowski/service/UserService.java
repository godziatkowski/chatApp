package com.godziatkowski.service;

import com.godziatkowski.chatprotocol.Message;
import com.godziatkowski.model.User;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final ConcurrentMap<Long, User> users = new ConcurrentHashMap<>();

    @Override
    public long login(String username) {
        User user = new User(username);
        users.put(user.getId(), user);
        return user.getId();
    }

    @Override
    public void logout(long id) {
        users.remove(id);
        User.removeId(id);
    }

    @Override
    public void newMessageForUser(long userId, long channelId, Message message) {
        users.get(userId).newMessage(channelId, message);
    }

    @Override
    public Map<Long, List<Message>> readMessagesForUser(long userId) {
        return users.get(userId).readMessages();
    }

    @Override
    public void addUserChannel(long userId, long channelId) {
        users.get(userId).addUserChannel(channelId);
    }

    @Override
    public void removeUserChannel(long userId, long channelId) {
        users.get(userId).removeUserChannel(channelId);
    }

    @Override
    public Set<Long> getUserChannels(long userId) {
        return users.get(userId).getUserChannels();
    }

}
