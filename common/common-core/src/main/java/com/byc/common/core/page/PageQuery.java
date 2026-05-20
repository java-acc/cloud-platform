package com.byc.common.core.page;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * Pagination request, intentionally simple and framework-agnostic.
 */
public class PageQuery {
    
    @Min(value = 1, message = "page must be >= 1")
    private int page = 1;
    
    @Min(value = 1, message = "size must be >= 1")
    @Max(value = 100, message = "size must be <= 100")
    private int size = 20;
    
    private String orderBy;
    
    private Boolean asc = true;
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public String getOrderBy() {
        return orderBy;
    }
    
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    
    public Boolean getAsc() {
        return asc;
    }
    
    public void setAsc(Boolean asc) {
        this.asc = asc;
    }
    
    public long offset() {
        return (long) (page - 1) * size;
    }
}
