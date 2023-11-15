package com.example.controller;

import java.util.UUID;

import org.apache.maven.shared.utils.StringUtils;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProductBoardLikeDto;
import com.example.service.ProductBoardLikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductBoardLikeController {

	private final ProductBoardLikeService productBoardLikeService;

	@PatchMapping(value = "/api/productBoard/user/like")
	public void like(@RequestBody ProductBoardLikeDto pboardLikeDto) {

		if (StringUtils.isBlank(pboardLikeDto.getLikeCode())) {
			UUID code = UUID.randomUUID();
			pboardLikeDto.setLikeCode(code.toString());
		}
		productBoardLikeService.like(pboardLikeDto);
	}

	@PatchMapping(value = "/api/productBoard/user/dislike")
	public void dislike(@RequestBody ProductBoardLikeDto pboardDislikeDto) {

		productBoardLikeService.dislike(pboardDislikeDto);
	}

	@RequestMapping("/api/productBoard/like/{productCode}/{userNickname}")
	public ProductBoardLikeDto likeCount(@PathVariable String productCode, @PathVariable String userNickname) {

		return productBoardLikeService.likeCount(productCode, userNickname);
	}

}
