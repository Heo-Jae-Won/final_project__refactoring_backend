package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dto.EventDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EventDao {
	
	@Autowired
	private final SqlSession session;

	String namespace = "com.example.mapper.EventMapper";

	public List<EventDto> list(int page, int num, String searchType, String keyword) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page - 1) * num);
		map.put("num", num);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return session.selectList(namespace + ".list", map);
	};

	public int getTotal(String searchType, String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return session.selectOne(namespace + ".getTotal", map);
	}

	public EventDto read(int ecode) {
		return session.selectOne(namespace + ".read", ecode);
	}

	public void insert(EventDto vo) {
		session.insert(namespace + ".insert", vo);
	}

	public void update(EventDto vo) {
		session.update(namespace + ".update", vo);

	}

	public void delete(int ecode) {
		session.delete(namespace + ".delete", ecode);

	}
}