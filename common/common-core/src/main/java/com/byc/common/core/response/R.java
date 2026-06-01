package com.byc.common.core.response;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * Uniform API envelope returned by every controller.
 *
 * @param <T> payload type
 */
@Getter
@Setter
public class R<T> implements Serializable {

  public static final int SUCCESS = 0;

  public static final int FAIL = 1;

  @Serial
  private static final long serialVersionUID = 1L;

  private int code;

  private String message;

  private T data;

  private long timestamp = System.currentTimeMillis();

  /**
   * Constructs a new R instance with default values.
   */
  public R() {
  }

  /**
   * Constructs a new R instance with specified status code, message and data.
   *
   * @param code the status code indicating success or failure
   * @param message the descriptive message
   * @param data the payload data
   */
  public R(int code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  /**
   * Creates a successful response with no data.
   *
   * @param <T> the payload type
   * @return a successful R instance with empty data
   */
  public static <T> R<T> ok() {
    return new R<>(SUCCESS, "OK", null);
  }

  /**
   * Creates a successful response with the specified data.
   *
   * @param <T> the payload type
   * @param data the payload data to include in the response
   * @return a successful R instance containing the provided data
   */
  public static <T> R<T> ok(T data) {
    return new R<>(SUCCESS, "OK", data);
  }

  /**
   * Creates a failed response with the specified error message.
   *
   * @param <T> the payload type
   * @param message the error message describing the failure
   * @return a failed R instance with the provided message
   */
  public static <T> R<T> fail(String message) {
    return new R<>(FAIL, message, null);
  }

  /**
   * Creates a failed response with custom status code and error message.
   *
   * @param <T> the payload type
   * @param code the custom status code
   * @param message the error message describing the failure
   * @return a failed R instance with custom code and message
   */
  public static <T> R<T> fail(int code, String message) {
    return new R<>(code, message, null);
  }

  /**
   * Checks whether the response indicates success.
   *
   * @return true if the status code equals SUCCESS, false otherwise
   */
  public boolean isSuccess() {
    return code == SUCCESS;
  }

}
