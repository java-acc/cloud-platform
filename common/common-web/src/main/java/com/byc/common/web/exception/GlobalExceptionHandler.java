package com.byc.common.web.exception;

import com.byc.common.core.exception.BizException;
import com.byc.common.core.response.R;
import jakarta.validation.ConstraintViolationException;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for REST controllers.
 *
 * <p>Provides centralized error handling for various exception types including
 * business exceptions, validation errors, and unexpected system errors.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * Handles business exceptions with custom error codes.
   *
   * @param ex the business exception containing error code and message
   * @return a response entity with failed status and error details
   */
  @ExceptionHandler(BizException.class)
  public ResponseEntity<R<Void>> onBiz(BizException ex) {
    log.warn("biz error: code={} msg={}", ex.getCode(), ex.getMessage());
    return ResponseEntity.ok(R.fail(ex.getCode(), ex.getMessage()));
  }

  /**
   * Handles method argument validation exceptions from {@code @Valid} annotations.
   *
   * @param ex the validation exception containing field error details
   * @return a bad request response with concatenated field error messages
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<R<Void>> onMethodArgNotValid(MethodArgumentNotValidException ex) {
    String msg = ex.getBindingResult().getFieldErrors().stream()
        .map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).collect(Collectors.joining("; "));
    return ResponseEntity.badRequest().body(R.fail(400, msg));
  }

  /**
   * Handles binding exceptions during form data binding.
   *
   * @param ex the binding exception containing field error details
   * @return a bad request response with concatenated field error messages
   */
  @ExceptionHandler(BindException.class)
  public ResponseEntity<R<Void>> onBind(BindException ex) {
    String msg = ex.getBindingResult().getFieldErrors().stream()
        .map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).collect(Collectors.joining("; "));
    return ResponseEntity.badRequest().body(R.fail(400, msg));
  }

  /**
   * Handles constraint violation exceptions from parameter-level validation.
   *
   * @param ex the constraint violation exception
   * @return a bad request response with the violation message
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<R<Void>> onConstraint(ConstraintViolationException ex) {
    return ResponseEntity.badRequest().body(R.fail(400, ex.getMessage()));
  }

  /**
   * Handles all unhandled exceptions as internal server errors.
   *
   * @param ex the unexpected exception
   * @return an internal server error response with generic error message
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<R<Void>> onAny(Exception ex) {
    log.error("unhandled exception", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(R.fail(500, "Internal server error"));
  }
}
