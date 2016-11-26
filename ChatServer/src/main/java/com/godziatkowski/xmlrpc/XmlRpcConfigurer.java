package com.godziatkowski.xmlrpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlRpcConfigurer {

    
    @Bean(name = "/xml-rpc")
    public void xmlRpcService(){
    }
}
