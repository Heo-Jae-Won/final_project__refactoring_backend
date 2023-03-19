package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.NoticeDao;
import com.example.dto.NoticeDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeDao noticeDao;

	public List<NoticeDto> getList(int page, int num, String searchType, String keyword) {
		List<NoticeDto> noticeList = noticeDao.getList(page, num, searchType, keyword);

		return noticeList;
	}

	public int getTotal(String searchType, String keyword) {
		int noticeListTotal = noticeDao.getTotal(searchType, keyword);

		return noticeListTotal;
	}

	public void insert(NoticeDto insertDto) {
		noticeDao.insert(insertDto);
	}

	public NoticeDto read(String noticeCode) {
		NoticeDto noticeDto = noticeDao.read(noticeCode);

		return noticeDto;
	}

	public void updateViewcnt(String noticeCode) {
		noticeDao.updateViewcnt(noticeCode);

	}

	public void update(NoticeDto updateDto) {
		noticeDao.update(updateDto);
	}

	public void delete(String noticeCode) {
		noticeDao.delete(noticeCode);
	}

}
