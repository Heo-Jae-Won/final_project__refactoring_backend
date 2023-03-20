package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dto.EventReplyDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EventReplyDao {
	
	@Autowired
	private final SqlSession session;

	String namespace = "com.example.mapper.EventReplyMapper";

	public List<EventReplyDto> list(int page, int num, int ecode) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page - 1) * num);
		map.put("num", num);
		map.put("ecode", ecode);
		return session.selectList(namespace + ".list", map);
	}

	public void update(EventReplyDto vo) {
		session.update(namespace + ".update", vo);

	}

	public void insert(EventReplyDto vo) {
		session.insert(namespace + ".insert", vo);

	}

	public void delete(int ercode) {
		session.update(namespace + ".delete", ercode);
	}

	public int getTotal(int ecode) {
		return session.selectOne(namespace + ".getTotal", ecode);

	}

	public void adminDelete(int ercode) {
		session.delete(namespace + ".adminDelete", ercode);

	}

	public void allDelete(int ecode) {
		session.delete(namespace + ".allDelete", ecode);

	}

	public void updateUserCondition(int ercode) {
		session.update(namespace + ".updateUserCondition", ercode);
	}

	public void updateAdminCondition(int ercode) {
		session.update(namespace + ".updateAdminCondition", ercode);
	}
}
