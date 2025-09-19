package com.multi.travel.controller;
import com.multi.travel.service.TourismService;
import com.multi.travel.vo.PageResponseVO;
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
    public String mainPage(Model model) {
        PageResponseVO pageData = tourismService.findTourismList(null, 0, 5);
        model.addAttribute("pageData", pageData);
        return "index";
    }
    @GetMapping("/list")
    public String listFragment(@RequestParam(required = false) String keyword,
                               @RequestParam(defaultValue = "0") int page,
                               Model model) {
        PageResponseVO pageData = tourismService.findTourismList(keyword, page, 5);
        model.addAttribute("pageData", pageData);
        return "index :: #tourismListContainer";
    }
}