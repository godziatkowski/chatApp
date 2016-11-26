package com.godziatkowski.burlap;

import com.godziatkowski.chatprotocol.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.BurlapServiceExporter;

@Configuration
public class BurlapConfigurer {
    
    @Autowired
    private IChatService burlapService;
    
    @Bean(name="/burlapService")
    public BurlapServiceExporter burlapService(){
        BurlapServiceExporter burlapServiceExporter = new BurlapServiceExporter();
        burlapServiceExporter.setService(burlapService);
        burlapServiceExporter.setServiceInterface(IChatService.class);
        
        return burlapServiceExporter;
    }
    
}
