package com.example.spring.service;

import com.example.spring.dto.GetAllProductDto;
import com.example.spring.dto.ListProductWithPagination;
import com.example.spring.dto.Pagination;
import com.example.spring.entity.Account;
import com.example.spring.entity.Product;
import com.example.spring.exception.ApiException;
import com.example.spring.helper.jwt_decode.JwtUtil;
import com.example.spring.model.Session;
import com.example.spring.repository.AccountRepository;
import com.example.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRespository;

    @Autowired
    AccountRepository accountRespository;

    @Autowired
    SessionService sessionService;


    public List<Product> getAll() {
        try {
            return productRespository.getAll();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public ListProductWithPagination getProductPage(int size , int page){
        int limit = size ;
        int offset = (page -1)*size;
        try {
            List<Product> data = productRespository.getProductPage(limit,offset);



            int totalItem = productRespository.countProduct();
            int totalPage ;
            if(totalItem % size == 0){
                totalPage = totalItem / size;
            }else {
                totalPage = totalItem / size + 1;
            }
            Pagination pagination = new Pagination(totalPage, data.size() , size);
            return new ListProductWithPagination(data,pagination);
        }catch (Exception e){
            return null;
        }
    }
    public GetAllProductDto getAll1(String token) throws ApiException {
        Session session = sessionService.getSessionByToken(token);
        if(session == null){
            throw new ApiException("token khong hop le");
        }
        String userId = session.getUserId();
        Account account = accountRespository.getAccountById(userId);
        List<Product> products = productRespository.getAll();
        return new GetAllProductDto(products,account);

    }
    public GetAllProductDto getAllProductDto1(String token){
        String userId = JwtUtil.verifyToken(token);
        Account account = accountRespository.getAccountById(userId);
        List<Product> products = productRespository.getAll();
        return new GetAllProductDto(products,account);
    }
    public List<Product> getAll(String type, String sort) {
        try {
            return productRespository.getAll(type,sort);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Product search(String id) {
        try {
            return productRespository.getProductById(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public Product getProductByName(String display){
        try {
            return productRespository.getProductByName(display);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List<Product> display(String str) {
        try {
            return productRespository.display(str);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Product> priceIn(String str) {
        try {
            return productRespository.priceIn(str);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Boolean addProduct(Product product) {
        try {
            return productRespository.addProduct(product);
        } catch (Exception e) {
            System.out.println(e);
            return false;

        }
    }

    public Boolean updateProduct(Product product) {
        try {
            return productRespository.updateProduct(product);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean deleteProduct(String id) {
        try {
            return productRespository.deleteProduct(id);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
