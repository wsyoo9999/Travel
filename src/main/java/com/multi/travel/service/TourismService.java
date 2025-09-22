package com.multi.travel.service;

import com.multi.travel.mapper.TourismMapper;
import com.multi.travel.vo.PageResponseVO;
import com.multi.travel.vo.TourismVO;
import com.multi.travel.support.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourismService {

    private final TourismMapper mapper;

    public TourismService(TourismMapper mapper) {
        this.mapper = mapper;
    }

    public List<TourismVO> getTouristList() {
        return mapper.getTouristList();
    }

    public TourismVO getTouristById(int no) {
        return mapper.getTouristById(no);
    }

    public int countTourist(String keyword) {
        return mapper.countTourist(keyword);
    }

    public PageResponseVO<TourismVO> getTouristPage(int start, int size, String keyword) {
        List<TourismVO> list = mapper.getTouristPage(start, size, keyword);
        Pagination pagination = new Pagination(start / size + 1, size, countTourist(keyword), 5);
        return new PageResponseVO<>(list, pagination);
    }

    public String getImg(int no){
        return mapper.getImg(no);
    }
}