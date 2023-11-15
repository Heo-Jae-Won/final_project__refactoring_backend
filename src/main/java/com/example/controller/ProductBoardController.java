package com.example.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.ProductBoardDto;
import com.example.response.ProductListResponse;
import com.example.service.ProductBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductBoardController {

	private final ProductBoardService pboardService;

	@GetMapping("/api/productBoard")
	public ProductListResponse list(@Param("page") int page, @Param("num") int num,
			@Param("searchType") String searchType, @Param("keyword") String keyword) throws Exception {
		ProductListResponse productListResponse = new ProductListResponse();
		productListResponse.setProductList(pboardService.getList(page, num, searchType, keyword));
		productListResponse.setProductListTotal(pboardService.getTotal(searchType, keyword));

		return productListResponse;
	}


	@PutMapping(value = "/api/productBoard/update")
	public void update(@RequestPart(value = "data") ProductBoardDto updateVO,
			@RequestPart(value = "file", required = false) MultipartFile multi) throws Exception {

		pboardService.update(updateVO, multi);
	}

	@GetMapping("/api/productBoard/{productCode}")
	public ProductBoardDto readviewcnt(@PathVariable String productCode) throws Exception {
		return pboardService.read(productCode);
	}

	@GetMapping("/api/productBoard/data/{productCode}")
	public ProductBoardDto read(@PathVariable String productCode) throws Exception {

		return pboardService.getProductInfo(productCode);
	}

	@PostMapping(value = "/api/productBoard")
	public void insert(@Validated @RequestPart(value = "data") ProductBoardDto insertVO,
			@RequestPart(value = "file") MultipartFile multi) throws Exception {
		pboardService.insert(insertVO, multi);
	}

	@GetMapping("/api/productBoard/best")
	public List<ProductBoardDto> getBestItems() {

		return pboardService.getBestItems();
	}

	@DeleteMapping(value = "/api/productBoard/{productCode}")
	public void delete(@PathVariable String productCode) {

		pboardService.delete(productCode);
	}

}
