package com.example.spring.exception.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {
    @Bean
    public FilterRegistrationBean<CustomFilter> loggingFilter(){
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter( new CustomFilter() );
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/product/all"); // filter dùng để xác nhận người dùng vì thế ko đk để nó chạy qua đường dẫn login
        return  registrationBean;
    }
}
