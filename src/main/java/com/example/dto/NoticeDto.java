package com.example.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class NoticeDto {

	private String noticeCode;
	private String noticeTitle;
	private String noticeContent;
	private String noticeWriter;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date noticeRegDate;
	
	



}
