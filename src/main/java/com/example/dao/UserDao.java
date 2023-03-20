package com.example.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dto.UserDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDao {
	
	@Autowired
	private final SqlSession session;

	String namespace = "com.example.mapper.UserMapper";

	public UserDto read(String uid) {
		return session.selectOne(namespace + ".read", uid);
	}

	public void update(UserDto updateVO) {
		session.update(namespace + ".update", updateVO);
	}

	public void insert(UserDto insertVO) {
		session.insert(namespace + ".insert", insertVO);
	}

	public void deactivate(String uid) {
		session.update(namespace + ".deactivate", uid);
	}

	public void restore(UserDto restoreVO) {
		session.update(namespace + ".restore", restoreVO);

	}

	public int updatePw(UserDto vo) throws Exception {
		return session.update(namespace + ".updatePw", vo);
	}

	public String readUnickname(String unickname) {
		return session.selectOne(namespace + ".readUnickname", unickname);
	}

	public String readUemail(String uemail, String uname) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("uemail", uemail);
		map.put("uname", uname);
		return session.selectOne(namespace + ".readUemail", map);
	}

	public void updateUpoint(String receiver) {
		session.update(namespace + ".updateUpoint", receiver);

	}



}
