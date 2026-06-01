package com.byc.common.web.trace;

import com.byc.common.core.constant.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Servlet filter that generates and propagates trace IDs for request tracking.
 *
 * <p>This filter extracts the trace ID from the incoming request header or generates
 * a new one if not present. The trace ID is stored in MDC for logging correlation
 * and added to the response header for client-side tracking.
 */
public class TraceFilter extends OncePerRequestFilter implements Ordered {

  /**
   * Processes the request by injecting trace ID into MDC and response headers.
   *
   * <p>If the request contains a trace ID header, it is reused; otherwise, a new
   * UUID-based trace ID is generated. The trace ID is cleaned up after request
   * processing to prevent memory leaks.
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
    String traceId = request.getHeader(Constants.TRACE_ID_HEADER);
    if (traceId == null || traceId.isBlank()) {
      traceId = UUID.randomUUID().toString().replace("-", "");
    }
    MDC.put(Constants.TRACE_ID_MDC, traceId);
    response.setHeader(Constants.TRACE_ID_HEADER, traceId);
    try {
      chain.doFilter(request, response);
    } finally {
      MDC.remove(Constants.TRACE_ID_MDC);
    }
  }

  /**
   * Returns the filter execution order.
   *
   * @return the highest precedence to ensure early trace ID injection
   */
  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }
}
