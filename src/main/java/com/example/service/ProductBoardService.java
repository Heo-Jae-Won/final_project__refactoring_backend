package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ProductBoardDao;
import com.example.dto.ProductBoardDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductBoardService {

	private final ProductBoardDao pboardDao;

	public ProductBoardDto read(String productCode) {
		pboardDao.updateViewcnt(productCode);

		return pboardDao.read(productCode);
	}

	public void insert(ProductBoardDto vo) {
		pboardDao.insert(vo);
	};

	public void update(ProductBoardDto vo) {
		pboardDao.update(vo);
	};

	public int getTotal(String searchType, String keyword) {
		int pboardListToal = pboardDao.getTotal(searchType, keyword);

		return pboardListToal;
	}

	public void delete(String productCode) {
		pboardDao.delete(productCode);
	}

	public List<ProductBoardDto> best() {
		List<ProductBoardDto> prouductBestListResponse = pboardDao.best();

		return prouductBestListResponse;
	}

	public List<ProductBoardDto> getList(int page, int num, String searchType, String keyword) throws Exception {
		List<ProductBoardDto> productListResponse = pboardDao.getList(page, num, searchType, keyword);

		return productListResponse;
	};

	public void updateLove() {
		pboardDao.updateLove();
	}

	public void updateSold(String productCode) {
		pboardDao.updateSold(productCode);
	}

	public int readPcondition(String productCode) {
		int productStatus = pboardDao.readPcondition(productCode);

		return productStatus;
	}

	public String readPwriterId(String productCode) {
		String productWriterId = pboardDao.readPwriterId(productCode);

		return productWriterId;
	}

	public String readPwriter(String productCode) {
		String productWriter = pboardDao.readPwriter(productCode);

		return productWriter;
	}

}
