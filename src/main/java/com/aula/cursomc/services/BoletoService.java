package com.aula.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import com.aula.cursomc.domain.PagamentoBoleto;

import org.springframework.stereotype.Service;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoBoleto pgto, Date instanteDoPedido) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instanteDoPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pgto.setDataVencimento(cal.getTime());
    
    }
    
}