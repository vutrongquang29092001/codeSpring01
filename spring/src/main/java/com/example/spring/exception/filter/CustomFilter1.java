package com.example.spring.exception.filter;

import com.example.spring.dto.InvalidTokenDto;
import com.example.spring.helper.jwt_decode.RSA;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

@Component
@Order(1)
public class CustomFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        BigInteger E = new BigInteger(token);
        System.out.println("E:  " + E );
        RSA rsa = new RSA();
        System.out.println("E: " + RSA.E + "D: " + rsa.getD() +"N: "+ rsa.getN());
        BigInteger a = rsa.decrytion(E);
        System.out.println("a: " + a);
        String string[] = rsa.changetoString(a).split("_");
        String role = string[1];
        System.out.println(role);
        String userId = string[0];
        System.out.println("userId: " + userId);
        try {
            if (role.equals("admin")) {
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
