package com.kenjohn.posapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenjohn.posapi.datasource.BrandRepository;
import com.kenjohn.posapi.exceptions.BadRequestException;
import com.kenjohn.posapi.models.Brand;
import com.kenjohn.posapi.services.BrandService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BrandControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BrandService brandService;

    @MockBean
    BrandRepository brandRepository;

    final String apiEndpoint = "/api/brands";

    //    @ExceptionHandler(ResourceNotFoundException.class)
//    private ResponseEntity<String> handleNotFound(ResourceNotFoundException ex){
//       return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(BadRequestException.class)
//    private ResponseEntity<String> handleBadRequest(BadRequestException ex){
//        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }


    @Nested
    @DisplayName("GET /api/brands")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class GetBrands {
        @Test
        @DisplayName("It should get all brands from the BrandService")
        public void getBrands() throws Exception{
            when(brandService.getBrands()).thenReturn(
                    Stream.of(new Brand(1, "a"), new Brand(2, "b")).collect(Collectors.toList()));

            mockMvc.perform(get(apiEndpoint))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("*", hasSize(2)))
                    .andExpect(jsonPath("$[0].brandName").value("a"));

            verify(brandService, times(1)).getBrands();
        }
    }

    @Nested
    @DisplayName("GET /api/brands/id")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class GetBrand {
        @Test
        @DisplayName("It should get brand by id from the BrandService.")
        public void getBrand() throws Exception{
            var brand = new Brand(1, "a");

            when(brandService.getBrand(1)).thenReturn(brand);

            mockMvc.perform(get(apiEndpoint + "/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.brandName").value("a"))
                    .andDo(print());

            verify(brandService, times(1)).getBrand(1);


        }
    }

    @Nested
    @DisplayName("POST /api/brands")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class AddBrand {
        @Test
        @DisplayName("It should add the new brand and return 201.")
        public void addBrand() throws Exception {
            var brand = new Brand(1, "a");

            var performPost = mockMvc.perform(post(apiEndpoint).contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(brand)));

            performPost.andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.brandName").value("a"))
                        .andExpect(jsonPath("$.id").value(1));
        }

        @Test
        @DisplayName("It should return 400 BAD REQUEST if brand with the given brandName already exists.")
        public void BrandExists() throws Exception {
            var invalidBrand = new Brand(1, "a");

            when(brandService.addBrand(invalidBrand)).thenThrow(BadRequestException.class);

            var performPost = mockMvc.perform(post(apiEndpoint).contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidBrand)));

            performPost.andDo(print()).andExpect(status().isBadRequest());

        }
    }


}
