package com.example.dto;



import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EventDto {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date eventRegDate;
	private int eventCode;
	private String eventTitle;
	private String eventContent;
	private String eventWriter;
	
	
	
	
	
}
