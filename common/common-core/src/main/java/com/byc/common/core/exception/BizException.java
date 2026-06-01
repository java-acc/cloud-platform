package com.byc.common.core.exception;

/**
 * Business exception — recoverable, with an explicit error code mapped to the API envelope.
 */
public class BizException extends RuntimeException {

  private final int code;

  /**
   * Constructs a new BizException with default error code.
   *
   * @param message the error message describing the business exception
   */
  public BizException(String message) {
    this(1, message);
  }

  /**
   * Constructs a new BizException with specified error code and message.
   *
   * @param code the error code mapped to the API envelope
   * @param message the error message describing the business exception
   */
  public BizException(int code, String message) {
    super(message);
    this.code = code;
  }

  /**
   * Constructs a new BizException with error code, message and cause.
   *
   * @param code the error code mapped to the API envelope
   * @param message the error message describing the business exception
   * @param cause the underlying cause of this exception
   */
  public BizException(int code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  /**
   * Gets the error code associated with this exception.
   *
   * @return the error code mapped to the API envelope
   */
  public int getCode() {
    return code;
  }
}
