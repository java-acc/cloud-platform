package com.byc.common.security.jwt;

import com.byc.common.core.constant.Constants;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWT authentication filter that extracts and validates tokens from HTTP requests.
 *
 * <p>This filter intercepts incoming requests, extracts the JWT token from the
 * Authorization header, validates it, and sets up the Spring Security context
 * with the authenticated user's details and authorities.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider provider;

  /**
   * Constructs a new JwtAuthenticationFilter with the token provider.
   *
   * @param provider the JWT token provider for parsing and validating tokens
   */
  public JwtAuthenticationFilter(JwtTokenProvider provider) {
    this.provider = provider;
  }

  /**
   * Processes the request by extracting JWT token from Authorization header
   * and setting up authentication context if the token is valid.
   *
   * <p>If the token is missing or invalid, the request continues with anonymous
   * authentication. Valid tokens result in a populated SecurityContext with
   * username and role-based authorities.
   *
   * @param request the HTTP servlet request
   * @param response the HTTP servlet response
   * @param chain the filter chain for continuing request processing
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs during filtering
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    String header = request.getHeader(Constants.AUTH_HEADER);
    if (header != null && header.startsWith(Constants.AUTH_BEARER_PREFIX)) {
      String token = header.substring(Constants.AUTH_BEARER_PREFIX.length());
      try {
        Claims claims = provider.parse(token);
        String username = claims.getSubject();
        Object rolesClaim = claims.get("roles");
        List<SimpleGrantedAuthority> authorities =
            rolesClaim instanceof List<?> list ? list.stream().map(Object::toString)
                .map(SimpleGrantedAuthority::new).toList() : List.of();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
            authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
      } catch (Exception ignored) {
        // invalid token — leave context anonymous
      }
    }
    chain.doFilter(request, response);
  }
}
