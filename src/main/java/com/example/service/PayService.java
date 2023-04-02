package com.example.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.PayDao;
import com.example.dao.ProductBoardDao;
import com.example.dto.PayDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PayService {

	private final PayDao payDao;
	private final ProductBoardDao pboardDao;

	public void insert(PayDto insertVO) {
		payDao.insert(insertVO);
		pboardDao.updateSold(insertVO.getProductCode());
	}

	public void updateSellerCondition(String paycode) {
		payDao.updateSellerCondition(paycode);

	}

	public void updateBuyerCondition(String paycode) {
		payDao.updateBuyerCondition(paycode);
	}

	public PayDto read(String paycode) {
		PayDto payDto = payDao.read(paycode);

		return payDto;
	}

}
