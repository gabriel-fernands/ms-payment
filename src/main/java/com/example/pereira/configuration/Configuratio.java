package com.example.pereira.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration
public class Configuratio {
    @Bean
    public ModelMap obterModelMap(){
        return new ModelMap();
    }
}
