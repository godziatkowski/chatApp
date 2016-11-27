package com.godziatkowski.chatclient.service;

import com.godziatkowski.chatclient.window.MainWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private MainWindow frame;
    
    @Override
    public void run(String... args) throws Exception {
        java.awt.EventQueue.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

}
