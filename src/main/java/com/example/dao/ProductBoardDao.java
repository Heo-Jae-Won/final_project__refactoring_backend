package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dto.ProductBoardDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductBoardDao {
	
	@Autowired
	private final SqlSession session;
	
	String namespace = "com.example.mapper.ProductBoardMapper";

	public void insert(ProductBoardDto vo) {
		session.insert(namespace + ".insert", vo);
	};

	public void update(ProductBoardDto vo) {
		session.update(namespace + ".update", vo);
	};

	public ProductBoardDto read(String pcode) {
		return session.selectOne(namespace + ".read", pcode);
	};

	public void updateViewcnt(String pcode) {
		session.update(namespace + ".updateViewcnt", pcode);
	};

	public int getTotal(String searchType, String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return session.selectOne(namespace + ".getTotal", map);
	}

	public void delete(String pcode) {
		session.delete(namespace + ".delete", pcode);
	}

	public List<ProductBoardDto> best() {
		return session.selectList(namespace + ".best");
	}

	public List<ProductBoardDto> getList(int page, int num, String searchType, String keyword) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page - 1) * num);
		map.put("num", num);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return session.selectList(namespace + ".list", map);
	};

	public void updateLove() {
		session.update(namespace + ".updateLove");
	}

	public void updateSold(int pcode) {
		session.update(namespace + ".updateSold", pcode);
	}

	public int readPcondition(int pcode) {
		return session.selectOne(namespace + ".readPcondition", pcode);
	}

	public String readPwriterId(int pcode) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".readPwriterId", pcode);
	}

	public String readPwriter(int pcode) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".readPwriter", pcode);
	}

}
