package com.example.dto;

import lombok.Data;

@Data
public class ProductBoardLikeDto {
	private String userNickname;
	private String productCode;
	private String likeCode;
	private short likeStatus;

}