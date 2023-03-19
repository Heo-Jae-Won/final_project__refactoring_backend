package com.example.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.dto.ProductBoardDto;
import com.example.response.ProductListResponse;
import com.example.service.ProductBoardService;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins ="http://localhost:3000")
public class PboardController {

	@Autowired
	private final ProductBoardService pboardService;
	
	@RequestMapping(value = "/pboard/list", method=RequestMethod.GET)
	public String list(Model model, int page, int num, String searchType, String keyword) throws Exception {
		int last = (pboardService.getTotal(searchType, keyword) % 6) == 0
				? (pboardService.getTotal(searchType, keyword) / 6)
				: (pboardService.getTotal(searchType, keyword) / 6) + 1;
		model.addAttribute("last", last);
		model.addAttribute("page", page);
		model.addAttribute("num", 6);
		model.addAttribute("keyword", keyword);
		model.addAttribute("list", pboardService.getList(page, 6, searchType, keyword));
		return "pboard/list";
	}

	@RequestMapping(value = "/pboard/read/{pcode}", method = RequestMethod.GET)
	public String read(Model model, @PathVariable String pcode) {
		model.addAttribute("pboardDto", pboardService.read(pcode));
		return "pboard/read";
	}

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
		if (multi.getFile("file") == null) {
			pboardService.update(updateVO);
			return;
		}

		String path = "/upload/project/";
		MultipartFile file = multi.getFile("file");

		String contentType = file.getContentType();
		if (!contentType.contains("image/png") && !contentType.contains("image/jpeg")) {
			throw new Exception("imagefile only accepted for jpeg,png");
		}

		// file -> thumnail
		File newFile = new File(path + file.getOriginalFilename());
		if (!newFile.exists()) {
			FileOutputStream thumnail = new FileOutputStream(newFile);
			Thumbnailator.createThumbnail(file.getInputStream(), thumnail, 300, 300);
			thumnail.close();
		}

		// update DB
		updateVO.setPimage("/upload/project/" + file.getOriginalFilename());
		pboardService.update(updateVO);
	};

	@ResponseBody
	@RequestMapping("/api/pboard/{pcode}")
	public ProductBoardDto readviewcnt(@PathVariable String pcode) {

		if (pboardService.read(pcode) == null) {
			throw new Error("불법침입자");
		}

		return pboardService.read(pcode);
	};

	@ResponseBody
	@RequestMapping("/api/pboard/data/{pcode}")
	public ProductBoardDto read(@PathVariable String pcode) {
		if (pboardService.read(pcode) == null) {
			return null;
		}

		return pboardService.read(pcode);
	};

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void insert(@Valid ProductBoardDto insertVO, MultipartHttpServletRequest multi) throws Exception {
		if (multi.getFile("file") == null) {
			throw new Error("no Image file");
		}

		String path = "c:/upload/project/";
		MultipartFile file = multi.getFile("file");

		String contentType = file.getContentType();
		if (!contentType.contains("image/png") && !contentType.contains("image/jpeg")) {
			throw new Error("imagefile only accepted for jpeg,png");
		}

		File newFile = new File(path + file.getOriginalFilename());

		if (!newFile.exists()) {
			file.transferTo(newFile);
		}
		insertVO.setPimage("/upload/project/" + file.getOriginalFilename());

		pboardService.insert(insertVO);
	};

	@ResponseBody
	@RequestMapping("/api/pboard/best")
	public List<ProductBoardDto> best() {
		return pboardService.best();
	}

	@ResponseBody
	@RequestMapping(value = "/api/pboard/{pcode}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String pcode) {
		pboardService.delete(pcode);
	}

}
