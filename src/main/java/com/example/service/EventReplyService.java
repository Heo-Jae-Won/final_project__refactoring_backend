package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.EventReplyDto;
import com.example.mapper.EventReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EventReplyService {

	private final EventReplyMapper eventReplyMapper;

	public void userDelete(int ercode) {
		eventReplyMapper.delete(ercode);
		eventReplyMapper.updateUserCondition(ercode);
	}

	public void adminDelete(int ercode) {
		eventReplyMapper.adminDelete(ercode);
		eventReplyMapper.updateAdminCondition(ercode);
	}

	public List<EventReplyDto> list(int page, int num, int eventCode) {
		int start = (page - 1) * num;
		List<EventReplyDto> eventReplyList = eventReplyMapper.list(start, num, eventCode);

		return eventReplyList;
	}

	public void update(EventReplyDto eventReplyDto) {
		eventReplyMapper.update(eventReplyDto);

	}

	public void insert(EventReplyDto eventReplyDto) {
		eventReplyMapper.insert(eventReplyDto);
	}

	public void delete(int eventReplyCode) {
		eventReplyMapper.delete(eventReplyCode);
	}

	public int getTotal(int eventReplyCode) {
		int eventReplyTotal = eventReplyMapper.getTotal(eventReplyCode);
		return eventReplyTotal;

	}

	public void allDelete(int eventCode) {
		eventReplyMapper.allDelete(eventCode);

	}

	public void updateUserCondition(int eventReplyCode) {
		eventReplyMapper.updateUserCondition(eventReplyCode);
	}

	public void updateAdminCondition(int eventReplyCode) {
		eventReplyMapper.updateAdminCondition(eventReplyCode);
	}

}
