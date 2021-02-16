package com.kenjohn.posapi.controllers;

import com.kenjohn.posapi.exceptions.BadRequestException;
import com.kenjohn.posapi.exceptions.ResourceNotFoundException;
import com.kenjohn.posapi.exceptions.ValidationHandler;
import com.kenjohn.posapi.models.Brand;
import com.kenjohn.posapi.repositories.BrandRepository;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("api/brands")
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    private Supplier<ResourceNotFoundException> notFoundException = () -> new ResourceNotFoundException("Brand not found.");

    @GetMapping
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @GetMapping("/{id}")
    public Brand getBrand(@PathVariable Integer id){
        return brandRepository.findById(id).orElseThrow(notFoundException);
    }

    @PostMapping
    public Brand addBrand (@Valid @RequestBody Brand brand) {
        if(brandRepository.existsByBrandName(brand.getBrandName())) throw new BadRequestException("Brand name already exists.");

        brandRepository.save(brand);
        return brand;
    }

    @PutMapping("/{id}")
    public Brand updateBrand(@PathVariable Integer id,@Valid @RequestBody Brand brand){
        brandRepository.findById(id).orElseThrow(notFoundException);

        brand.setId(id);
        brandRepository.save(brand);
        return brand;
    }

    @DeleteMapping("/{id}")
    public Brand deleteBrand(@PathVariable Integer id){
        Brand brand = brandRepository.findById(id).orElseThrow(notFoundException);
        brandRepository.deleteById(id);

        return brand;
    }

}
