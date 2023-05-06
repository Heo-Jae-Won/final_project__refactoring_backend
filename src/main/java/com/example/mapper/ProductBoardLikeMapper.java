package com.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import com.example.dto.ProductBoardLikeDto;

@Mapper
public interface ProductBoardLikeMapper {

	public void like(ProductBoardLikeDto loveVO);

	public void dislike(ProductBoardLikeDto disloveVO);

	public ProductBoardLikeDto likeCount(@Param("productCode") String productCode,
			@Param("userNickname") String userNickname);

}
