package com.example.response;

import java.util.List;

import com.example.dto.EventReplyDto;

import lombok.Data;

@Data
public class EventReplyListResponse {
	private List<EventReplyDto> eventReplyList;
	private int eventReplyTotal;

}
