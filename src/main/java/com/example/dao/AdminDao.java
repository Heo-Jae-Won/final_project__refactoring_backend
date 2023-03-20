package com.example.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dto.AdminLoginDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminDao {
	
	@Autowired
	private final SqlSession session;
	
	String namespace="com.example.mapper.AdminMapper";
	
	public AdminLoginDto read(String aid) {
		return session.selectOne(namespace + ".read", aid);
	}

	public void insert(AdminLoginDto insertVO) {
		session.insert(namespace+".insert" , insertVO);		
	}
	
	
}
