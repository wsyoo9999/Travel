package com.multi.travel.mapper;

import com.multi.travel.vo.TourismVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TourismMapper {

    List<TourismVO> getTouristList();
    TourismVO getTouristById(int no);

    int countTourist(@Param("keyword") String keyword);

    List<TourismVO> getTouristPage(@Param("start") int start,
                                   @Param("size") int size,
                                   @Param("keyword") String keyword);
}
