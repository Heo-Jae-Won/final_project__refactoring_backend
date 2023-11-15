package com.example.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.response.TransactionListResponse;
import com.example.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService transactionService;

	@RequestMapping("/api/tradeInfo/buyList/{buyer}")
	public TransactionListResponse buyList(@PathVariable String buyer, @Param("page") int page) throws Exception {
		TransactionListResponse transactionListResponse = new TransactionListResponse();
		transactionListResponse.setBuyList(transactionService.getBuyList(buyer, page));
		transactionListResponse.setBuyListTotal(transactionService.getBuyListTotal(buyer));

		return transactionListResponse;
	}

	@RequestMapping("/api/tradeInfo/sellList/{seller}")
	public TransactionListResponse sellList(@PathVariable String seller, @Param("page") int page) throws Exception {
		TransactionListResponse transactionListResponse = new TransactionListResponse();
		transactionListResponse.setSellList(transactionService.getSellList(seller, page));
		transactionListResponse.setSellListTotal(transactionService.getSellListTotal(seller));

		return transactionListResponse;
	}

	@RequestMapping("/api/tradeInfo/sellChart/{seller}")
	public TransactionListResponse sellchart(@PathVariable String seller) throws Exception {
		TransactionListResponse transactionListResponse = new TransactionListResponse();
		transactionListResponse.setSellListChart(transactionService.getSellChart(seller));

		return transactionListResponse;
	}

	@RequestMapping("/api/tradeInfo/buyChart/{buyer}")
	public TransactionListResponse buyChart(@PathVariable String buyer) throws Exception {
		TransactionListResponse transactionListResponse = new TransactionListResponse();
		transactionListResponse.setBuyListChart(transactionService.getBuyChart(buyer));

		return transactionListResponse;
	}

}
