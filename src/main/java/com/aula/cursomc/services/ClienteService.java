package com.aula.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.aula.cursomc.domain.Cidade;
import com.aula.cursomc.domain.Cliente;
import com.aula.cursomc.domain.Endereco;
import com.aula.cursomc.domain.enums.Perfil;
import com.aula.cursomc.domain.enums.TipoCliente;
import com.aula.cursomc.dto.ClienteDTO;
import com.aula.cursomc.dto.ClienteNewDTO;
import com.aula.cursomc.repositories.ClienteRepository;
import com.aula.cursomc.repositories.EnderecoRepository;
import com.aula.cursomc.security.UserSS;
import com.aula.cursomc.services.exception.AuthorizationException;
import com.aula.cursomc.services.exception.DataIntegrityException;
import com.aula.cursomc.services.exception.ObjectNotFoundException;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id){

        UserSS user = UserService.authenticated();

        if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())){
            throw new AuthorizationException("Acesso Negado!");
        }

        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());

        return obj;
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        uptadeData(newObj, obj);

        return repo.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } 
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir um Cliente que possui entidades relacionadas"); 
        }
    }

    public List<Cliente> findAll(){    
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

    public Cliente fromDTO(ClienteDTO objDTO){
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
    }

    private void uptadeData(Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public Cliente fromDTO(ClienteNewDTO objDTO){
        Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipoCliente()),pe.encode(objDTO.getSenha())); 
        Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
                            objDTO.getBairro(), objDTO.getCep(), cli,cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDTO.getTeletone1());

        if(objDTO.getTeletone2() != null){
            cli.getTelefones().add(objDTO.getTeletone2());
        }
        if(objDTO.getTeletone3() != null){
            cli.getTelefones().add(objDTO.getTeletone3());
        }

        return cli;        
    
    } 
}