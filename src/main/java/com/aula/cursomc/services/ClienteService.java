package com.aula.cursomc.services;

import java.util.Optional;

import com.aula.cursomc.domain.Cliente;
import com.aula.cursomc.repositories.ClienteRepository;
import com.aula.cursomc.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repo;

    public Cliente find(Integer id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 
    }
}