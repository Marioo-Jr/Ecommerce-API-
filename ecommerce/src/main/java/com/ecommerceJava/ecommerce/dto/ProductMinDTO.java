package com.ecommerceJava.ecommerce.dto;
import com.ecommerceJava.ecommerce.entities.Product;

public class ProductMinDTO {


    private long id;
    private String name;
    private Double price;
    private String imgUrl;

    public ProductMinDTO() {

    }

    public ProductMinDTO(long id, String name, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductMinDTO(Product entity) {
       id = entity.getId();
       name = entity.getName();
       price = entity.getPrice();
       imgUrl = entity.getImgUrl();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

}
