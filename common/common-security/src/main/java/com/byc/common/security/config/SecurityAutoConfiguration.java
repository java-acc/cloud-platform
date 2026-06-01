package com.byc.common.security.config;

import com.byc.common.security.jwt.JwtAuthenticationFilter;
import com.byc.common.security.jwt.JwtTokenProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Auto-configuration for Spring Security with JWT-based authentication.
 *
 * <p>Configures a stateless security filter chain with JWT token validation,
 * disables CSRF and CORS, and sets up public endpoints for actuator,
 * API documentation, and authentication routes.
 */
@AutoConfiguration
@ConditionalOnClass(SecurityFilterChain.class)
public class SecurityAutoConfiguration {

  /**
   * Creates a security filter chain configured for stateless JWT authentication.
   *
   * <p>The configuration includes:
   * <ul>
   *   <li>Disabled CSRF and CORS protection</li>
   *   <li>Stateless session management</li>
   *   <li>Public access to actuator, API docs, and auth endpoints</li>
   *   <li>JWT authentication filter before username/password filter</li>
   * </ul>
   *
   * @param http the HttpSecurity builder for configuring security rules
   * @param provider the JWT token provider for validating tokens
   * @return a configured SecurityFilterChain instance
   * @throws Exception if security configuration fails
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, JwtTokenProvider provider) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(
            auth -> auth.requestMatchers("/actuator/**", "/v3/api-docs/**", "/swagger-ui/**",
                    "/swagger-ui.html", "/auth/**", "/login", "/error").permitAll().anyRequest()
                .authenticated())
        .addFilterBefore(new JwtAuthenticationFilter(provider), UsernamePasswordAuthenticationFilter.class)
        .build();
  }
}
