package com.example.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EventReplyDto {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date eventReplyRegDate;
	private int eventReplyCode;
	private int eventCode;
	private String eventReplyContent;
	private String eventReplyWriter;
	private int adminDeleted;
	private int userDeleted;
	
}
