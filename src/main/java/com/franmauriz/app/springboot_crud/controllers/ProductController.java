package com.franmauriz.app.springboot_crud.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franmauriz.app.springboot_crud.entities.Product;
import com.franmauriz.app.springboot_crud.services.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





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

    @GetMapping("/id/{id}")
    public Optional<Product> getfindById(@PathVariable("id") Long id) {
        Optional<Product> product = productservice.findById(id);
        if(product.isPresent()){
            return product;
        }else{
            return Optional.empty();
        }
    }
    
    

}
