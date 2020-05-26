package com.aula.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.aula.cursomc.services.validation.ClienteInsert;

public class ClienteNewDTO implements Serializable{
    private static final long serialVersionUID = 1L;

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Campo nome vazio!")
    @Email(message = "Email Invalido!")
	private String email;
	
	@NotEmpty(message = "Campo nome vazio!")
	private String cpfOuCnpj;
	
    private Integer tipoCliente;


	@NotEmpty(message = "Campo nome vazio!")
	private String logradouro;
	
	@NotEmpty(message = "Campo nome vazio!")
	private String numero;
	
    private String complemento;
	private String bairro;
	
	@NotEmpty(message = "Campo nome vazio!")
    private String cep;


	@NotEmpty(message = "Campo nome vazio!")
	private String teletone1;
	private String teletone2;
	
    private String teletone3;

	private Integer cidadeId;
	
	@NotEmpty
	private String senha;

	public ClienteNewDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTeletone1() {
		return teletone1;
	}

	public void setTeletone1(String teletone1) {
		this.teletone1 = teletone1;
	}

	public String getTeletone2() {
		return teletone2;
	}

	public void setTeletone2(String teletone2) {
		this.teletone2 = teletone2;
	}

	public String getTeletone3() {
		return teletone3;
	}

	public void setTeletone3(String teletone3) {
		this.teletone3 = teletone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

     
    
    
}