package com.example.spring.controller;

import com.example.spring.dto.GetAllProductDto;
import com.example.spring.dto.ListProductWithPagination;
import com.example.spring.entity.Account;
import com.example.spring.entity.Product;
import com.example.spring.exception.ApiException;
import com.example.spring.model.Session;
import com.example.spring.repository.AccountRepository;
import com.example.spring.service.ProductService;
import com.example.spring.service.SessionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    SessionService sessionService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/all")
    public GetAllProductDto getAll(@RequestParam(required = false) String type, @RequestParam(required = false) String sort, @RequestHeader(required = false) String token) throws ApiException {
        // query check db lay user tuong ung voi token
        // neu ko co user nao => fail
        // neu co => lay ra user_id
        Session session = sessionService.getSessionByToken(token);
        if (session == null) {
            throw new ApiException("token khong hop le");
        }
        String userId = session.getUserId();
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
    @SneakyThrows
    @GetMapping("/getAllProductDto")
    public GetAllProductDto getAllProductDto(@RequestParam String token){
        return productService.getAll1(token);
    }

    @GetMapping("/getAllProductDto1") //
    public GetAllProductDto getAllProductDto1(@RequestParam String token){
        return productService.getAllProductDto1(token);
    }
    @GetMapping("/getProductPage")
    public ListProductWithPagination getProductPage(@RequestParam(required = false) Integer size, @RequestParam(required = false) Integer page) {
        if (size == null) {
            size = 10;
        }
        if (page == null) {
            page = 1;
        }
        return productService.getProductPage(size, page);
    }

    @GetMapping("/search")
    public Product getAllById(@RequestParam String id) {
        return productService.search(id);
    }

    @GetMapping("/getProductByName")
    public Product getProductByName(@RequestParam String name) {
        // Filter

        return productService.getProductByName(name);
    }

    @GetMapping("/display")
    public List<Product> display(@RequestParam String str) {
        return productService.display(str);
    }

    @GetMapping("/priceIn")
    public List<Product> priceIn(@RequestParam String str) {
        return productService.priceIn(str);
    }

    @PostMapping("/addProduct")
    public Boolean addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/updateProduct/{id}")
    public Boolean updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct")
    public Boolean deleteProduct(@PathVariable("id") String id) {
        return productService.deleteProduct(id);
    }

    // interceptor
    //filter
    //servlet
    //restController

    //
    @GetMapping("/test-ex")
    public Integer test(@RequestParam Integer input) {
        return input;
    }
}
