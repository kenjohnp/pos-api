package com.kenjohn.posapi.repositories;

import com.kenjohn.posapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
