package com.cristianbalta.cloudmanagerworker.security;

import com.cristianbalta.cloudmanagerworker.security.jwt.JwtRequestFilter;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;

@Configuration
public class FilterConfig {

    @Bean
    public KeyPair keyPair() {
        return Keys.keyPairFor(SignatureAlgorithm.RS256);
    }

    @Bean
    public FilterRegistrationBean<JwtRequestFilter> registerJwtFilter() {
        FilterRegistrationBean<JwtRequestFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter();
        filterRegistrationBean.setFilter(jwtRequestFilter);
        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }
}
