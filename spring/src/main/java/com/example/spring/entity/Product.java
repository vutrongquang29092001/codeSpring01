package com.example.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productID;
    private String display;
    private int priceIn;
    private int priceOut;
    private int priceSale;
    private int amount;
    private int shipday;
    private String description;
    private String images;
}
