package com.ibmecmall2025.Ibmec.Mall.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ibmecmall2025.Ibmec.Mall.model.Usuario;
import com.ibmecmall2025.Ibmec.Mall.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(name ="/users" )
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsers(){
        List<Usuario> response = repository.findAll();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
     @GetMapping("{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id) {
        Optional<Usuario> response = this.repository.findById(id);
        if (response.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response.get() ,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        this.repository.save(usuario);
        return new ResponseEntity<>(usuario ,HttpStatus.CREATED);
    }
     @DeleteMapping("{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Integer id) {
        Optional<Usuario> response = this.repository.findById(id);
        if (response.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        //Exclui o usuario da base
        this.repository.delete(response.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
