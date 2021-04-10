package com.account.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@PropertySource("classpath:Configurations.properties")
public class JwtFilter extends OncePerRequestFilter {
    RestTemplate restTemplate = new RestTemplate();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authToken = httpServletRequest.getHeader("Authorization");
        if(authToken != null && !authToken.isEmpty()){
            System.out.println(authToken);
            HttpHeaders header = new HttpHeaders();
            header.set("Content-Type", "application/json");
            header.set("Authorization", authToken);
            HttpEntity entity = new HttpEntity<>(header);
            ResponseEntity<Boolean> responseEntity = restTemplate.exchange("${JwtFilter.ValidateToken}", HttpMethod.POST, entity, Boolean.class);
            System.out.println(responseEntity.getBody());
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
