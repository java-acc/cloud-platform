package com.byc.common.core.page;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Pagination result wrapper containing total count and record list.
 *
 * @param <T> the data type of records
 */
@Getter
@Setter
public class PageResult<T> {

  private long total;

  private int page;

  private int size;

  private List<T> records;

  /**
   * Constructs a new PageResult with pagination data.
   *
   * @param total the total number of records
   * @param page the current page number
   * @param size the number of records per page
   * @param records the list of data records for current page
   */
  public PageResult(long total, int page, int size, List<T> records) {
    this.total = total;
    this.page = page;
    this.size = size;
    this.records = records;
  }

  /**
   * Creates an empty PageResult with zero total records.
   *
   * @param <T> the data type of records
   * @param page the current page number
   * @param size the number of records per page
   * @return an empty PageResult instance with no records
   */
  public static <T> PageResult<T> empty(int page, int size) {
    return new PageResult<>(0, page, size, List.of());
  }
}
