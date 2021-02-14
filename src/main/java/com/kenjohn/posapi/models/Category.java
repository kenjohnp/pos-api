package com.kenjohn.posapi.models;

import javax.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Integer id;
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
