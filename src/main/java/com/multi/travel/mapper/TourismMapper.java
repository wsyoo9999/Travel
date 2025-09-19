package com.multi.travel.mapper;

import com.multi.travel.vo.TourismVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TourismMapper {
    List<TourismVO> getTouristList();
    TourismVO getTouristById(int no);
}
