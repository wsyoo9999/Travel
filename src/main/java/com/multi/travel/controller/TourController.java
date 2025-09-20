package com.multi.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TourController {

    private final com.multi.travel.service.TourismService tourismService;
    public TourController(com.multi.travel.service.TourismService tourismService) {
        this.tourismService = tourismService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("keyword", "");
        return "index";
    }

    @GetMapping("/tour")
    public String detail(@RequestParam int no, Model model) {
        com.multi.travel.vo.TourismVO vo = tourismService.getTouristById(no);
        model.addAttribute("tour", vo);
        return "detail";
    }
}