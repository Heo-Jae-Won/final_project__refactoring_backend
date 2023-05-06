package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import com.example.dto.EventDto;
import com.example.dto.EventReplyDto;

@Mapper
public interface EventReplyMapper {
	List<EventReplyDto> list(@Param("start") int start, @Param("num") int num, @Param("eventCode") int eventCode);

	void update(EventReplyDto vo);

	void insert(EventReplyDto vo);

	void delete(int eventReplyCode);

	int getTotal(int eventReplyCode);

	void adminDelete(int eventReplyCode);

	void allDelete(int eventReplyCode);

	void updateUserCondition(int eventReplyCode);

	void updateAdminCondition(int eventReplyCode);
}
