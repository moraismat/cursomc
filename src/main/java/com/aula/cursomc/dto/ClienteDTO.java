package com.aula.cursomc.dto; 

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;

import com.aula.cursomc.domain.Cliente;

import org.hibernate.validator.constraints.Length;

public class ClienteDTO implements Serializable{
    private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Campo nome vazio!")
	@Length(min = 5, max = 120, message = "Tamanho deve estar no intervalo [25,80] ")
    private String nome;

    @NotEmpty(message = "Campo nome vazio!")
    @Email(message = "Email Invalido!")
    private String email;

    public ClienteDTO(){}

    public ClienteDTO(Cliente obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

    
    
}