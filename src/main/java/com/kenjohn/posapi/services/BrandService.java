package com.kenjohn.posapi.services;

import com.kenjohn.posapi.exceptions.BadRequestException;
import com.kenjohn.posapi.exceptions.ResourceNotFoundException;
import com.kenjohn.posapi.models.Brand;
import com.kenjohn.posapi.datasource.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.function.Supplier;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    private Supplier<ResourceNotFoundException> notFoundException = () -> new ResourceNotFoundException("Brand not found.");


    public List<Brand> getBrands(){
        return brandRepository.findAll();
    }

    public Brand getBrand(int id){
        return brandRepository.findById(id).orElseThrow(notFoundException);
    }

    public Brand addBrand(@RequestBody Brand brand) {
        if(brandRepository.existsByBrandName(brand.getBrandName())) throw new BadRequestException("Brand name already exists.");

        brandRepository.save(brand);

        return brand;
    }

    public Brand editBrand(@RequestBody Brand brand, int id){
        Brand found = brandRepository.findById(id).orElseThrow(notFoundException);

        brand.setId(found.getId());
        brandRepository.save(brand);

        return brand;
    }

    public Brand deleteBrand(int id){
        Brand found = brandRepository.findById(id).orElseThrow(notFoundException);

        brandRepository.deleteById(found.getId());

        return found;
    }

}
