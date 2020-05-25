package com.aula.cursomc.services;

import com.aula.cursomc.services.AbstractEmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;



public class MockEmailService extends AbstractEmailService{
 
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg){
        LOG.info("Simulando de envio de Email...");
        LOG.info(msg.toString());
        LOG.info("Email enviado!!!");

    }
}