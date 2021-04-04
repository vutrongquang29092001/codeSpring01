package com.example.spring.service;

import com.example.spring.model.Session;
import com.example.spring.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    SessionRepository sessionRepository;

    public boolean createSession(String userId, String token) {
        return sessionRepository.createSession(userId, token);
    }

    public Session getSessionByToken(String token) {
        return sessionRepository.getSessionByToken(token);
    }

}
