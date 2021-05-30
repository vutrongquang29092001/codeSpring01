package com.example.spring.service;

import com.example.spring.entity.Account;
import com.example.spring.model.Session;
import com.example.spring.repository.AccountRepository;
import com.example.spring.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    AccountRepository accountRepository;

    public boolean createSession(String userId, String token) {
        return sessionRepository.createSession(userId, token);
    }

    public Session getSessionByToken(String token) {
        return sessionRepository.getSessionByToken(token);
    }


    public List<Account> getAll() {
        try {
            return accountRepository.getAll();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Account getAccountById(String id) {
        try {
            return accountRepository.getAccountById(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Account> email(String str) {
        try {
            return accountRepository.email(str);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Account> display(String str) {
        try {
            return accountRepository.display(str);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Account> roleStaff(String str) {
        try {
            return accountRepository.roleStaff(str);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Boolean addAccount(Account account) {
        try {
//            if (accountRespository.getAccountById(account.getAccountID()) != null) {
//                return false;
//            } else {
            return accountRepository.addAccount(account);
//            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean updateAccount(Account account) {
        try {
            return accountRepository.updateAccount(account);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean deleteAccount(String id) {
        try {
            return accountRepository.deleteAccount(id);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Account getAccountByEmailAndPassword(String email, String password) {
        return accountRepository.getAccountByEmailAndPassword(email, password);
    }
}
