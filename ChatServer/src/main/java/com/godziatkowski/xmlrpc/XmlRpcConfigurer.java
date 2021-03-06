package com.godziatkowski.xmlrpc;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlRpcConfigurer {
    
    @Autowired
    private RequestProcessorFactoryFactory requestProcessorFactoryFactory;
    
    @Bean(name = "/xml-rpc")
    public XmlRpcService xmlRpcService() throws XmlRpcException {
        return new XmlRpcService(requestProcessorFactoryFactory);
    }
}
