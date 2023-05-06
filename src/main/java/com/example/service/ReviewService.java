package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.ReviewDto;
import com.example.mapper.PayMapper;
import com.example.mapper.ReviewMapper;
import com.example.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewMapper reviewMapper;
	private final UserMapper userMapper;
	private final PayMapper payMapper;

	public void insertSellerReview(ReviewDto SellerVO) {
		reviewMapper.insert(SellerVO);
		userMapper.updateUpoint(SellerVO.getReviewReceiver());
		payMapper.updateSellerCondition(SellerVO.getPayCode());
	}

	public void insertBuyerReview(ReviewDto BuyerVO) {
		reviewMapper.insert(BuyerVO);
		userMapper.updateUpoint(BuyerVO.getReviewReceiver());
		payMapper.updateBuyerCondition(BuyerVO.getPayCode());
	}

	public List<ReviewDto> getLlist(int page, int num, String receiver) {
		int start = (page - 1) * num;
		List<ReviewDto> reveiwList = reviewMapper.getList(start, num, receiver);

		return reveiwList;

	}

	public void insert(ReviewDto insertVO) {
		reviewMapper.insert(insertVO);

	}

	public int getTotal(String receiver) {
		int reviewListTotal = reviewMapper.getTotal(receiver);

		return reviewListTotal;
	}

}
