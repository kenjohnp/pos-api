package com.kenjohn.posapi.controllers;

import com.kenjohn.posapi.exceptions.BadRequestException;
import com.kenjohn.posapi.exceptions.ResourceNotFoundException;
import com.kenjohn.posapi.models.Brand;
import com.kenjohn.posapi.datasource.BrandRepository;
import com.kenjohn.posapi.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("api/brands")
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandService brandService;

    private Supplier<ResourceNotFoundException> notFoundException = () -> new ResourceNotFoundException("Brand not found.");

    @GetMapping
    public List<Brand> getBrands() {
        return brandService.getBrands();
    }

    @GetMapping("/{id}")
    public Brand getBrand(@PathVariable Integer id){
//        return brandRepository.findById(id).orElseThrow(notFoundException);
        return brandService.getBrand(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Brand addBrand (@Valid @RequestBody Brand brand) {

        brandService.addBrand(brand);
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
