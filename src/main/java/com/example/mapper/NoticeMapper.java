package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import com.example.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
	List<NoticeDto> list(@Param("start") int start, @Param("num") int num, @Param("searchType") String searchType,
			@Param("keyword") String keyword);

	public void insert(NoticeDto vo);

	public NoticeDto read(String noticeCode);

	public void updateViewcnt(String noticeCode);

	public void update(NoticeDto vo);

	public void delete(String noticeCode);

	public int getTotal(@Param("searchType") String searchType, @Param("keyword") String keyword);

}
