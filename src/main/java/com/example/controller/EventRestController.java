package com.example.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.EventDAO;
import com.example.domain.EventVO;
import com.example.service.EventService;

@RestController
@RequestMapping("/api/event")
public class EventRestController {
	
	@Autowired
	EventDAO edao;
	
	@Autowired
	EventService eservice;
	
	@RequestMapping("/list")
	public HashMap<String,Object> list(int page, int num, String searchType, String keyword)throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("list", edao.list(page, num, searchType, keyword));
		map.put("total", edao.getTotal(searchType, keyword));
		return map;
	};
	
	@RequestMapping("/{ecode}")
	public EventVO read(@PathVariable int ecode){
		return edao.read(ecode);
	}
	
	@RequestMapping(value="",method=RequestMethod.PATCH)
	public void update(@RequestBody EventVO UpdateVO){
		edao.update(UpdateVO);
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public void insert(EventVO InsertVO){
		edao.insert(InsertVO);
	}
	
	@RequestMapping(value="/{ecode}", method=RequestMethod.DELETE)
	public void delete(@PathVariable int ecode){
		eservice.eventDelete(ecode);
	}
	
	
}
