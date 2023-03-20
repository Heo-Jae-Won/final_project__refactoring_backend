package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ProductBoardDao;
import com.example.dao.ProductBoardLikeDao;
import com.example.dto.ProductBoardLikeDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductBoardLikeService {
	private final ProductBoardLikeDao pboardLikeDao;
	private final ProductBoardDao pboardDao;

	public void like(ProductBoardLikeDto pboardLikeDto) {
		pboardLikeDao.like(pboardLikeDto);
		pboardDao.updateLove();
	}

	public void dislike(ProductBoardLikeDto pboardDislikeDto) {
		pboardLikeDao.dislike(pboardDislikeDto);
		pboardDao.updateLove();

	}

	public ProductBoardLikeDto likeCount(String pcode, String unickname) {
		ProductBoardLikeDto pboardLikeDto = pboardLikeDao.likeCount(pcode, unickname);

		return pboardLikeDto;
	}
}
