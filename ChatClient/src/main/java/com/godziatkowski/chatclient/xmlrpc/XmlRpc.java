package com.godziatkowski.chatclient.xmlrpc;

import com.godziatkowski.chatprotocol.IChatService;
import java.net.MalformedURLException;
import org.apache.xmlrpc.client.util.ClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class XmlRpc {

    private final ClientFactory factory;

    @Autowired
    public XmlRpc(ClientFactory factory) {
        this.factory = factory;
    }

    public IChatService getService() throws MalformedURLException {
        return (IChatService) factory.newInstance(IChatService.class);
    }

}
