package com.multi.travel.controller;

import com.multi.travel.service.TourismService;
import com.multi.travel.support.Pagination;
import com.multi.travel.vo.PageResponseVO;
import com.multi.travel.vo.TourismVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AsyncController {

    private final TourismService tourismService;

    @GetMapping("/tours")
    public Map<String, Object> getTours(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "") String keyword) {

        int block = 5; // 페이지 블록 수

        int total = tourismService.countTourist(keyword);

        Pagination pagination = new Pagination(page, size, total, block);

        PageResponseVO<TourismVO> pageData = tourismService.getTouristPage(pagination.getOffset(), size, keyword);

        Map<String, Object> response = new HashMap<>();
        response.put("pageData", pageData);
        response.put("keyword", keyword); // 현재 검색어 상태도 전달

        return response;
    }
}