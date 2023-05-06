package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import com.example.dto.ProductBoardDto;

@Mapper
public interface ProductBoardMapper {
	public void insert(ProductBoardDto vo);

	public void update(ProductBoardDto vo);

	public ProductBoardDto read(String productCode);

	public void updateViewcnt(String productCode);

	public int getTotal(@Param("searchType") String searchType, @Param("keyword") String keyword);

	public void delete(String pcode);

	public List<ProductBoardDto> best();

	List<ProductBoardDto> list(@Param("start") int start, @Param("num") int num, @Param("searchType") String searchType,
			@Param("keyword") String keyword);

	public void updateLove();

	public void updateSold(int pcode);

	public int readPcondition(int pcode);

	public String readPwriterId(int pcode);

	public String readPwriter(int pcode);
}
