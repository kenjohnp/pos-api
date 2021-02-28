package com.kenjohn.posapi.datasource;

import com.kenjohn.posapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
