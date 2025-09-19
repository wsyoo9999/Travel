package com.multi.travel.service;
import com.multi.travel.mapper.TourismMapper;
import com.multi.travel.vo.PageResponseVO;
import com.multi.travel.vo.TourismVO;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TourismService {
    private final TourismMapper tourismMapper;

    public TourismService(TourismMapper tourismMapper) {
        this.tourismMapper = tourismMapper;
    }

    public PageResponseVO findTourismList(String keyword, int page, int size) {
        int total = tourismMapper.count(keyword);
        int offset = page * size;
        List<TourismVO> list = tourismMapper.findList(keyword, size, offset);
        int totalPages = (total == 0) ? 0 : (int) Math.ceil((double) total / size);
        return new PageResponseVO(list, page, size, totalPages, total);
    }

    public int count(String query){
        return tourismMapper.count(query);
    }

    public List<TourismVO> list(int offset, int size, String query) {
        return tourismMapper.list(offset, size, query);
    }
}