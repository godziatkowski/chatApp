package com.godziatkowski.chatprotocol;

import java.io.Serializable;

public class ChannelData implements Serializable {

    private final Long id;
    private final String name;

    public ChannelData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
