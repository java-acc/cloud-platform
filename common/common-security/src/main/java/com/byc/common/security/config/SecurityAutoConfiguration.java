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

@AutoConfiguration
@ConditionalOnClass(SecurityFilterChain.class)
public class SecurityAutoConfiguration {
    
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
