package com.example.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReviewDto {
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date regDate;
	private String rvcode;
	private String rvcontent;
	private String receiver;
	private String sender;
	private String paycode;
	private String pcode;
	private double point;
	
	
	
	
	
	
}
