package com.aula.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.aula.cursomc.domain.Categoria;
import com.aula.cursomc.dto.CategoriaDTO;
import com.aula.cursomc.repositories.CategoriaRepository;
import com.aula.cursomc.services.exception.DataIntegrityException;
import com.aula.cursomc.services.exception.ObjectNotFoundException;
@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Integer id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); 
    }


    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        Categoria newObj = find(obj.getId());
        uptadeData(newObj, obj);

        return repo.save(newObj);
    }


    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } 
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir uma Categoria que possui Produtos"); 
        }
    }

    public List<Categoria> findAll(){    
        return repo.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

    public Categoria fromDTO(CategoriaDTO objDTO){
        return new Categoria(objDTO.getId(), objDTO.getNome());
    }

    private void uptadeData(Categoria newObj, Categoria obj){
        newObj.setNome(obj.getNome());
    }

}