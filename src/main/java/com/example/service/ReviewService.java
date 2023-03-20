package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.PayDao;
import com.example.dao.ReviewDao;
import com.example.dao.UserDao;
import com.example.dto.ReviewDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewDao reviewDao;
	private final UserDao userDao;
	private final PayDao payDao;

	public void insertSellerReview(ReviewDto SellerVO) {
		reviewDao.insert(SellerVO);
		userDao.updateUpoint(SellerVO.getReceiver());
		payDao.updateSellerCondition(SellerVO.getPaycode());
	}

	public void insertBuyerReview(ReviewDto BuyerVO) {
		reviewDao.insert(BuyerVO);
		userDao.updateUpoint(BuyerVO.getReceiver());
		payDao.updateBuyerCondition(BuyerVO.getPaycode());
	}

	public List<ReviewDto> getLlist(int page, int num, String receiver) {
		List<ReviewDto> reveiwList = reviewDao.getList(page, num, receiver);

		return reveiwList;

	}

	public void insert(ReviewDto insertVO) {
		reviewDao.insert(insertVO);

	}

	public int getTotal(String receiver) {
		int reviewListTotal = reviewDao.getTotal(receiver);

		return reviewListTotal;
	}

}
