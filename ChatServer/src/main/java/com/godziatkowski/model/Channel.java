package com.godziatkowski.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Channel {

    private static final Random RANDOM = new Random();
    private static final Set<Long> IDS = new HashSet<>();

    private final long id;
    private final String name;
    private final Set<Long> usersOnChannel = new HashSet<>();

    public Channel(String name, long creatorId) {
        long id;
        do {
            id = RANDOM.nextLong();
        } while (IDS.contains(id));
        IDS.add(id);
        this.id = id;
        this.name = name;
        usersOnChannel.add(creatorId);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Long> getUsersOnChannel() {
        return usersOnChannel;
    }

    public void addUserToChannel(long userId) {
        usersOnChannel.add(userId);
    }

    public void removeUserFromChannel(long userId) {
        usersOnChannel.remove(userId);
    }

    public boolean isEmpty() {
        return usersOnChannel.isEmpty();
    }

}
