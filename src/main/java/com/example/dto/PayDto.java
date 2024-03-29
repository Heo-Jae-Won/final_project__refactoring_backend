package com.example.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PayDto extends ProductBoardDto{
	private int payPrice;
	private int paySellerReview;
	private int payBuyerReview;
	private String payType;
	private String payEmail;
	private String payCode;
	private String payBuyer;
	private String paySeller;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date payRegDate;
	
	//productCode는 extends로 가져와보자.

	

}
