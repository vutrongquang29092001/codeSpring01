package com.example.spring.repository;

import com.example.spring.helper.SessionMapper;
import com.example.spring.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SessionRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean createSession(String userId, String token) {
        String sql = "Insert into session (user_id,token) values (?,?)";
        System.out.println("create session");
        Integer result = jdbcTemplate.update(sql, new Object[]{userId, token});
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Session getSessionByToken(String token) {
        String sql = "Select * from session where token = ? limit 1";
        List<Session> res = jdbcTemplate.query(sql, new SessionMapper(), new Object[]{token});
        if (res.size() == 0) {
            return null;
        } else {
            return res.get(0);
        }
    }
}
