package com.multi.travel.controller;

import com.multi.travel.service.TourismService;
import com.multi.travel.support.Pagination;
import com.multi.travel.vo.PageResponseVO;
import com.multi.travel.vo.TourismVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TourController {

    private final TourismService tourismService;

    public TourController(TourismService tourismService) {
        this.tourismService = tourismService;
    }

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "") String keyword) {

        int size = 10; // 한 페이지에 10개
        int block = 5;  // 페이지 블록 수

        int total = tourismService.countTourist(keyword);
        Pagination pagination = new Pagination(page, size, total, block);

        PageResponseVO<TourismVO> pageData = tourismService.getTouristPage(pagination.getStart(), size, keyword);

        model.addAttribute("pageData", pageData);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @GetMapping("/tour")
    public String detail(@RequestParam int no, Model model) {
        TourismVO vo = tourismService.getTouristById(no);
        model.addAttribute("tour", vo);
        return "detail"; // 나중에 모달로 사용할 수도 있음
    }
}
