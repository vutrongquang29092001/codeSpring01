package com.example.spring.controller;

import com.example.spring.entity.Account;
import com.example.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/all")
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/search")
    public Account getAccountById(@RequestParam String id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/email")
    public List<Account> email(@RequestParam String str) {
        return accountService.email(str);
    }
    @GetMapping("/display")
    public List<Account> display(@RequestParam String str) {
        return accountService.display(str);
    }
    @GetMapping("/roleStaff")
    public List<Account> roleStaff(@RequestParam String str) {
        return accountService.roleStaff(str);
    }


    @PostMapping("/addAccount")
    public Boolean addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @PutMapping("/updateAccount/{id}")
    public Boolean updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public Boolean deleteAccount(@PathVariable("id") String id) {
        return accountService.deleteAccount(id);
    }

}
