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
import com.example.spring.service.redisService.RedisServiceProduct;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SessionService sessionService;

    @Autowired
    ProductService productService;
    @Autowired
    RedisServiceProduct redisServiceProduct;

    public GetAllProductDto getAllUseToken(String type, String sort, String userId) throws ApiException {
//        Session session = sessionService.getSessionByToken(token);
//        if (session == null) {
//            throw new ApiException("token khong hop le");
//        }
//        String userId = (String) request.getAttribute("user_id");

        Account account = accountRepository.getAccountById(userId); // dung ra la phai goi service
        List<Product> products;
        // lay duoc user_info
        // lay product_info
        // >> can tao bang: session(user_id, token)
        if (type != null && sort != null) {
            products = productService.getAll(type, sort);
        } else {
            products = productService.getAll();
        }
        return new GetAllProductDto(products, account);

    }
    public List<Product> getAll() {
        try {
            return productRepository.getAll();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public ListProductWithPagination getProductPage(int size , int page){
        int limit = size ;
        int offset = (page -1)*size;
        try {
            List<Product> data = productRepository.getProductPage(limit,offset);



            int totalItem = productRepository.countProduct();
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
        Account account = accountRepository.getAccountById(userId);
        List<Product> products = productRepository.getAll();
        return new GetAllProductDto(products,account);

    }
    public GetAllProductDto getAllProductDto1(String token){
        Claims userId = JwtUtil.verifyToken(token);
        Account account = accountRepository.getAccountById((String) userId.get("userId"));

        List<Product> products = productRepository.getAll();
        return new GetAllProductDto(products,account);
    }
    public List<Product> getAll(String type, String sort) {
        try {
            return productRepository.getAll(type,sort);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Product search(String id) {
        try {
            return productRepository.getProductById(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public Product getProductByName(String display){
        try {
            redisServiceProduct.setProduct(productRepository.getProductByName(display));
            return productRepository.getProductByName(display);

        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List<Product> display(String str) {
        try {
            return productRepository.display(str);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Product> priceIn(String str) {
        try {
            return productRepository.priceIn(str);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Boolean addProduct(Product product) {
        try {
            return productRepository.addProduct(product);
        } catch (Exception e) {
            System.out.println(e);
            return false;

        }
    }

    public Boolean updateProduct(Product product) {
        try {
            return productRepository.updateProduct(product);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean deleteProduct(String id) {
        try {
            return productRepository.deleteProduct(id);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
