package com.kenjohn.posapi.controllers;

import com.kenjohn.posapi.repositories.ProductRepository;
import com.kenjohn.posapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id){
        return productRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        productRepository.save(product);
        return product;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product){
        product.setId(id);
        productRepository.save(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable Integer id){
        Product product = productRepository.findById(id).orElse(null);

        productRepository.deleteById(id);

        return product;
    }

}
