package com.example.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PboardVO {
	private String pcode;
	@NotBlank
	private String pcontent;
	@NotBlank
	private String ptitle;
	@NotBlank
	private String pwriter;
	@NotBlank
	private String pname;
	private String pimage;
	private double upoint;
	private String uid;
	private int pprice;
	private int pcondition;
	private int viewcnt;
	private int plike;
	private int replycnt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date regDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date updateDate;
	

	

}
