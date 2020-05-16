package com.aula.cursomc.config;

import java.text.ParseException;

import com.aula.cursomc.services.DBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class testConfig {

    @Autowired
    private DBService dbService;
    
    @Bean
    public boolean instantiateDataBase() throws ParseException{
        
        dbService.instantiateTestDataBase();

        return true;
    }
}