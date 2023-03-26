package com.example.response;

import java.util.List;

import com.example.dto.EventDto;

import lombok.Data;

@Data
public class EventListResponse {
	private List<EventDto> eventList;
	private int eventListTotal;
}
