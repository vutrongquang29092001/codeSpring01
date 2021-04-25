package com.example.spring.controller;

import com.example.spring.entity.Account;
import com.example.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    private Account userDetail = null;
    @Autowired
    AccountService accountService;
    @ModelAttribute("BeforeRequest")
    public void getUserById(HttpServletRequest request) {
        userDetail = accountService.getAccountById((String)request.getAttribute("user_id"));
        System.out.println(userDetail.toString());
    }
}
