package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import com.example.dto.PayChartDto;
import com.example.dto.PayDto;

@Mapper
public interface TransactionMapper {

	public List<PayDto> getBuyList(@Param("buyer") String buyer, @Param("start") int start);

	public List<PayDto> getSellList(@Param("seller") String seller, @Param("start") int start);

	public int getBuyTotal(String buyer);

	public int getSellTotal(String seller);

	public List<PayChartDto> getSellchart(String seller);

	public List<PayChartDto> getBuychart(String buyer);

}
