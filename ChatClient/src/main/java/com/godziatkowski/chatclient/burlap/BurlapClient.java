package com.godziatkowski.chatclient.burlap;

import com.caucho.burlap.client.BurlapProxyFactory;
import com.godziatkowski.chatprotocol.IChatService;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.remoting.caucho.BurlapProxyFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class BurlapClient {

    private static final String BURLAP_SERVICE = "/burlapService";

    @Autowired
    @Value("${server.url}")
    private String url;

    public IChatService getService() {
        String serviceUrl = url + BURLAP_SERVICE;

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
