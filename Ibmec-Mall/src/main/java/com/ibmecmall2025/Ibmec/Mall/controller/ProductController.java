package com.ibmecmall2025.Ibmec.Mall.controller;

import java.util.*;


import org.springframework.web.bind.annotation.*;

import com.azure.cosmos.models.PartitionKey;

import com.ibmecmall2025.Ibmec.Mall.model.Product;
import com.ibmecmall2025.Ibmec.Mall.repository.cosmos.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;




@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> create(@RequestBody Product product) {
        
        //Gerando identificadores unicos
        product.setId(UUID.randomUUID().toString());
        repository.save(product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> get(@PathVariable String id, @RequestParam String category) {
        Optional<Product> optProduct = this.repository.findById(id);

        if (optProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optProduct.get(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Product>> getAll(@RequestParam String category) {
        List<Product> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Product> delete(@PathVariable String id, @RequestParam String category) {
        Optional<Product> optProduct = this.repository.findById(id);

        if (optProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}