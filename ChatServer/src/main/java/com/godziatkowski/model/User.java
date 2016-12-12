package com.godziatkowski.model;

import com.godziatkowski.chatprotocol.ChannelMessages;
import com.godziatkowski.chatprotocol.Message;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class User {

    private static final int FIVE_MINUTES = 5;
    private static final Random RANDOM = new Random();
    private static final Set<Long> EXISTING_IDS = new HashSet<>();

    public static void removeId(long id) {
        EXISTING_IDS.remove(id);
    }

    private final long id;
    private final String username;
    private final Map<Long, List<Message>> messagesGroupedByChannelId = new HashMap<>();
    private final Set<Long> userChannels = new HashSet<>();
    private LocalDateTime lastAction;

    public User(String username) {
        long id;
        do {
            id = RANDOM.nextLong();
        } while (EXISTING_IDS.contains(id));
        EXISTING_IDS.add(id);
        this.id = id;
        this.username = username;
        lastAction = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void newMessage(long channelId, Message message) {
        if (messagesGroupedByChannelId.containsKey(channelId)) {
            messagesGroupedByChannelId.get(channelId).add(message);
        } else {
            List<Message> messages = new ArrayList<>();
            messages.add(message);
            messagesGroupedByChannelId.put(channelId, messages);
        }
    }

    public List<ChannelMessages> readMessages() {
        List<ChannelMessages> channelMessages = messagesGroupedByChannelId.entrySet()
                .stream()
                .map(entry -> new ChannelMessages(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        messagesGroupedByChannelId.clear();
        lastAction = LocalDateTime.now();
        return channelMessages;
    }

    public void addUserChannel(long channelId) {
        userChannels.add(channelId);
    }

    public void removeUserChannel(long channelId) {
        userChannels.remove(channelId);
    }

    public Set<Long> getUserChannels() {
        return userChannels;
    }

    public boolean isInactive() {
        long minutes = ChronoUnit.MINUTES.between(lastAction, LocalDateTime.now());
        return minutes < FIVE_MINUTES;
    }

}
