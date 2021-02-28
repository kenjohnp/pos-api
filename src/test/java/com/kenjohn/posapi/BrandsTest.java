package com.kenjohn.posapi;


import com.kenjohn.posapi.controllers.BrandController;
import com.kenjohn.posapi.datasource.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest()
public class BrandsTest {

    private MockMvc mockMvc;

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandController brandController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(brandController).build();
    }

    @Test
    public void getBrands() throws Exception{
        mockMvc.perform(get("/api/brands"))
                .andExpect(status().isOk());
    }

    @Test
    public void addBrand() throws Exception{
        String payload = "{\n" +
                " \"brandName\": \"test\"\n }";
        mockMvc.perform(post("/api/brands").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandName").value("test"));
    }

    @Test
    public void getBrand() throws Exception{
        String payload = "{\n" +
                " \"brandName\": \"test\",\n " +
                " \"id\": 1 \n " +
                "}";
        mockMvc.perform(post("/api/brands").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandName").value("test"));

        mockMvc.perform(get("/api/brands/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}
