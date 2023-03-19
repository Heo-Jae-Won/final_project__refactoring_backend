package com.example.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dto.PayDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PayDao {
	
	@Autowired
	private final SqlSession session;

	String namespace = "com.example.mapper.PayMapper";

	public void insert(PayDto payVO) {
		session.insert(namespace + ".insert", payVO);
	}

	public void updateSellerCondition(String paycode) {
		session.update(namespace + ".updateSellerCondition", paycode);

	}

	public void updateBuyerCondition(String paycode) {
		session.update(namespace + ".updateBuyerCondition", paycode);

	}

	public PayDto read(String paycode) {
		return session.selectOne(namespace + ".read", paycode);
	}
}
