package com.kenjohn.posapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Integer id;

    @NotNull(message="Brand name is required.")
    @Size(max=50, message = "Brand name must be less than 50 characters.")
    private String brandName;

    public Brand(Integer id, @NotNull(message = "Brand name is required.") @Size(max = 50, message = "Brand name must be less than 50 characters.") String brandName) {
        this.id = id;
        this.brandName = brandName;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "brandName: " + brandName;
    }
}
