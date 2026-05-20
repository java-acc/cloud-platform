package com.byc.common.core.response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Uniform API envelope returned by every controller.
 *
 * @param <T> payload type
 */
public class R<T> implements Serializable {
    
    public static final int SUCCESS = 0;
    
    public static final int FAIL = 1;
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    private int code;
    
    private String message;
    
    private T data;
    
    private long timestamp = System.currentTimeMillis();
    
    public R() {
    }
    
    public R(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    public static <T> R<T> ok() {
        return new R<>(SUCCESS, "OK", null);
    }
    
    public static <T> R<T> ok(T data) {
        return new R<>(SUCCESS, "OK", data);
    }
    
    public static <T> R<T> fail(String message) {
        return new R<>(FAIL, message, null);
    }
    
    public static <T> R<T> fail(int code, String message) {
        return new R<>(code, message, null);
    }
    
    public boolean isSuccess() {
        return code == SUCCESS;
    }
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
