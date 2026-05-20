package com.byc.common.web.config;

import com.byc.common.web.exception.GlobalExceptionHandler;
import com.byc.common.web.trace.TraceFilter;
import jakarta.servlet.Servlet;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

@AutoConfiguration
@ConditionalOnClass(Servlet.class)
public class CommonWebAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
    
    @Bean
    public FilterRegistrationBean<TraceFilter> traceFilter() {
        FilterRegistrationBean<TraceFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(new TraceFilter());
        reg.addUrlPatterns("/*");
        reg.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return reg;
    }
}
