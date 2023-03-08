package com.example.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EreplyVO {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date regDate;
	private int ercode;
	private int ecode;
	private String ercontent;
	private String erwriter;
	private int admincondition;
	private int usercondition;
	
}
