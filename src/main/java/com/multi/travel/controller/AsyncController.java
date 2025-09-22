package com.multi.travel.controller;

import com.multi.travel.service.TourismService;
import com.multi.travel.support.Pagination;
import com.multi.travel.vo.PageResponseVO;
import com.multi.travel.vo.TourismVO;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AsyncController {

    private final TourismService tourismService;
    private final String serviceKey = "8267e45b7e32c93e61785aaeda84a54a4bb14b89491bf714e2a4b41a1a01bf49";

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

    @GetMapping("/img")
    public List<String> getImg(@RequestParam Integer id) {
        String keyword = tourismService.getImg(id);
        List<String> urls = new LinkedList<>();
        try{
            String urlStr = "http://apis.data.go.kr/B551011/PhotoGalleryService1/gallerySearchList1"
                    + "?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8")
                    + "&keyword=" + URLEncoder.encode(keyword, "UTF-8")
                    + "&MobileOS=ETC&MobileApp=AppTest&_type=json&numOfRows=1&pageNo=1";

            System.out.println(urlStr);

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(urlStr, String.class);

            JSONObject json = new JSONObject(result);
            JSONArray items = json.getJSONObject("response")
                    .getJSONObject("body")
                    .getJSONObject("items")
                    .getJSONArray("item");

            if(items.length() > 0){
                int i = 0;
                while(items.getJSONObject(i)!=null || i<=2){
                    urls.add(items.getJSONObject(i).getString("galWebImageUrl"));
                    i++;
                }
                return urls;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}