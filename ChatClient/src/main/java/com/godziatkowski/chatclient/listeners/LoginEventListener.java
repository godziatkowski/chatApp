package com.godziatkowski.chatclient.listeners;

import com.godziatkowski.chatclient.event.LoginEvent;
import com.godziatkowski.chatclient.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LoginEventListener implements ApplicationListener<LoginEvent> {
    
    @Autowired
    private CommunicationService communicationService;

    @Override
    public void onApplicationEvent(LoginEvent event) {
        communicationService.login(event.getUsername());
    }

}
