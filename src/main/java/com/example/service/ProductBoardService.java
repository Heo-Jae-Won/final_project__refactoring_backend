package com.example.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.ProductBoardDto;
import com.example.exception.ErrorEnum;
import com.example.exception.ServiceException;
import com.example.mapper.ProductBoardMapper;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductBoardService {

	private final ProductBoardMapper productBoardMapper;

	public ProductBoardDto read(String productCode) throws Exception {
		productBoardMapper.updateViewcnt(productCode);

		if (productBoardMapper.read(productCode) == null) {
			throw new ServiceException(ErrorEnum.NO_PRODUCT);
		}

		return productBoardMapper.read(productCode);
	}

	public ProductBoardDto getProductInfo(String productCode) throws Exception {

		if (productBoardMapper.read(productCode) == null) {
			throw new Exception("불법침입자");
		}

		return productBoardMapper.read(productCode);
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
		productBoardMapper.insert(insertDto);
	};

	public void update(ProductBoardDto updateVO, MultipartFile multi) throws Exception {

		// file path designate isEmpty()로는 NPE처리안됨.
		if (multi == null) {
			productBoardMapper.update(updateVO);
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
		productBoardMapper.update(updateVO);
	};

	public int getTotal(String searchType, String keyword) {
		int pboardListToal = productBoardMapper.getTotal(searchType, keyword);

		return pboardListToal;
	}

	public void delete(String productCode) {
		productBoardMapper.delete(productCode);
	}

	public List<ProductBoardDto> getBestItems() {
		List<ProductBoardDto> prouductBestListResponse = productBoardMapper.best();

		return prouductBestListResponse;
	}

	public List<ProductBoardDto> getList(int page, int num, String searchType, String keyword) throws Exception {
		int start = (page - 1) * num;
		List<ProductBoardDto> productListResponse = productBoardMapper.list(start, num, searchType, keyword);

		return productListResponse;
	};

	public void updateLove() {
		productBoardMapper.updateLove();
	}

	public void updateSold(int productCode) {
		productBoardMapper.updateSold(productCode);
	}

//	public int readPcondition(int productCode) {
//		int productStatus = productBoardMapper.readPcondition(productCode);
//
//		return productStatus;
//	}

	public String readPwriterId(int productCode) {
		String productWriterId = productBoardMapper.readPwriterId(productCode);

		return productWriterId;
	}

	public String readPwriter(int productCode) {
		String productWriter = productBoardMapper.readPwriter(productCode);

		return productWriter;
	}

}
