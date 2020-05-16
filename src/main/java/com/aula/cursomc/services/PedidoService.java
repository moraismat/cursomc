package com.aula.cursomc.services;

import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;


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

    public Pedido find(Integer id){
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())); 
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagto = (PagamentoBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
    }
}