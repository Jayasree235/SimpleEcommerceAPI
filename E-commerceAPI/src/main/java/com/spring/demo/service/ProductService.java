package com.spring.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.spring.demo.model.Product;
import com.spring.demo.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public Page<Product> getAll(int page, int size) {
        return productRepo.findAll(PageRequest.of(page, size));
    }

    public Page<Product> searchByName(String name, int page, int size) {
        return productRepo.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(name, "", PageRequest.of(page, size));
    }
    
    public Page<Product> searchByCategory(String category, int page, int size) {
        return productRepo.findByCategory(category, PageRequest.of(page, size));
    }

    public Product add(Product p) {
        return productRepo.save(p);
    }

    public Product update(Long id, Product p) {
        p.setId(id);
        return productRepo.save(p);
    }

    public void delete(Long id) {
        productRepo.deleteById(id);
    }
}
