package com.example.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PayVO {
	private int payprice;
	private int sellercondition,buyercondition;
	private String paytype,payemail,paycode,buyer,seller,pcode;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date regDate;
	
	
	

}
