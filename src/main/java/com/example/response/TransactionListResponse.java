package com.example.response;

import java.util.List;

import com.example.dto.PayChartDto;
import com.example.dto.PayDto;

import lombok.Data;

@Data
public class TransactionListResponse {
	private List<PayDto> sellList;
	private int sellListTotal;
	private List<PayDto> buyList;
	private int buyListTotal;
	private List<PayChartDto> sellListChart;
	private List<PayChartDto> buyListChart;
}
