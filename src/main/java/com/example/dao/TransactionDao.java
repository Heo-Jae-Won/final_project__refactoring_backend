package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dto.PayDto;
import com.example.dto.ProductBoardDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TransactionDao {
	
	@Autowired
	private final SqlSession session;

	String namespace = "com.example.mapper.TransactionMapper";

	public List<PayDto> getBuyList(String buyer, int page) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("buyer", buyer);
		map.put("start", (page - 1) * 6);
		return session.selectList(namespace + ".buyList", map);
	}

	public List<PayDto> getSellList(String seller, int page) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("seller", seller);
		map.put("start", (page - 1) * 6);
		return session.selectList(namespace + ".sellList", map);
	}

	public int getBuyTotal(String buyer) {
		return session.selectOne(namespace + ".buyTotal", buyer);
	}

	public int getSellTotal(String seller) {
		return session.selectOne(namespace + ".sellTotal", seller);
	}

	public List<PayDto> getSellchart(String seller) {
		return session.selectList(namespace + ".sellchart", seller);
	}

	public List<PayDto> getBuychart(String buyer) {
		return session.selectList(namespace + ".buychart", buyer);
	}
}
