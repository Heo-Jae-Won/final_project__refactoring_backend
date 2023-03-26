package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping(value = "/event/list", method = RequestMethod.GET)
	public String list(int page, int num, String searchType, String keyword, Model model) throws Exception {
		List<EventDto> list = eventService.getList(page, num, searchType, keyword);
		int last = (eventService.getTotal(searchType, keyword) % 6) == 0
				? (eventService.getTotal(searchType, keyword) / 6)
				: (eventService.getTotal(searchType, keyword) / 6) + 1;

		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("keyword", keyword);
		model.addAttribute("num", 6);
		model.addAttribute("last", last);
		return "event/list";
	}

	@RequestMapping(value = "/event/read/{eventCode}", method = RequestMethod.GET)
	public String read(Model model, @PathVariable int eventCode) {
		model.addAttribute("eventDto", eventService.read(eventCode));

		return "event/read";
	}

	@RequestMapping(value = "/event/insert", method = RequestMethod.GET)
	public String insert(Model model, EventDto eventvo, HttpSession session) {
		model.addAttribute("adminId", session.getAttribute("adminId"));

		return "event/insert";
	}
	
	@ResponseBody
	@RequestMapping(value="/api/event/list",method=RequestMethod.GET)
	public EventListResponse list(int page, int num, String searchType, String keyword) throws Exception {
		EventListResponse eventReplyListResponse = new EventListResponse();
		eventReplyListResponse.setEventList(eventService.getList(page, num, searchType, keyword));
		eventReplyListResponse.setEventListTotal(eventService.getTotal(searchType, keyword));

		return eventReplyListResponse;
	};

	@ResponseBody
	@RequestMapping(value="/api/event/{eventCode}",method=RequestMethod.GET)
	public EventDto read(@PathVariable int eventCode) {
		return eventService.read(eventCode);
	}

	@ResponseBody
	@RequestMapping(value = "/api/event", method = RequestMethod.PATCH)
	public void update(@RequestBody EventDto UpdateDtO) {
		eventService.update(UpdateDtO);
	}

	@ResponseBody
	@RequestMapping(value = "/api/event", method = RequestMethod.POST)
	public void insert(EventDto InsertDto) {
		eventService.insert(InsertDto);
	}

	@ResponseBody
	@RequestMapping(value = "/api/event/{eventCode}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int eventCode) {
		eventService.eventDelete(eventCode);
	}

}
