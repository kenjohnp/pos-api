package com.kenjohn.posapi.controllers;

import com.kenjohn.posapi.datasource.CategoryRepository;
import com.kenjohn.posapi.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @PostMapping
    public Category addCategory(@Valid @RequestBody Category category){
        categoryRepository.save(category);
        return category;
    }


}
