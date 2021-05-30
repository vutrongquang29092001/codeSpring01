package com.example.spring.controller;

import com.example.spring.entity.Account;
import com.example.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    private Account userDetail = null;
    @Autowired
    AccountService accountService;

    @ModelAttribute("BeforeRequest")
    public void getUserById(HttpServletRequest request) {
        System.out.println(request+"  123456789");
        String userId = (String)request.getAttribute("userId");
        System.out.println(userId+"co gi khong ");
        userDetail = accountService.getAccountById((String)request.getAttribute("userId"));
        System.out.println( (String)request.getAttribute("userId")+"fhdhdhd");
        System.out.println( userDetail.toString());
    }
}
