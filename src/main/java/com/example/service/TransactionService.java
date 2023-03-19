package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.TransactionDao;
import com.example.dto.PayDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {
	private TransactionDao transactionDao;

	public List<PayDto> getBuyList(String buyer, int page) {
		List<PayDto> buyList = transactionDao.getBuyList(buyer, page);

		return buyList;
	}
	
	public int getBuyListTotal(String buyer) {
		int buyListTotal = transactionDao.getBuyTotal(buyer);

		return buyListTotal;
	}
	
	public List<PayDto> getBuyChart(String buyer) {
		List<PayDto>buyChart=transactionDao.getBuychart(buyer);
		
		return buyChart;
	}

	public List<PayDto> getSellList(String seller, int page) {
		List<PayDto> sellList = transactionDao.getSellList(seller, page);

		return sellList;
	}

	public int getSellListTotal(String seller) {
		int sellListTotal = transactionDao.getSellTotal(seller);

		return sellListTotal;
	}
	
	public List<PayDto> getSellChart(String seller) {
		List<PayDto> sellChart=transactionDao.getBuychart(seller);
		
		return sellChart;
		
	}


}
