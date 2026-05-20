package com.byc.common.web.trace;

import com.byc.common.core.constant.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

public class TraceFilter extends OncePerRequestFilter implements Ordered {
    
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
    
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
