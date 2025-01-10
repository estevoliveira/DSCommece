package com.estevao.DSCommece.dto;

import com.estevao.DSCommece.entities.Product;
import jakarta.persistence.Column;

public class ProductDTO {

    private Long id;

    private String name;


    private String description;

    private Double price;
    private String imgUrl;

    public ProductDTO() {
    }

    public ProductDTO(Product p) {
        this.imgUrl = p.getUmgUrl();
        this.price = p.getPrice();
        this.description = p.getDescription();
        this.name = p.getName();
        this.id = p.getId();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
