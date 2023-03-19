package com.example.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ProductBoardLikeDao;
import com.example.dto.ProductBoardLikeDto;
import com.example.service.ProductBoardLikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PboardLikeRestController {

	private final ProductBoardLikeService productBoardLikeService;

	@RequestMapping(value = "/api/pboard/user/like", method = RequestMethod.PATCH)
	public void like(@RequestBody ProductBoardLikeDto pboardLikeDto) {

		if (pboardLikeDto.getLcode() == null) {
			UUID code = UUID.randomUUID();
			pboardLikeDto.setLcode(code.toString());
		}
		productBoardLikeService.like(pboardLikeDto);
	}

	@RequestMapping(value = "/api/pboard/user/dislike", method = RequestMethod.PATCH)
	public void dislike(@RequestBody ProductBoardLikeDto pboardDislikeDto) {
		productBoardLikeService.dislike(pboardDislikeDto);
	}

	@RequestMapping("/api/pboard/like/{pcode}/{unickname}")
	public ProductBoardLikeDto likeCount(@PathVariable String pcode, @PathVariable String unickname) {
		ProductBoardLikeDto likeCount = productBoardLikeService.likeCount(pcode, unickname);

		return likeCount;
	}

}
