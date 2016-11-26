package com.godziatkowski.chatclient.hessian;

import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianConnectionFactory;
import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.client.HessianURLConnectionFactory;
import com.godziatkowski.chatprotocol.IChatService;
import java.io.IOException;
import java.net.URL;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

public class HessianClient {

    public IChatService getService() {
        String serviceUrl = "http://localhost:8080/hessianService";

        HessianProxyFactory hpf = new HessianProxyFactory();
        HessianConnectionFactory hcf = new HessianURLConnectionFactory() {

            @Override
            public HessianConnection open(URL url) throws IOException {
                HessianConnection hc = super.open(url);
                hc.addHeader("User-Agent", "RC-Hessian");
                return hc;
            }
        };

        hcf.setHessianProxyFactory(hpf);
        hpf.setConnectionFactory(hcf);

        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setProxyFactory(hpf);
        factory.setConnectionFactory(hcf);
        factory.setServiceUrl(serviceUrl);
        factory.setServiceInterface(IChatService.class);

        factory.afterPropertiesSet();
        IChatService chatService = (IChatService) factory.getObject();

        return chatService;
    }
}
