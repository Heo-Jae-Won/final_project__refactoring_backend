package com.example.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PayDto {
	private int payprice;
	private int sellercondition;
	private int buyercondition;
	private String paytype;
	private String payemail;
	private String paycode;
	private String buyer;
	private String seller;
	private String pcode;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date regDate;

}
