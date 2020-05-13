package com.aula.cursomc.resources;

import com.aula.cursomc.domain.Categoria;
import com.aula.cursomc.domain.Cliente;
import com.aula.cursomc.services.CategoriaService;
import com.aula.cursomc.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id ) {
        
        Cliente obj = service.buscar(id);

        return ResponseEntity.ok().body(obj);

    }


}