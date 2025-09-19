package com.multi.travel.controller;

import com.multi.travel.service.TourismService;
import com.multi.travel.vo.TourismVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TourController {

    private final TourismService tourismService;

    public TourController(TourismService tourismService) {
        this.tourismService = tourismService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<TourismVO> list = tourismService.getTouristList();
        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("/tour/{no}")
    public String detail(@PathVariable int no, Model model) {
        TourismVO vo = tourismService.getTouristById(no);
        model.addAttribute("tour", vo);
        return "detail";
    }
}
