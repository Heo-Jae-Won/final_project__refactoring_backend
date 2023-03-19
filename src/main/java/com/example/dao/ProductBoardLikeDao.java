package com.example.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.dto.ProductBoardLikeDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductBoardLikeDao {
	private final SqlSession session;

	String namespace = "com.example.mapper.ProductBoardLikeMapper";

	public void like(ProductBoardLikeDto loveVO) {
		session.update(namespace + ".like", loveVO);
	}

	public void dislike(ProductBoardLikeDto disloveVO) {
		session.update(namespace + ".dislike", disloveVO);
	}

	public ProductBoardLikeDto likeCount(String pcode, String unickname) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("pcode", pcode);
		map.put("unickname", unickname);
		return session.selectOne(namespace + ".likeCount", map);
	}
}
