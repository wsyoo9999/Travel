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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/async")
@RequiredArgsConstructor
public class AsyncController {
    private final TourismService tourismService;
    @GetMapping("/list")
    public Map<String,Object> list(@RequestParam(defaultValue = "1") int page,
                      @RequestParam(defaultValue = "5") int size,
                      @RequestParam(defaultValue = "") String query,
                      @RequestParam(defaultValue = "0.0")double lat,
                      @RequestParam(defaultValue = "0.0")double lng

    ){
        int total = tourismService.count(query);
        Pagination pagination = new Pagination(page,size,total,5);
        List<TourismVO> lists = tourismService.list(pagination.getOffset(), pagination.getSize(), query);

        System.out.println(page+"||"+size+"||"+total);


        Map<String, Object> body = new HashMap<>();
        body.put("pagination", pagination);
        body.put("lists", lists);
        return   body;
    }
}
