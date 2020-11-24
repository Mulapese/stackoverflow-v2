package com.example.stackoverflow.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class QuestionInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    QuesInterceptor quesInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(quesInterceptor);
    }
}