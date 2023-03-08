package com.example.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.PboardLikeDAO;
import com.example.domain.PboardLikeVO;
import com.example.service.PboardService;

@RestController
@RequestMapping("/api/pboard")
public class PboardLikeRestController {

	@Autowired
	PboardLikeDAO pldao;

	@Autowired
	PboardService service;

	@RequestMapping(value = "/user/like", method = RequestMethod.PATCH)
	public void like(@RequestBody PboardLikeVO likeVO) {
		
		if (likeVO.getLcode() == null) {
			UUID code = UUID.randomUUID();
			likeVO.setLcode(code.toString());
		}
		service.like(likeVO);
	}

	@RequestMapping(value = "/user/dislike", method = RequestMethod.PATCH)
	public void dislike(@RequestBody PboardLikeVO dislikeVO) {
		service.dislike(dislikeVO);
	}

	@RequestMapping("/like/{pcode}/{unickname}")
	public PboardLikeVO likeCount(@PathVariable String pcode, @PathVariable String unickname) {
		return pldao.likeCount(pcode, unickname);
	}

}
