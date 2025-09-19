package com.multi.travel.service;

import com.multi.travel.mapper.TourismMapper;
import com.multi.travel.vo.TourismVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourismService {

    private final TourismMapper tourismMapper;

    public TourismService(TourismMapper tourismMapper) {
        this.tourismMapper = tourismMapper;
    }

    public List<TourismVO> getTouristList() {
        return tourismMapper.getTouristList(); // 전체 데이터 반환
    }


    public TourismVO getTouristById(int no) {
        return tourismMapper.getTouristById(no);
    }
}
