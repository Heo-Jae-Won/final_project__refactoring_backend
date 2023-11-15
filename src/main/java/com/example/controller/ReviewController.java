package com.example.controller;

import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ReviewDto;
import com.example.response.ReviewListResponse;
import com.example.service.PayService;
import com.example.service.ProductBoardService;
import com.example.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

	private final ReviewService reviewService;
	private final PayService payService;
	private final ProductBoardService productBoardService;

	@RequestMapping("")
	public ReviewListResponse list(@Param("page") int page, @Param("num") int num, @Param("receiver") String receiver) {
		ReviewListResponse reviewListResponse = new ReviewListResponse();
		reviewListResponse.setReviewListTotal(reviewService.getTotal(receiver));
		reviewListResponse.setReviewList(reviewService.getLlist(page, num, receiver));

		return reviewListResponse;
	}

	@PostMapping(value = "")
	public int insert(ReviewDto insertDto) {
		int result = 0;

		if (ObjectUtils.isEmpty(payService.read(insertDto.getPayCode()))) {
			return result;
		}

		UUID code = UUID.randomUUID();
		insertDto.setReviewCode(code.toString());

		String productWriter = productBoardService.readPwriter(insertDto.getProductCode());

		if (insertDto.getReviewSender().equals(productWriter)) {
			reviewService.insertSellerReview(insertDto);
		} else {
			reviewService.insertBuyerReview(insertDto);
		}

		result = 1;

		return result;
	}

}
