package com.example.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EventReplyDto;
import com.example.response.EventReplyListResponse;
import com.example.service.EventReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/eventReply")
public class EventReplyController {

	private final EventReplyService eventReplyService;

	@RequestMapping("")
	public EventReplyListResponse list(@Param("page") int page, @Param("num") int num,
			@Param("eventCode") int eventCode) throws Exception {
		EventReplyListResponse eventReplyListResponse = new EventReplyListResponse();
		eventReplyListResponse.setEventReplyList(eventReplyService.list(page, num, eventCode));
		eventReplyListResponse.setEventReplyTotal(eventReplyService.getTotal(eventCode));

		return eventReplyListResponse;
	}

	@PostMapping(value = "")
	public void insert(@RequestBody EventReplyDto eventReplyDto) {

		eventReplyService.insert(eventReplyDto);
	}

	@PatchMapping(value = "/{eventReplyCode}")
	public void delete(@PathVariable int eventReplyCode) {

		eventReplyService.userDelete(eventReplyCode);
	}

	@PatchMapping(value = "/admin/{eventReplyCode}")
	public void adminDelete(@PathVariable int eventReplyCode) {

		eventReplyService.adminDelete(eventReplyCode);
	}

	@PatchMapping(value = "/update")
	public void update(@RequestBody EventReplyDto eventReplyDto) {

		eventReplyService.update(eventReplyDto);
	}

}