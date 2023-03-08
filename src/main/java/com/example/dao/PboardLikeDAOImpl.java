package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.PboardLikeVO;

@Repository
public class PboardLikeDAOImpl implements PboardLikeDAO {

	@Autowired
	SqlSession session;

	String namespace = "com.example.mapper.PboardLikeMapper";

	@Override
	public void like(PboardLikeVO loveVO) {
		session.update(namespace + ".like", loveVO);
	}

	@Override
	public void dislike(PboardLikeVO disloveVO) {
		session.update(namespace + ".dislike", disloveVO);
	}

	@Override
	public PboardLikeVO likeCount(String pcode, String unickname) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("pcode", pcode);
		map.put("unickname", unickname);
		return session.selectOne(namespace + ".likeCount", map);
	}


	
}
