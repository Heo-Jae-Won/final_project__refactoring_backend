package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.PayChartDto;
import com.example.dto.PayDto;
import com.example.mapper.TransactionMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
	private final TransactionMapper transactionMapper; // final이 없으면 null 뜸.

	public List<PayDto> getBuyList(String buyer, int page) {
		int start = (page - 1) * 6;
		List<PayDto> buyList = transactionMapper.getBuyList(buyer, start);

		return buyList;
	}

	public int getBuyListTotal(String buyer) {
		int buyListTotal = transactionMapper.getBuyTotal(buyer);

		return buyListTotal;
	}

	public List<PayChartDto> getBuyChart(String buyer) {
		List<PayChartDto> buyChart = transactionMapper.getBuychart(buyer);

		return buyChart;
	}

	public List<PayDto> getSellList(String seller, int page) {
		int start = (page - 1) * 6;
		List<PayDto> sellList = transactionMapper.getSellList(seller, start);

		return sellList;
	}

	public int getSellListTotal(String seller) {
		int sellListTotal = transactionMapper.getSellTotal(seller);

		return sellListTotal;
	}

	public List<PayChartDto> getSellChart(String seller) {
		List<PayChartDto> sellChart = transactionMapper.getBuychart(seller);

		return sellChart;

	}

}
