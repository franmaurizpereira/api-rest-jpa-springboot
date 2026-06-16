package com.franmauriz.app.springboot_crud.controllers;

import com.franmauriz.app.springboot_crud.repositories.ProductRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franmauriz.app.springboot_crud.entities.Product;
import com.franmauriz.app.springboot_crud.services.ProductService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/product")
public class ProductController {

    
    @Autowired
    private ProductService productservice;



    @GetMapping("/all")
    public List<Product> getfindAll() {
        List<Product> products = productservice.findAll();
        return products;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getfindById(@PathVariable("id") Long id) {
        Optional<Product> product = productservice.findById(id);
        if(product.isPresent()){
            return ResponseEntity.ok(product.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody Product entity, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productservice.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product entity, BindingResult result,@PathVariable Long id){
        if(result.hasFieldErrors()){
           return validation(result);
        }
        Optional<Product> product = productservice.update(id, entity);
        if(product.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(product.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }        
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Optional<Product> optionalproduct = productservice.delete(id);
        if(optionalproduct.isPresent()){
            return ResponseEntity.ok(optionalproduct.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
    
    

}
