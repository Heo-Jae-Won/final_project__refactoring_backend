package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.dto.ProductBoardDto;
import com.example.response.ProductListResponse;
import com.example.service.ProductBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProductBoardController {

	@Autowired
	private final ProductBoardService pboardService;

	@ResponseBody
	@RequestMapping("/api/pboard")
	public ProductListResponse list(@Param("page") int page, @Param("num") int num,
			@Param("searchType") String searchType, @Param("keyword") String keyword) throws Exception {
		ProductListResponse productListResponse = new ProductListResponse();
		productListResponse.setProductList(pboardService.getList(page, num, searchType, keyword));
		productListResponse.setProductListToal(pboardService.getTotal(searchType, keyword));

		return productListResponse;
	};

	@ResponseBody
	@RequestMapping("/api/pboard/readpcondition/{pcode}")
	public int readPcondition(@PathVariable String pcode) {

		return pboardService.readPcondition(pcode);
	};

	@ResponseBody
	@RequestMapping(value = "/api/pboard/update", method = RequestMethod.POST)
	public void update(ProductBoardDto updateVO, MultipartHttpServletRequest multi) throws Exception {

		// file path designate

		pboardService.update(updateVO, multi);
	};

	@ResponseBody
	@RequestMapping("/api/pboard/{pcode}")
	public ProductBoardDto readviewcnt(@PathVariable String pcode) throws Exception {

		return pboardService.read(pcode);
	};

	@ResponseBody
	@RequestMapping("/api/pboard/data/{pcode}")
	public ProductBoardDto read(@PathVariable String pcode) throws Exception {
		
		return pboardService.getProductInfo(pcode);
	};

	@ResponseBody
	@RequestMapping(value = "/api/pboard", method = RequestMethod.POST)
	public void insert(@Valid ProductBoardDto insertVO, MultipartHttpServletRequest multi) throws Exception {

		pboardService.insert(insertVO, multi);
	};

	@ResponseBody
	@RequestMapping("/api/pboard/best")
	public List<ProductBoardDto> getBestItems() {
		
		return pboardService.getBestItems();
	}

	@ResponseBody
	@RequestMapping(value = "/api/pboard/{pcode}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String pcode) {
		
		pboardService.delete(pcode);
	}

}
