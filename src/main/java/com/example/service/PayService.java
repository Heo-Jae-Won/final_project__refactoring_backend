package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.PayDto;
import com.example.mapper.PayMapper;
import com.example.mapper.ProductBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PayService {

	private final PayMapper payMapper;
	private final ProductBoardMapper productBoardMapper;

	public void insert(PayDto insertVO) {
		payMapper.insert(insertVO);
		productBoardMapper.updateSold(insertVO.getProductCode());
	}

	public void updateSellerCondition(String paycode) {
		payMapper.updateSellerCondition(paycode);

	}

	public void updateBuyerCondition(String paycode) {
		payMapper.updateBuyerCondition(paycode);
	}

	public PayDto read(String paycode) {
		PayDto payDto = payMapper.read(paycode);

		return payDto;
	}

}
