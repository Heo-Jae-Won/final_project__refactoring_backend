package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import com.example.dto.EventDto;

@Mapper
public interface EventMapper {
	List<EventDto> list(@Param("start") int page, @Param("num") int num, @Param("searchType") String searchType,
			@Param("keyword") String keyword);

	int getTotal(@Param("searchType") String searchType, @Param("keyword") String keyword);

	EventDto read(int eventCode);

	void insert(EventDto vo);

	void update(EventDto vo);

	void delete(int eventCode);
}
