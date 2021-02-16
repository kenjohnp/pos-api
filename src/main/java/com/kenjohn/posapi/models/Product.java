package com.kenjohn.posapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @NotNull(message = "Product name is required.")
    @NotBlank(message = "Product name must not be blank.")
    @Size(max=255, message = "Product name must be less than 255 characters.")
    private String productName;

    @OneToOne
    @NotNull(message = "Brand is required.")
    private Brand brand;

    @OneToOne
    @NotNull(message = "Category is required.")
    private Category category;

    @Size(max=20, message = "Barcode must be less than 20 characters.")
    private String barcode;

    @NotNull
    @PositiveOrZero
    private long unitPrice;

    @NotNull
    private String unitOfMeasurement;

    @NotNull
    @PositiveOrZero
    private int currentQty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public int getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(int currentQty) {
        this.currentQty = currentQty;
    }
}
