package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.dto.ProductBoardDto;
import com.example.response.ProductListResponse;
import com.example.service.ProductBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProductBoardController {

	@Autowired
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

	@RequestMapping(value = "/api/productBoard/update", method = RequestMethod.POST)
	public void update(ProductBoardDto updateVO, MultipartHttpServletRequest multi) throws Exception {

		// file path designate

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
	public void insert(@Valid ProductBoardDto insertVO, MultipartHttpServletRequest multi) throws Exception {

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
