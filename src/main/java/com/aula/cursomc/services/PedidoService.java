package com.aula.cursomc.services;

import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aula.cursomc.domain.ItemPedido;
import com.aula.cursomc.domain.PagamentoBoleto;
import com.aula.cursomc.domain.Pedido;	
import com.aula.cursomc.domain.enums.EstadoPagamento;
import com.aula.cursomc.repositories.ItemPedidoRepository;
import com.aula.cursomc.repositories.PagamentoRepository;
import com.aula.cursomc.repositories.PedidoRepository;	
import com.aula.cursomc.services.exception.ObjectNotFoundException;	
@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private EmailService emailService;
    
    public Pedido find(Integer id){
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())); 
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);
		obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagto = (PagamentoBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
        itemPedidoRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
    }
}