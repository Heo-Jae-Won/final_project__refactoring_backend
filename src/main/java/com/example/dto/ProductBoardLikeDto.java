package com.example.dto;

import lombok.Data;

@Data
public class ProductBoardLikeDto {
	private String unickname;
	private String pcode;
	private String lcode;
	private boolean lovecondition;
	
	//boolean은 getter setter를 하면 is가 알아서 붙게 됨. 그래서 like와 isLike의 차이가 터진듯.

	
	
}