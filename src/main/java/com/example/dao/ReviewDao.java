package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dto.ReviewDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReviewDao {
	
	@Autowired
	private final SqlSession session;

	String namespace = "com.example.mapper.ReviewMapper";

	public List<ReviewDto> getList(int page, int num, String receiver) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("start", (page - 1) * num);
		map.put("num", num);
		map.put("reviewReceiver", receiver);
		return session.selectList(namespace + ".list", map);
	}

	public void insert(ReviewDto reviewVO) {
		session.insert(namespace + ".insert", reviewVO);

	}

	public int getTotal(String receiver) {
		return session.selectOne(namespace + ".total", receiver);
	}
}
