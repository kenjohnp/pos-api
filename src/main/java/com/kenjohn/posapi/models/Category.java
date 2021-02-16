package com.kenjohn.posapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Integer id;

    @NotNull(message = "Category Name is required.")
    @NotBlank(message = "Category Name must not be blank.")
    @Size(max=50, message = "Category Name must be less than 50 characters.")
    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
