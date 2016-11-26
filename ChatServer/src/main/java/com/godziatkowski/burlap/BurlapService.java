package com.godziatkowski.burlap;

import com.godziatkowski.chatprotocol.IChatService;
import org.springframework.stereotype.Service;

@Service
public class BurlapService implements IChatService{

    @Override
    public String login() {
        System.out.println("burlap login");
        return "burlap login";
    }

    @Override
    public String sendMessage() {
        System.out.println("burlap send message");
        return "burlap send message";
    }

}
