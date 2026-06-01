package com.byc.common.core.page;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

/**
 * Pagination request, intentionally simple and framework-agnostic.
 */
@Getter
@Setter
public class PageQuery {

  @Min(value = 1, message = "page must be >= 1")
  private int page = 1;

  @Min(value = 1, message = "size must be >= 1")
  @Max(value = 100, message = "size must be <= 100")
  private int size = 20;

  private String orderBy;

  private Boolean asc = true;

  /**
   * Calculates the offset for database pagination query.
   *
   * @return the offset value based on current page and size
   */
  public long offset() {
    return (long) (page - 1) * size;
  }
}
