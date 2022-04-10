package com.example.spring.controller;

import com.example.spring.dto.login.LoginRequestDto;
import com.example.spring.dto.login.LoginResponseDto;
import com.example.spring.entity.Account;
import com.example.spring.exception.ApiException;
import com.example.spring.helper.jwt_decode.JwtUtil;
import com.example.spring.service.AccountService;
import com.example.spring.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    //login
    @Autowired
    AccountService accountService;
    @Autowired
    SessionService sessionService;
    @PostMapping("/login") // tao ra 1 token , va luu token trong database
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) throws ApiException {
        // kieerm tra xem co nguoi dung co email va password do ko => neu co, query lay ra id thang day


        Account account = accountService.getAccountByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        if (account == null) {
            throw new ApiException("sai tai khoan hoac mat khau");
        }
        String accountId = account.getAccountID();
        String token = UUID.randomUUID().toString();
        Boolean createSessionResult = sessionService.createSession(accountId, token);
        if (createSessionResult) {
            return new LoginResponseDto(token);
        } else {
            throw new ApiException("dang nhap that bai, vui long thu lai sau");
        }
    }
    @PostMapping("/login1")  // ko luu token trong database , ma hoa userId thanh token roi dung token giai ma lai thanh userId
    public String  login1(@RequestBody LoginRequestDto loginRequestDto) throws ApiException {
        Account account = accountService.getAccountByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        if (account == null) {
            throw new ApiException("sai tai khoan hoac mat khau");
        }
        String accountId = account.getAccountID();
        String token = JwtUtil.generateToken(accountId,account.getRole());
        return token;

    }


}
