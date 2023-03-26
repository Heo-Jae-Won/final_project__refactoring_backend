package com.example.response;

import java.util.List;

import com.example.dto.ProductBoardDto;

import lombok.Data;

@Data
public class ProductListResponse {
	private List<ProductBoardDto> productList;
	private int productListToal;

}
