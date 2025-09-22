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

import java.net.URI;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AsyncController {

    private final TourismService tourismService;
    private final String serviceKey = "8267e45b7e32c93e61785aaeda84a54a4bb14b89491bf714e2a4b41a1a01bf49"; // 실제 유효한 서비스 키를 입력하세요.

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
        response.put("keyword", keyword);

        return response;
    }

    @GetMapping("/img")
    public List<String> getImg(@RequestParam Integer no) {
        String keyword = tourismService.getImg(no);
        List<String> urls = new LinkedList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            urls.add("/images/default.png"); // 기본 이미지 경로
            return urls;
        }

        try {
            StringBuilder urlStr = new StringBuilder("https://apis.data.go.kr/B551011/PhotoGalleryService1/gallerySearchList1");
            urlStr.append("?serviceKey=").append(serviceKey);
            urlStr.append("&keyword=").append(URLEncoder.encode(keyword, "UTF-8"));
            urlStr.append("&MobileOS=ETC&MobileApp=AppTest&_type=json&numOfRows=3&pageNo=1");

            URI url = new URI(urlStr.toString());

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);

            JSONObject json = new JSONObject(result);
            JSONObject response = json.optJSONObject("response");
            if (response == null) return urls;

            JSONObject body = response.optJSONObject("body");
            if (body == null) return urls;

            // items가 없는 경우(검색 결과가 없는 경우) 처리
            if (!body.has("items") || body.isNull("items")) {
                return urls;
            }

            JSONObject itemsContainer = body.optJSONObject("items");
            if(itemsContainer == null) return urls;

            JSONArray items = itemsContainer.optJSONArray("item");
            if (items == null) return urls;


            for (int i = 0; i < items.length(); i++) {
                urls.add(items.getJSONObject(i).getString("galWebImageUrl"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (urls.isEmpty()) {
            urls.add("/images/default.png");
        }

        return urls;
    }
}