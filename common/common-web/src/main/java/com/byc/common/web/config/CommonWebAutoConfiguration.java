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

/**
 * Auto-configuration for common web components including exception handling and tracing.
 *
 * <p>Registers {@link GlobalExceptionHandler} for centralized error handling and
 * {@link TraceFilter} for request trace ID propagation.
 */
@AutoConfiguration
@ConditionalOnClass(Servlet.class)
public class CommonWebAutoConfiguration {

  /**
   * Creates a GlobalExceptionHandler bean if not already defined.
   *
   * @return a new GlobalExceptionHandler instance for centralized exception handling
   */
  @Bean
  @ConditionalOnMissingBean
  public GlobalExceptionHandler globalExceptionHandler() {
    return new GlobalExceptionHandler();
  }

  /**
   * Registers the TraceFilter to add trace ID to all incoming requests.
   *
   * <p>The filter is configured with highest precedence to ensure trace ID
   * is available early in the request processing chain.
   *
   * @return a FilterRegistrationBean configured with TraceFilter
   */
  @Bean
  public FilterRegistrationBean<TraceFilter> traceFilter() {
    FilterRegistrationBean<TraceFilter> reg = new FilterRegistrationBean<>();
    reg.setFilter(new TraceFilter());
    reg.addUrlPatterns("/*");
    reg.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return reg;
  }
}
