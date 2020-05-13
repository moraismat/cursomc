package com.aula.cursomc;

import java.util.Arrays;

import com.aula.cursomc.domain.Categoria;
import com.aula.cursomc.domain.Cidade;
import com.aula.cursomc.domain.Cliente;
import com.aula.cursomc.domain.Endereco;
import com.aula.cursomc.domain.Estado;
import com.aula.cursomc.domain.Produto;
import com.aula.cursomc.domain.enums.TipoCliente;
import com.aula.cursomc.repositories.CategoriaRepository;
import com.aula.cursomc.repositories.CidadeRepository;
import com.aula.cursomc.repositories.ClienteRepository;
import com.aula.cursomc.repositories.EnderecoRepository;
import com.aula.cursomc.repositories.EstadoRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
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
	}

}
