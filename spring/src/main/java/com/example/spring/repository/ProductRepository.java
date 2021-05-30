package com.example.spring.repository;

import com.example.spring.entity.Product;
import com.example.spring.helper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Product> getAll() {
        String sql = "SELECT * FROM Product WHERE deleted = 0 ;";
        List<Product> list = jdbcTemplate.query(sql, new ProductMapper());
        return list;
    }
    public List<Product> getProductPage(int limit , int offset){
        String sql = "SELECT * FROM Product LIMIT ? OFFSET ? ;";
        List<Product> list = jdbcTemplate.query(sql, new ProductMapper(),new Object[]{limit,offset});
        return list;
    }
    public Integer countProduct(){
        String sql = "SELECT COUNT (productID) FROM Product ;";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }
    public Product getProductById(String id) {
        String sql = "SELECT * FROM Product WHERE productID = ? and deleted = 0 ;";
        Product product = (Product) jdbcTemplate.queryForObject(sql, new ProductMapper(), new Object[]{id});
        return product;
    }

    public List<Product> getAll(String type, String sort) {
        switch (sort) {
            case "0":
                String sql = "SELECT * FROM Product WHERE deleted = 0 ORDER BY " + type + " desc ;";
                List<Product> list = jdbcTemplate.query(sql, new ProductMapper());
                return list;
            case "1":
                String sql1 = "SELECT * FROM Product WHERE deleted = 0 ORDER BY " + type + " asc ;";
                List<Product> list1 = jdbcTemplate.query(sql1, new ProductMapper());
                return list1;
            default:
                return null;
        }
    }

    public List<Product> display(String str) {
        String sql = "SELECT * FROM Product WHERE deleted = 0 ORDER BY display " + str + ";";
        List<Product> list = jdbcTemplate.query(sql, new ProductMapper());
        return list;
    }

    public List<Product> priceIn(String str) {
        String sql = "SELECT * FROM Product WHERE deleted = 0 ORDER BY priceIn " + str + ";";
        List<Product> list = jdbcTemplate.query(sql, new ProductMapper());
        return list;
    }
    public Product getProductByName(String display){
        String sql = "SELECT * FROM Product WHERE LOWER(display) = LOWER(?) AND deleted = 0";
        Product product = (Product) jdbcTemplate.queryForObject(sql,new ProductMapper(),new Object[]{display});
        return product;
    }


    public Boolean addProduct(Product product) {
        String sql = "INSERT INTO Product (productID,display,priceIn,priceOut,priceSale,amount,shipday,description,images) VALUES (?,?,?,?,?,?,?,?,?) ;";
        Object params[] = new Object[9];
        params[0] = product.getProductID();
        params[1] = product.getDisplay();
        params[2] = product.getPriceIn();
        params[3] = product.getPriceOut();
        params[4] = product.getPriceSale();
        params[5] = product.getAmount();
        params[6] = product.getShipday();
        params[7] = product.getDescription();
        params[8] = product.getImages();
        jdbcTemplate.update(sql, params);
        return true;

    }

    public Boolean updateProduct(Product product) {
        String sql = "UPDATE Product SET " +
                " display =?," +
                "priceIn =?," +
                "priceOut =?," +
                " priceSale =?," +
                " amount =?," +
                "shipday =?," +
                "description =?," +
                "images =? " +
                "WHERE productID =? and deleted = 0;";
        Object params[] = new Object[9];
        params[0] = product.getDisplay();
        params[1] = product.getPriceIn();
        params[2] = product.getPriceOut();
        params[3] = product.getPriceSale();
        params[4] = product.getAmount();
        params[5] = product.getShipday();
        params[6] = product.getDescription();
        params[7] = product.getImages();
        params[8] = product.getProductID();
        jdbcTemplate.update(sql, params);
        return true;
    }

    public Boolean deleteProduct(String id) {
        String sql = "DELETE FROM Product WHERE ProductID = ? AND deleted = 0;";
        jdbcTemplate.update(sql, new Object[]{id});
        return true;
    }

}
