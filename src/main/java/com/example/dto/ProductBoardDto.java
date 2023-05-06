package com.example.dto;

import java.util.Date;

import com.exapmle.validation.ValidProductBoardDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProductBoardDto {
	private int productCode;
	private String productContent;
	@ValidProductBoardDto
	private String productTitle;
	private String productWriter;
	private String productName;
	private String productImage;
	private int productPrice;
	private int productStatus;
	private int productViewcnt;
	private int productLikecnt;
	private int productReplycnt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date productRegDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date productUpdateDate;
	private double userPoint;
	private String userId;

}
