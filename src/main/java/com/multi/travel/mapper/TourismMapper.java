package com.multi.travel.mapper;

import com.multi.travel.vo.TourismVO;
import org.apache.ibatis.annotations.Mapper; // 이 줄을 추가
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TourismMapper {
    int count(@Param("keyword") String keyword);
    List<TourismVO> findList(@Param("keyword") String keyword, @Param("limit") int limit, @Param("offset") int offset);
}