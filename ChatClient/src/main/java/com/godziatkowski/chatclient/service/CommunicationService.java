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
import com.godziatkowski.chatclient.xmlrpc.XmlRpc;
import com.godziatkowski.chatprotocol.ChannelMessages;
import com.godziatkowski.chatprotocol.Message;
import java.net.MalformedURLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CommunicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommunicationService.class);

    private static final Map<Technology, IChatService> CHAT_SERVICES = new HashMap<>();
    private final BurlapClient burlapClient;
    private final HessianClient hessianClient;
    private final XmlRpc xmlRpc;

    @Autowired
    public CommunicationService(BurlapClient burlapClient, HessianClient hessianClient, XmlRpc xmlRpc) throws MalformedURLException {
        this.burlapClient = burlapClient;
        this.hessianClient = hessianClient;
        this.xmlRpc = xmlRpc;
        hessian();
        burlap();
        xmlRpc();
    }

    private void hessian() {
        IChatService hessianService = hessianClient.getService();
        CHAT_SERVICES.put(Technology.HESSIAN, hessianService);
    }

    private void burlap() {
        IChatService burlapService = burlapClient.getService();
        CHAT_SERVICES.put(Technology.BURLAP, burlapService);
    }

    private void xmlRpc() throws MalformedURLException {
        IChatService xmlRpcService = xmlRpc.getService();
        CHAT_SERVICES.put(Technology.XML_RPC, xmlRpcService);
    }

    public void login(String username) {
        long userId = CHAT_SERVICES.get(getSelectedTechnology()).login(username);
        Statics.setLoggedUser(new User(userId, username));
        LOGGER.info("User <{}> logged in, his id <{}>", username, userId);
    }

    public void logout() {
        if (getLoggedUser() != null) {
            CHAT_SERVICES.get(getSelectedTechnology()).logout(getLoggedUser().getId());
            LOGGER.info("User <{}> logged out", getLoggedUser().getUsername());
        }
    }

    public ChannelData createChannel(String channelName) {
        ChannelData channelData = CHAT_SERVICES.get(getSelectedTechnology()).createChannel(channelName, getLoggedUser().getId());
        LOGGER.info("Channel <{}> <{}> created", channelData.getName(), channelData.getId());
        Statics.addChannel(channelData.getId());
        return channelData;
    }

    public void joinChannel(Long id) {
        Statics.addChannel(id);
        CHAT_SERVICES.get(getSelectedTechnology()).joinChannel(id, getLoggedUser().getId());
        LOGGER.info("Joining channel <{}>", id);
    }

    public void leaveChannel(Long id) {
        CHAT_SERVICES.get(getSelectedTechnology()).leaveChannel(id, getLoggedUser().getId());
        LOGGER.info("Leaving channel <{}>", id);
    }

    public List<ChannelData> loadChannels() {
        LOGGER.debug("Loading channels");
        List<ChannelData> channelDatas = CHAT_SERVICES.get(getSelectedTechnology()).getChannels();
        return channelDatas;
    }

    public void sendMessage(String messageText, ChannelData channelData) {
        Message message = new Message(messageText, getLoggedUser().getUsername());
        CHAT_SERVICES.get(getSelectedTechnology()).sendMessage(channelData.getId(), getLoggedUser().getId(), message);
        LOGGER.info("Message send on channel <{}>", channelData.getName());
    }

    public List<ChannelMessages> readMessages() {
        List<ChannelMessages> messages = CHAT_SERVICES.get(getSelectedTechnology()).getMyMessages(getLoggedUser().getId());
        LOGGER.info("Loaded messages for <{}> channels", messages.size());
        return messages;
    }

}
