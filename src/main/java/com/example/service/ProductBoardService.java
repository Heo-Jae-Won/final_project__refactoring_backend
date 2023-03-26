package com.example.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.dao.ProductBoardDao;
import com.example.dto.ProductBoardDto;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductBoardService {

	private final ProductBoardDao productBoardDao;

	public ProductBoardDto read(String productCode) throws Exception {
		productBoardDao.updateViewcnt(productCode);
		
		if (productBoardDao.read(productCode) == null) {
			throw new Exception("불법침입자");
		}

		return productBoardDao.read(productCode);
	}
	
	public ProductBoardDto getProductInfo(String productCode) throws Exception {
		
		if (productBoardDao.read(productCode) == null) {
			throw new Exception("불법침입자");
		}

		return productBoardDao.read(productCode);
	}

	public void insert(ProductBoardDto insertDto,MultipartHttpServletRequest multi) throws Exception {
		if (multi.getFile("file") == null) {
			throw new Exception("no Image file");
		}

		String path = "c:/upload/project/";
		MultipartFile file = multi.getFile("file");

		String contentType = file.getContentType();
		if (!contentType.contains("image/png") && !contentType.contains("image/jpeg")) {
			throw new Exception("imagefile only accepted for jpeg,png");
		}

		File newFile = new File(path + file.getOriginalFilename());

		if (!newFile.exists()) {
			file.transferTo(newFile);
		}
		insertDto.setPimage("/upload/project/" + file.getOriginalFilename());
		productBoardDao.insert(insertDto);
	};

	public void update(ProductBoardDto updateVO, MultipartHttpServletRequest multi) throws Exception {
		
		// file path designate
		if (multi.getFile("file") == null) {
			productBoardDao.update(updateVO);
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
		productBoardDao.update(updateVO);
	};

	public int getTotal(String searchType, String keyword) {
		int pboardListToal = productBoardDao.getTotal(searchType, keyword);

		return pboardListToal;
	}

	public void delete(String productCode) {
		productBoardDao.delete(productCode);
	}

	public List<ProductBoardDto> getBestItems() {
		List<ProductBoardDto> prouductBestListResponse = productBoardDao.best();

		return prouductBestListResponse;
	}

	public List<ProductBoardDto> getList(int page, int num, String searchType, String keyword) throws Exception {
		List<ProductBoardDto> productListResponse = productBoardDao.getList(page, num, searchType, keyword);

		return productListResponse;
	};

	public void updateLove() {
		productBoardDao.updateLove();
	}

	public void updateSold(String productCode) {
		productBoardDao.updateSold(productCode);
	}

	public int readPcondition(String productCode) {
		int productStatus = productBoardDao.readPcondition(productCode);

		return productStatus;
	}

	public String readPwriterId(String productCode) {
		String productWriterId = productBoardDao.readPwriterId(productCode);

		return productWriterId;
	}

	public String readPwriter(String productCode) {
		String productWriter = productBoardDao.readPwriter(productCode);

		return productWriter;
	}

}
