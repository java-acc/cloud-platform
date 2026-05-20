package com.byc.common.web.exception;

import com.byc.common.core.exception.BizException;
import com.byc.common.core.response.R;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(BizException.class)
    public ResponseEntity<R<Void>> onBiz(BizException ex) {
        log.warn("biz error: code={} msg={}", ex.getCode(), ex.getMessage());
        return ResponseEntity.ok(R.fail(ex.getCode(), ex.getMessage()));
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<R<Void>> onMethodArgNotValid(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body(R.fail(400, msg));
    }
    
    @ExceptionHandler(BindException.class)
    public ResponseEntity<R<Void>> onBind(BindException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body(R.fail(400, msg));
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<R<Void>> onConstraint(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(R.fail(400, ex.getMessage()));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<R<Void>> onAny(Exception ex) {
        log.error("unhandled exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(R.fail(500, "Internal server error"));
    }
}
