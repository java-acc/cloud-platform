package com.byc.common.core.constant;

/**
 * Cross-cutting constants shared by every module in the platform.
 */
public final class Constants {
    
    public static final String TRACE_ID_HEADER = "X-Trace-Id";
    
    public static final String TRACE_ID_MDC = "X-Trace-Id";
    
    public static final String AUTH_HEADER = "Authorization";
    
    public static final String AUTH_BEARER_PREFIX = "Bearer ";
    
    public static final String DEFAULT_TENANT = "default";
    
    private Constants() {
    }
}
