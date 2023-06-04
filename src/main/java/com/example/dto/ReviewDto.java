package com.example.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReviewDto {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date reviewRegDate;
	private String reviewCode;
	private String reviewContent;
	private String reviewSender;
	private String reviewReceiver;
	private double reviewPoint;
	private String payCode;
	private int productCode;

}
