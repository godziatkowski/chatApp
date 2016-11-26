package com.godziatkowski.hessian;

import com.godziatkowski.chatprotocol.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;


@Configuration
public class HessianConfigurer {

    @Autowired
    private IChatService hessianService;
    
    @Bean(name = "/hessianService")
    public HessianServiceExporter hessianService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(hessianService);
        exporter.setServiceInterface(IChatService.class);
        return exporter;
    }
}
