package com.example.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.ProductBoardDto;
import com.example.response.ProductListResponse;
import com.example.service.ProductBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductBoardController {

	private final ProductBoardService pboardService;

	@RequestMapping("/api/productBoard")
	public ProductListResponse list(@Param("page") int page, @Param("num") int num,
			@Param("searchType") String searchType, @Param("keyword") String keyword) throws Exception {
		ProductListResponse productListResponse = new ProductListResponse();
		productListResponse.setProductList(pboardService.getList(page, num, searchType, keyword));
		productListResponse.setProductListTotal(pboardService.getTotal(searchType, keyword));

		return productListResponse;
	};

//	@RequestMapping("/api/productBoard/readpcondition/{productCode}")
//	public int readPcondition(@PathVariable int productCode) {
//
//		return pboardService.readPcondition(productCode);
//	};

	@RequestMapping(value = "/api/productBoard/update", method = RequestMethod.PUT)
	public void update(@RequestPart(value = "data") ProductBoardDto updateVO,
			@RequestPart(value = "file", required = false) MultipartFile multi) throws Exception {

		pboardService.update(updateVO, multi);
	};

	@RequestMapping("/api/productBoard/{productCode}")
	public ProductBoardDto readviewcnt(@PathVariable String productCode) throws Exception {
		return pboardService.read(productCode);
	};

	@RequestMapping("/api/productBoard/data/{productCode}")
	public ProductBoardDto read(@PathVariable String productCode) throws Exception {

		return pboardService.getProductInfo(productCode);
	};

	@RequestMapping(value = "/api/productBoard", method = RequestMethod.POST)
	public void insert(@Validated @RequestPart(value = "data") ProductBoardDto insertVO,
			@RequestPart(value = "file") MultipartFile multi) throws Exception {
		pboardService.insert(insertVO, multi);
	};

	@RequestMapping("/api/productBoard/best")
	public List<ProductBoardDto> getBestItems() {

		return pboardService.getBestItems();
	}

	@RequestMapping(value = "/api/productBoard/{productCode}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String productCode) {

		pboardService.delete(productCode);
	}

}
