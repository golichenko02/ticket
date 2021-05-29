package org.hillel.service.query_info;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class PaginationInfo {

    private int pageNumber;
    private int pageSize;
    private String orderField;
    private boolean isAsc;

    public PaginationInfo(int pageNumber, int pageSize, String orderField, boolean isAsc) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.orderField = orderField;
        this.isAsc = isAsc;
    }

    public int getFirstResult() {
        return (pageNumber - 1) * pageSize;
    }

    public String getOrderBy() {
        if (!StringUtils.isEmpty(orderField)) {
            return " order by " + orderField + (isAsc ? " asc" : " desc");
        }
        return "";
    }
}
