package com.example.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EventReplyDto;
import com.example.response.EventReplyListResponse;
import com.example.service.EventReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins ="http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/api/ereply")
public class EventReplyController {

	@Autowired
	private final EventReplyService eventReplyService;

	@RequestMapping("/list")
	public EventReplyListResponse list(@Param("page") int page, @Param("num") int num, @Param("ecode") int eventCode)
			throws Exception {
		EventReplyListResponse eventReplyListResponse = new EventReplyListResponse();
		eventReplyListResponse.setEventReplyList(eventReplyService.list(page, num, eventCode));
		eventReplyListResponse.setEventReplyTotal(eventReplyService.getTotal(eventCode));
		
		return eventReplyListResponse;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void insert(@RequestBody EventReplyDto eventReplyDto) {
		
		eventReplyService.insert(eventReplyDto);
	}

	@RequestMapping(value = "/{eventReplyCode}", method = RequestMethod.PATCH)
	public void delete(@PathVariable int eventReplyCode) {
		
		eventReplyService.userDelete(eventReplyCode);
	}

	@RequestMapping(value = "/admin/{eventReplyCode}", method = RequestMethod.PATCH)
	public void adminDelete(@PathVariable int eventReplyCode) {
		
		eventReplyService.adminDelete(eventReplyCode);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	public void update(@RequestBody EventReplyDto EventReplyDto) {
		
		eventReplyService.update(EventReplyDto);
	}

}