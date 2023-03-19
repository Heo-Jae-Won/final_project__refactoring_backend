package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.EventDao;
import com.example.dao.EventReplyDao;
import com.example.dto.EventDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

	private final EventReplyDao eventReplyDao;
	private final EventDao eventDao;

	public void eventDelete(int ecode) {
		eventReplyDao.allDelete(ecode);
		eventDao.delete(ecode);
	}

	public List<EventDto> getList(int page, int num, String searchType, String keyword) throws Exception {
		List<EventDto> eventList = eventDao.list(page, num, searchType, keyword);

		return eventList;
	};

	public int getTotal(String searchType, String keyword) {
		int eventListTotal = eventDao.getTotal(searchType, keyword);

		return eventListTotal;
	}

	public EventDto read(int eventCode) {
		EventDto eventDto = eventDao.read(eventCode);

		return eventDto;
	}

	public void insert(EventDto eventDto) {
		eventDao.insert(eventDto);
	}

	public void update(EventDto eventDto) {
		eventDao.update(eventDto);

	}

	public void delete(int eventCode) {
		eventDao.delete(eventCode);

	}

}
