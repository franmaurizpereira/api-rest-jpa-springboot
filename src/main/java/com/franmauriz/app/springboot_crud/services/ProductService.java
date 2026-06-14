package com.franmauriz.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import com.franmauriz.app.springboot_crud.entities.Product;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void delete(Product product);
}
