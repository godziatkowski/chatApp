package com.godziatkowski.chatclient.burlap;

import com.caucho.burlap.client.BurlapProxyFactory;
import com.godziatkowski.chatprotocol.IChatService;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.remoting.caucho.BurlapProxyFactoryBean;

public class BurlapClient {

    public IChatService getService() {
        String serviceUrl = "http://localhost:8080/burlapService";

        BurlapProxyFactory burlapProxyFactory = new BurlapProxyFactory() {
            @Override
            protected URLConnection openConnection(URL url) throws IOException {
                URLConnection uRLConnection = super.openConnection(url);
                uRLConnection.setRequestProperty("User-Agent", "RC-Burlap");
                return uRLConnection;
            }

        };

        BurlapProxyFactoryBean burlapProxyFactoryBean = new BurlapProxyFactoryBean();
        burlapProxyFactoryBean.setServiceUrl(serviceUrl);
        burlapProxyFactoryBean.setServiceInterface(IChatService.class);
        burlapProxyFactoryBean.setProxyFactory(burlapProxyFactory);

        burlapProxyFactoryBean.afterPropertiesSet();
        IChatService chatService = (IChatService) burlapProxyFactoryBean.getObject();

        return chatService;
    }
}
