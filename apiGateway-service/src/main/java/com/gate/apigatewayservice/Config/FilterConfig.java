package com.gate.apigatewayservice.Config;

import com.gate.apigatewayservice.Filters.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> filterRegistrationBean() {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtAuthFilter);
        registrationBean.addUrlPatterns("/brief/*", "/rendu/*"); // secured routes
        return registrationBean;
    }
}

