package com.godziatkowski.chatclient;

import com.godziatkowski.chatclient.burlap.BurlapClient;
import com.godziatkowski.chatclient.hessian.HessianClient;
import com.godziatkowski.chatprotocol.IChatService;
import com.godziatkowski.chatprotocol.Technology;
import java.util.HashMap;
import java.util.Map;

public class ClientApp {

    private static final Map<Technology, IChatService> CHAT_SERVICES = new HashMap<>();

    public static void main(String[] args) {
        ClientApp clientApp = new ClientApp();
    }

    public ClientApp() {
        hessian();
        burlap();
    }

    private void hessian() {
        HessianClient hessianClient = new HessianClient();
        IChatService hessianService = hessianClient.getService();
        hessianService.login();
        CHAT_SERVICES.put(Technology.HESSIAN, hessianService);
    }

    private void burlap() {
        BurlapClient burlapClient = new BurlapClient();
        IChatService burlapService = burlapClient.getService();
        burlapService.login();
        CHAT_SERVICES.put(Technology.BURLAP, burlapService);
        
    }

}
