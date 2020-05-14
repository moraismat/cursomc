package com.aula.cursomc.dto;

import javax.validation.constraints.NotEmpty;

import java.io.Serializable;

import com.aula.cursomc.domain.Categoria;

import org.hibernate.validator.constraints.Length;

public class CategoriaDTO implements Serializable{
    private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Campo nome vazio!")
	@Length(min = 5, max = 80, message = "Tamanho deve estar no intervalo [25,80] ")
    private String nome;

    public CategoriaDTO(){}

    public CategoriaDTO(Categoria obj){
        id = obj.getId();
        nome = obj.getNome();
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

    
    
}