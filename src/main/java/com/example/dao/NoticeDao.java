package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.dto.NoticeDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NoticeDao {
	private final SqlSession session;

	String namespace = "com.example.mapper.NoticeMapper";

	public List<NoticeDto> getList(int page, int num, String searchType, String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page - 1) * num);
		map.put("num", num);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return session.selectList(namespace + ".list", map);
	}

	public void insert(NoticeDto vo) {
		session.insert(namespace + ".insert", vo);
	}

	public NoticeDto read(String ncode) {
		return session.selectOne(namespace + ".read", ncode);
	}

	public void updateViewcnt(String ncode) {
		session.update(namespace + ".updateViewcnt", ncode);

	}

	public void update(NoticeDto vo) {
		session.update(namespace + ".update", vo);
	}

	public void delete(String ncode) {
		session.delete(namespace + ".delete", ncode);
	}

	public int getTotal(String searchType, String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return session.selectOne(namespace + ".getTotal", map);
	}

}
