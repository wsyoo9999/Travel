package com.multi.travel.controller;

import com.multi.travel.service.TourismService;
import com.multi.travel.support.Pagination;
import com.multi.travel.vo.TourismVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AsyncController {
    private final TourismService tourismService;
                      @RequestParam(defaultValue = "5") int size,




    }
}