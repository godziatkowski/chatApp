package com.godziatkowski.chatclient.service;

import com.godziatkowski.chatclient.model.User;
import com.godziatkowski.chatprotocol.Technology;

public class Statics {

    private static Technology selectedTechnology = Technology.BURLAP;
    private static User loggedUser;

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

}
