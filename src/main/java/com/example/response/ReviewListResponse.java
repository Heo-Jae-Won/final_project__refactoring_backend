package com.example.response;

import java.util.List;

import com.example.dto.ReviewDto;

import lombok.Data;
@Data
public class ReviewListResponse {
	private List<ReviewDto> reviewList;
	private int reveiwListTotal;

}
