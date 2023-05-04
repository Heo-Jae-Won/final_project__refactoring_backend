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
import com.example.exception.ErrorEnum;
import com.example.exception.ServiceException;

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
			throw new ServiceException(ErrorEnum.NO_PRODUCT);
		}

		return productBoardDao.read(productCode);
	}

	public ProductBoardDto getProductInfo(String productCode) throws Exception {

		if (productBoardDao.read(productCode) == null) {
			throw new Exception("불법침입자");
		}

		return productBoardDao.read(productCode);
	}

	public void insert(ProductBoardDto insertDto, MultipartFile multi) throws Exception {
		if (multi.isEmpty()) {
			throw new ServiceException(ErrorEnum.NO_CONTENT);
		}

		String path = "/upload/";

		String contentType = multi.getContentType();
		if (!contentType.contains("image/png") && !contentType.contains("image/jpeg")) {
			throw new ServiceException(ErrorEnum.NOT_ACCEPTED_TYPE_IMAGE);
		}

		File newFile = new File(path + multi.getOriginalFilename());

		if (!newFile.exists()) {
			multi.transferTo(newFile);
		}
		insertDto.setProductImage(path + multi.getOriginalFilename());
		productBoardDao.insert(insertDto);
	};

	public void update(ProductBoardDto updateVO, MultipartFile multi) throws Exception {

		// file path designate
		if (multi.isEmpty()) {
			productBoardDao.update(updateVO);
			return;
		}

		String path = "/upload/";

		String contentType = multi.getContentType();
		if (!contentType.contains("image/png") && !contentType.contains("image/jpeg")) {
			throw new ServiceException(ErrorEnum.NOT_ACCEPTED_TYPE_IMAGE);
		}

		// multi -> thumnail
		File newFile = new File(path + multi.getOriginalFilename());
		if (!newFile.exists()) {
			FileOutputStream thumnail = new FileOutputStream(newFile);
			Thumbnailator.createThumbnail(multi.getInputStream(), thumnail, 300, 300);
			thumnail.close();
		}

		// update DB
		updateVO.setProductImage(path + multi.getOriginalFilename());
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

	public void updateSold(int productCode) {
		productBoardDao.updateSold(productCode);
	}

//	public int readPcondition(int productCode) {
//		int productStatus = productBoardDao.readPcondition(productCode);
//
//		return productStatus;
//	}

	public String readPwriterId(int productCode) {
		String productWriterId = productBoardDao.readPwriterId(productCode);

		return productWriterId;
	}

	public String readPwriter(int productCode) {
		String productWriter = productBoardDao.readPwriter(productCode);

		return productWriter;
	}

}
