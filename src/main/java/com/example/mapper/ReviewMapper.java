package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import com.example.dto.ReviewDto;

@Mapper
public interface ReviewMapper {

	public List<ReviewDto> getList(@Param("start") int start, @Param("num") int num, @Param("receiver") String receiver);

	public void insert(ReviewDto reviewVO);

	public int getTotal(String receiver);

}
