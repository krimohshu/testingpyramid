package com.sdettest.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO  {
    private double price;
    private String name;

    public static class ProductBuilder{

        private double price;
        private String name;
        public ProductBuilder withName(String productName)
        {
            this.name = productName;
            return this;
        }
        public ProductBuilder withPrice(double price)
        {
            this.price = price;
            return this;
        }
        public ProductDTO build(){
            ProductDTO product = new ProductDTO();
            product.name = this.name;
            product.price = this.price;
            return product;
        }

    }

    public ProductDTO(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public ProductDTO(){

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
