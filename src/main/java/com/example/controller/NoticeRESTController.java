package com.example.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.NoticeDAO;
import com.example.domain.NoticeVO;

@RestController
@RequestMapping("/api/notice")
public class NoticeRESTController {

	@Autowired
	NoticeDAO ndao;
	
	
	@RequestMapping("/list")
	public HashMap<String,Object> list(int page, int num, String searchType, String keyword)throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("list", ndao.list(page, num, searchType, keyword));
		map.put("total", ndao.getTotal(searchType, keyword));
		return map;
	};
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void insert(@RequestBody NoticeVO vo) {
		ndao.insert(vo);
	}
	
	@RequestMapping(value = "/{ncode}")
	public NoticeVO read(@PathVariable String ncode) {
		return ndao.read(ncode);
	}
	
	@RequestMapping(value="/{ncode}", method=RequestMethod.DELETE)
	public void delete(@PathVariable String ncode){
		ndao.delete(ncode);
	}
	
	@RequestMapping(value="", method=RequestMethod.PATCH)
	public void update(@RequestBody NoticeVO vo){
		ndao.update(vo);
	}


		

}
