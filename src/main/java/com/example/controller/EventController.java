package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EventDto;
import com.example.response.EventListResponse;
import com.example.service.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins ="http://localhost:3000")
@RequiredArgsConstructor
public class EventController {

	@Autowired
	private final EventService eventService;
	
	@RequestMapping(value="/api/event",method=RequestMethod.GET)
	public EventListResponse list(int page, int num, String searchType, String keyword) throws Exception {
		EventListResponse eventReplyListResponse = new EventListResponse();
		eventReplyListResponse.setEventList(eventService.getList(page, num, searchType, keyword));
		eventReplyListResponse.setEventListTotal(eventService.getTotal(searchType, keyword));

		return eventReplyListResponse;
	};

	@RequestMapping(value="/api/event/{eventCode}",method=RequestMethod.GET)
	public EventDto read(@PathVariable int eventCode) {
		return eventService.read(eventCode);
	}

	@RequestMapping(value = "/api/event", method = RequestMethod.PATCH)
	public void update(@RequestBody EventDto UpdateDtO) {
		eventService.update(UpdateDtO);
	}

	@RequestMapping(value = "/api/event", method = RequestMethod.POST)
	public void insert(EventDto InsertDto) {
		eventService.insert(InsertDto);
	}

	@RequestMapping(value = "/api/event/{eventCode}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int eventCode) {
		eventService.eventDelete(eventCode);
	}

}
