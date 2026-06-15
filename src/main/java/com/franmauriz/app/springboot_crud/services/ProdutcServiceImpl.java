package com.franmauriz.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franmauriz.app.springboot_crud.entities.Product;
import com.franmauriz.app.springboot_crud.repositories.ProductRepository;

@Service
public class ProdutcServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Transactional
    @Override
    public  Optional<Product> delete(Product product) {
        Optional<Product> productdb = repository.findById(product.getId());
        productdb.ifPresent(pro -> {
            repository.delete(pro);
        });

        return productdb;
        
    }

}
