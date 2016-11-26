package com.godziatkowski.chatprotocol;

import java.io.Serializable;

public class Message implements Serializable {

    private final String message;
    private final String author;

    public Message(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

}
