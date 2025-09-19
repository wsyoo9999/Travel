package com.multi.travel.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseVO {
    private List<TourismVO> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
}