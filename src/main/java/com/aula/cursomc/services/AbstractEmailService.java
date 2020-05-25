package com.aula.cursomc.services;

import java.util.Date;

import com.aula.cursomc.domain.Pedido;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService{
    
    @Value("${default.send}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido obj){
        SimpleMailMessage sm = prepareSimpleMailMessage(obj);
        sendEmail(sm);
    }

	protected SimpleMailMessage prepareSimpleMailMessage(Pedido obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado! Código: " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        
        return sm;
	}
}