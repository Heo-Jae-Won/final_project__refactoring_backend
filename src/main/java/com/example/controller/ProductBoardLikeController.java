package com.example.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProductBoardLikeDto;
import com.example.service.ProductBoardLikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins ="http://localhost:3000")
public class ProductBoardLikeController {

	private final ProductBoardLikeService productBoardLikeService;

	@RequestMapping(value = "/api/productBoard/user/like", method = RequestMethod.PATCH)
	public void like(@RequestBody ProductBoardLikeDto pboardLikeDto) {

		if (pboardLikeDto.getLikeCode() == null) {
			UUID code = UUID.randomUUID();
			pboardLikeDto.setLikeCode(code.toString());
		}
		productBoardLikeService.like(pboardLikeDto);
	}

	@RequestMapping(value = "/api/productBoard/user/dislike", method = RequestMethod.PATCH)
	public void dislike(@RequestBody ProductBoardLikeDto pboardDislikeDto) {
		
		productBoardLikeService.dislike(pboardDislikeDto);
	}

	@RequestMapping("/api/productBoard/like/{productCode}/{userNickname}")
	public ProductBoardLikeDto likeCount(@PathVariable String productCode, @PathVariable String userNickname) {
		ProductBoardLikeDto likeCount = productBoardLikeService.likeCount(productCode, userNickname);

		return likeCount;
	}

}
