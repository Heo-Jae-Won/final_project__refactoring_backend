package com.example.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EventDto;
import com.example.response.EventListResponse;
import com.example.service.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;
	
	@GetMapping(value="/api/event")
	public EventListResponse list(int page, int num, String searchType, String keyword) throws Exception {
		EventListResponse eventReplyListResponse = new EventListResponse();
		eventReplyListResponse.setEventList(eventService.getList(page, num, searchType, keyword));
		eventReplyListResponse.setEventListTotal(eventService.getTotal(searchType, keyword));

		return eventReplyListResponse;
	}

	@GetMapping(value="/api/event/{eventCode}")
	public EventDto read(@PathVariable int eventCode) {
		return eventService.read(eventCode);
	}

	@PatchMapping(value = "/api/event")
	public void update(@RequestBody EventDto updateDto) {
		eventService.update(updateDto);
	}

	@PostMapping(value = "/api/event")
	public void insert(EventDto insertDto) {
		eventService.insert(insertDto);
	}

	@DeleteMapping(value = "/api/event/{eventCode}")
	public void delete(@PathVariable int eventCode) {
		eventService.eventDelete(eventCode);
	}

}
