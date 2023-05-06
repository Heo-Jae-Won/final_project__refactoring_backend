package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.EventDto;
import com.example.mapper.EventMapper;
import com.example.mapper.EventReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

	private final EventReplyMapper eventReplyMapper;
	private final EventMapper eventMapper;

	public void eventDelete(int ecode) {
		eventReplyMapper.allDelete(ecode);
		eventMapper.delete(ecode);
	}

	public List<EventDto> getList(int page, int num, String searchType, String keyword) throws Exception {
		int start = (page - 1) * num;
		List<EventDto> eventList = eventMapper.list(start, num, searchType, keyword);

		return eventList;
	};

	public int getTotal(String searchType, String keyword) {
		int eventListTotal = eventMapper.getTotal(searchType, keyword);

		return eventListTotal;
	}

	public EventDto read(int eventCode) {
		EventDto eventDto = eventMapper.read(eventCode);

		return eventDto;
	}

	public void insert(EventDto eventDto) {
		eventMapper.insert(eventDto);
	}

	public void update(EventDto eventDto) {
		eventMapper.update(eventDto);

	}

	public void delete(int eventCode) {
		eventMapper.delete(eventCode);

	}

}
