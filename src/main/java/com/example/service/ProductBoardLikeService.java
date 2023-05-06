package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.ProductBoardLikeDto;
import com.example.mapper.ProductBoardLikeMapper;
import com.example.mapper.ProductBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductBoardLikeService {
	private final ProductBoardLikeMapper productBoardLikeMapper;
	private final ProductBoardMapper productBoardMapper;

	public void like(ProductBoardLikeDto pboardLikeDto) {
		productBoardLikeMapper.like(pboardLikeDto);
		productBoardMapper.updateLove();
	}

	public void dislike(ProductBoardLikeDto pboardDislikeDto) {
		productBoardLikeMapper.dislike(pboardDislikeDto);
		productBoardMapper.updateLove();

	}

	public ProductBoardLikeDto likeCount(String pcode, String unickname) {
		ProductBoardLikeDto pboardLikeDto = productBoardLikeMapper.likeCount(pcode, unickname);

		return pboardLikeDto;
	}
}
