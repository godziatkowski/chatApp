package com.godziatkowski.chatclient.window;

import com.godziatkowski.chatclient.event.LoginEvent;
import com.godziatkowski.chatclient.panels.AppPanel;
import com.godziatkowski.chatclient.panels.LoginPanel;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MainWindow extends JFrame implements ApplicationListener<LoginEvent> {

    private static final String LOGIN_PANEL = "LOGIN_PANEL";
    private static final String APP_PANEL = "APP_PANEL";

    private final LoginPanel loginPanel;
    private final AppPanel appPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    @Autowired
    public MainWindow(LoginPanel loginPanel, AppPanel appPanel) {
        this.loginPanel = loginPanel;
        this.appPanel = appPanel;
        initialize();
    }

    private void initialize() {
        setSize(800, 800);
        setLocation(600, 150);
        
        this.setLayout(new GridLayout(1, 1));
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        mainPanel.add(loginPanel, LOGIN_PANEL);
        mainPanel.add(appPanel, APP_PANEL);
        cardLayout.show(mainPanel, LOGIN_PANEL);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                appPanel.logout();
                System.exit(0);
            }
        };
        addWindowListener(exitListener);
    }

    @Override
    public void onApplicationEvent(LoginEvent event) {
        cardLayout.show(mainPanel, APP_PANEL);
        appPanel.loadChannels();
    }

}
