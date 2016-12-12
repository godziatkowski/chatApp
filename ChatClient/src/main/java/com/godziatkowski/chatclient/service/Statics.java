package com.godziatkowski.chatclient.service;

import com.godziatkowski.chatclient.model.User;
import com.godziatkowski.chatprotocol.Technology;
import java.util.HashSet;
import java.util.Set;

public class Statics {

    private static Technology selectedTechnology = Technology.BURLAP;
    private static User loggedUser;
    private static final Set<Long> JOINDED_CHANNELS = new HashSet<>();

    public static Technology getSelectedTechnology() {
        return selectedTechnology;
    }

    public static void setSelectedTechnology(Technology selectedTechnology) {
        Statics.selectedTechnology = selectedTechnology;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        Statics.loggedUser = loggedUser;
    }

    public static Set<Long> getJoinedChannels() {
        return JOINDED_CHANNELS;
    }
    
    public static void addChannel(long id){
        JOINDED_CHANNELS.add(id);
    }
    
    public static void removeChannel(long id){
        JOINDED_CHANNELS.remove(id);
    }

}
