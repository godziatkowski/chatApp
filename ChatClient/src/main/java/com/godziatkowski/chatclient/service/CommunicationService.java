package com.godziatkowski.chatclient.service;

import com.godziatkowski.chatclient.burlap.BurlapClient;
import com.godziatkowski.chatclient.hessian.HessianClient;
import com.godziatkowski.chatclient.model.User;
import com.godziatkowski.chatprotocol.ChannelData;
import com.godziatkowski.chatprotocol.IChatService;
import com.godziatkowski.chatprotocol.Technology;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import static com.godziatkowski.chatclient.service.Statics.*;


@Component
public class CommunicationService {

    private static final Map<Technology, IChatService> CHAT_SERVICES = new HashMap<>();

    public CommunicationService() {
        hessian();
        burlap();
    }

    private void hessian() {
        HessianClient hessianClient = new HessianClient();
        IChatService hessianService = hessianClient.getService();
        CHAT_SERVICES.put(Technology.HESSIAN, hessianService);
    }

    private void burlap() {
        BurlapClient burlapClient = new BurlapClient();
        IChatService burlapService = burlapClient.getService();
        CHAT_SERVICES.put(Technology.BURLAP, burlapService);
    }

    public void login(String username) {
        long userId = CHAT_SERVICES.get(getSelectedTechnology()).login(username);
        Statics.setLoggedUser(new User(userId, username));
    }

    public List<ChannelData> loadChannels() {
        return CHAT_SERVICES.get(getSelectedTechnology()).getChannels();
    }

    public void createChannel(String channelName) {
        CHAT_SERVICES.get(getSelectedTechnology()).createChannel(channelName, getLoggedUser().getId());
    }

    public void joinChannel(Long id) {
        CHAT_SERVICES.get(getSelectedTechnology()).joinChannel(id, getLoggedUser().getId());
    }

}
