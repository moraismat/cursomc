package com.aula.cursomc.services;

import java.util.Optional;

import com.aula.cursomc.domain.Categoria;
import com.aula.cursomc.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id){
        Optional<Categoria> obj = repo.findById(id);

        return obj.orElse(null);
    }
}