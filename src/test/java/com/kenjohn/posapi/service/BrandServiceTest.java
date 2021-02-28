package com.kenjohn.posapi.service;

import com.kenjohn.posapi.datasource.BrandRepository;
import com.kenjohn.posapi.exceptions.ResourceNotFoundException;
import com.kenjohn.posapi.models.Brand;
import com.kenjohn.posapi.services.BrandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BrandServiceTest {

    @Autowired
    private BrandService brandService;

    @MockBean
    private BrandRepository brandRepository;

    @Nested
    @DisplayName("GetBrands")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class GetBrands {
        @Test
        @DisplayName("It should get all brands from the repository.")
        public void getBrands(){
            when(brandRepository.findAll()).thenReturn(
                    Stream.of(new Brand(1, "a"), new Brand(1, "b")).collect(Collectors.toList()));

            assertEquals(2, brandService.getBrands().size());
        }
    }

    @Nested
    @DisplayName("GetBrand")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class GetBrand {
        @Test
        @DisplayName("It should get the brand by Id")
        public void getBrand(){
            var brand = new Brand(1, "a");

            when(brandRepository.findById(1)).thenReturn(Optional.of(brand));

            assertEquals(brand, brandService.getBrand(1));
        }

        @Test
        @DisplayName("It should return an exception if brand ID was not found.")
        public void testBrandNotFound() {
            ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> brandService.getBrand(1));

            assertTrue(thrown.getMessage().contains("Brand not found."));
        }
    }

    @Nested
    @DisplayName("AddBrand")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class AddBrand {
        @Test
        @DisplayName("It should add the brand using repository")
        public void addBrand(){
            var brand = new Brand(1, "a");

            when(brandRepository.save(brand)).thenReturn(brand);

            assertEquals(brand, brandService.addBrand(brand));

        }

    }


    @Nested
    @DisplayName("Delete Brand")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class className {
        @Test
        @DisplayName("It should delete the brand if exists")
        public void deleteBrand(){
            var brand = new Brand(1, "a");

            when(brandRepository.findById(1)).thenReturn(Optional.of(brand));

            brandService.deleteBrand(1);

            verify(brandRepository, times(1)).deleteById(1);
            assertEquals(brand, brandService.deleteBrand(1));
        }


        @Test
        @DisplayName("It should return exception if brand Id was not found.")
        public void deleteBrandNotFound() {
            ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> brandService.deleteBrand(1));

            assertTrue(thrown.getMessage().contains("Brand not found."));
        }
    }




}
