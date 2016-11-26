package com.godziatkowski.hessian;

import com.godziatkowski.chatprotocol.IChatService;
import org.springframework.stereotype.Service;

@Service
public class HessianService implements IChatService{

    @Override
    public String login() {
        System.out.println("hessian login");
        return "hessian login";
    }

    @Override
    public String sendMessage() {
        System.out.println("hessian send message");
        return "hessian send message";
    }
    
}
