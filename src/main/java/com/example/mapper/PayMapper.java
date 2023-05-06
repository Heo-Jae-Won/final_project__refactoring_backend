package com.example.mapper;

import org.mapstruct.Mapper;

import com.example.dto.PayDto;

@Mapper
public interface PayMapper {

	void insert(PayDto payVO);

	void updateSellerCondition(String paycode);

	void updateBuyerCondition(String paycode);

	PayDto read(String paycode);

}
