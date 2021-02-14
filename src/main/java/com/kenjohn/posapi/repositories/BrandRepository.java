package com.kenjohn.posapi.repositories;

import com.kenjohn.posapi.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsByBrandName(String brandName);
}
