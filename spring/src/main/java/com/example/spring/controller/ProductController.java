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
import com.example.spring.service.redisService.RedisServiceProduct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends  BaseController {
    @Autowired
    ProductService productService;

    @Autowired
    SessionService sessionService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RedisServiceProduct redisServiceProduct;
    @GetMapping("/all")
    public GetAllProductDto getAll(@RequestParam(required = false) String type, @RequestParam(required = false) String sort,HttpServletRequest request ) throws ApiException {

       String userId = (String)request.getAttribute("userId");

        return productService.getAllUseToken(type, sort,userId);
    }
    @SneakyThrows
    @GetMapping("/getAllProductDto")
    public GetAllProductDto getAllProductDto(@RequestParam String token,HttpServletRequest request){
        return productService.getAll1(token);
    }

    @GetMapping("/getAllProductDto1") //
    public GetAllProductDto getAllProductDto1(HttpServletRequest request){
        String token = request.getHeader("token");
        return productService.getAllProductDto1(token);
    }
    @GetMapping("/getProductPage")
    public ListProductWithPagination getProductPage(@RequestParam(required = false) Integer size, @RequestParam(required = false) Integer page,HttpServletRequest request) {
        if (size == null) {
            size = 10;
        }
        if (page == null) {
            page = 1;
        }
        return productService.getProductPage(size, page);
    }

    @GetMapping("/search")
    public Product getAllById(@RequestParam String id,HttpServletRequest request) {
        return productService.search(id);
    }

    @GetMapping("/getProductByName")
    public Product getProductByName(@RequestParam String name,HttpServletRequest request) {
        // Filter
        return productService.getProductByName(name);
    }

    @GetMapping("/display")
    public List<Product> display(@RequestParam String str,HttpServletRequest request) {
        return productService.display(str);
    }

    @GetMapping("/priceIn")
    public List<Product> priceIn(@RequestParam String str,HttpServletRequest request) {
        return productService.priceIn(str);
    }

    @PostMapping("/addProduct")
    public Boolean addProduct(@RequestBody Product product,HttpServletRequest request) {
        return productService.addProduct(product);
    }

    @PutMapping("/updateProduct/{id}")
    public Boolean updateProduct(@PathVariable("id") String id,@RequestBody Product product,HttpServletRequest request) throws ApiException {

        redisServiceProduct.setProduct(product);
        return productService.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct")
    public Boolean deleteProduct(@PathVariable("id") String id,HttpServletRequest request) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/test-ex")
    public Integer test(@RequestParam Integer input) {
        return input;
    }
    @Scheduled(fixedRate =  3600000)
    public void scheduleFixedRateTaskAsync() throws ApiException {
        List<Product> list = productService.getAll();
        for(Product temp : list) {
            redisServiceProduct.setProduct(temp);
        }
    }
}
