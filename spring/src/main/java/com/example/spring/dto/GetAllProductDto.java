package com.example.spring.dto;

import com.example.spring.entity.Account;
import com.example.spring.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductDto {
    List<Product> products;
    Account account;
}
