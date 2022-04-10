package com.example.spring.exception.filter;

import com.example.spring.dto.InvalidTokenDto;
import com.example.spring.helper.jwt_decode.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsMutator;
import io.jsonwebtoken.Jwts;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
@Order(1)
public class CustomFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        //exception handler khong co tac dung o tang nay, phai tu xu ly
        try {
            Claims claims = JwtUtil.verifyToken(token);
            String role = (String) claims.get("role");
            String userId = (String) claims.get("userId");
            if (role.equals("ADMIN")) {
                System.out.println("userId: " + userId);
                request.setAttribute("user_id", userId);
                System.out.println("CustomFilter - true");
                filterChain.doFilter(request, response);
            } else {
                System.out.println("CustomFilter - false");
            }
        } catch (Exception e) {
            response.setStatus(401);
            response.setContentType("application/json");
            InvalidTokenDto invalidTokenDTO = new InvalidTokenDto("Invalid token");
            OutputStream out = response.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            out.flush();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(" Default filter init");
    }

    @Override
    public void destroy() {
        System.out.println("default filter destroy");
    }
}

