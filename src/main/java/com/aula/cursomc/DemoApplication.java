package com.aula.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.aula.cursomc.domain.Categoria;
import com.aula.cursomc.domain.Cidade;
import com.aula.cursomc.domain.Cliente;
import com.aula.cursomc.domain.Endereco;
import com.aula.cursomc.domain.Estado;
import com.aula.cursomc.domain.ItemPedido;
import com.aula.cursomc.domain.Pagamento;
import com.aula.cursomc.domain.PagamentoBoleto;
import com.aula.cursomc.domain.PagamentoCartao;
import com.aula.cursomc.domain.Pedido;
import com.aula.cursomc.domain.Produto;
import com.aula.cursomc.domain.enums.EstadoPagamento;
import com.aula.cursomc.domain.enums.TipoCliente;
import com.aula.cursomc.repositories.CategoriaRepository;
import com.aula.cursomc.repositories.CidadeRepository;
import com.aula.cursomc.repositories.ClienteRepository;
import com.aula.cursomc.repositories.EnderecoRepository;
import com.aula.cursomc.repositories.EstadoRepository;
import com.aula.cursomc.repositories.ItemPedidoRepository;
import com.aula.cursomc.repositories.PagamentoRepository;
import com.aula.cursomc.repositories.PedidoRepository;
import com.aula.cursomc.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama, Mesa e Banho");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
	
		

		Estado est1 = new Estado(null, "MG");
		Estado est2 = new Estado(null, "SP");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
	
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "11111111", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("546464646", "565656565", "1238512"));

		Endereco e1 = new Endereco(null, "Rua FLores", "320", "APT 01", "Jardim", "358916316", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Matos", "3220", "APT 123", "Bessa", "312312", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:08"), cli1, e2);

		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
	
		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));

		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));


		ItemPedido ipe1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ipe2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ipe3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ipe1,ipe2));
		ped2.getItens().addAll(Arrays.asList(ipe3));

		p1.getItens().addAll(Arrays.asList(ipe1));
		p2.getItens().addAll(Arrays.asList(ipe3));
		p3.getItens().addAll(Arrays.asList(ipe2));

		itemPedidoRepository.saveAll(Arrays.asList(ipe1,ipe2,ipe3));

	}

}
