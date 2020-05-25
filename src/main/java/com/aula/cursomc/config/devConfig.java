package com.aula.cursomc.config;

import java.text.ParseException;

import com.aula.cursomc.services.DBService;
import com.aula.cursomc.services.EmailService;
import com.aula.cursomc.services.SmtpEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("dev")
public class devConfig {

    @Autowired
    private DBService dbService;
    
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String stragey;

    @Bean
    public boolean instantiateDataBase() throws ParseException{
        
        if(!"create".equals(stragey)){
            return false;
        }

        dbService.instantiateTestDataBase();

        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}