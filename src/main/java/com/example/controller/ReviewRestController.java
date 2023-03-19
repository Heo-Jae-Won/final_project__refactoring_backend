package com.example.controller;

import java.util.UUID;

import org.apache.ibatis.annotations.Param;
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
public class ReviewRestController {

	private final ReviewService reviewService;
	private final PayService payService;
	private final ProductBoardService productBoardService;

	@RequestMapping("")
	public ReviewListResponse list(@Param("page") int page, @Param("num") int num, @Param("receiver") String receiver) {
		ReviewListResponse reviewListResponse = new ReviewListResponse();
		reviewListResponse.setReveiwListTotal(reviewService.getTotal(receiver));
		reviewListResponse.setReviewList(reviewService.getLlist(page, num, receiver));

		return reviewListResponse;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public int insert(ReviewDto insertDto) {
		int result = 0;

		if (payService.read(insertDto.getPaycode()) == null)
			return result;

		UUID code = UUID.randomUUID();
		insertDto.setRvcode(code.toString());

		String pwriter = productBoardService.readPwriter(insertDto.getPcode());

		if (insertDto.getSender().equals(pwriter))
			reviewService.insertSellerReview(insertDto);

		else if (!insertDto.getSender().equals(pwriter))
			reviewService.insertBuyerReview(insertDto);

		result = 1;

		return result;
	}

}
