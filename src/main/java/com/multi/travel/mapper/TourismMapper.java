package com.multi.travel.mapper;

import com.multi.travel.vo.TourismVO;
import org.apache.ibatis.annotations.Mapper; // 이 줄을 추가
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TourismMapper {
    int count(@Param("query") String keyword);
    List<TourismVO> findList(@Param("keyword") String keyword, @Param("limit") int limit, @Param("offset") int offset);

    List<TourismVO> list(@Param("offset") int offset,
                         @Param("limit") int size,
                         @Param("query") String query);
}