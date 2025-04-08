package com.example;

import org.camunda.connect.plugin.impl.ConnectProcessEnginePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaConnectConfiguration {

    @Bean
    public ConnectProcessEnginePlugin connectProcessEnginePlugin(){
        return new ConnectProcessEnginePlugin();
    }
}

