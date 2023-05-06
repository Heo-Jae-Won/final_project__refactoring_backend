package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.NoticeDto;
import com.example.mapper.NoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeMapper noticeMapper;

	public List<NoticeDto> getList(int page, int num, String searchType, String keyword) {
		int start = (page - 1) * num;
		List<NoticeDto> noticeList = noticeMapper.list(start, num, searchType, keyword);

		return noticeList;
	}

	public int getTotal(String searchType, String keyword) {
		int noticeListTotal = noticeMapper.getTotal(searchType, keyword);

		return noticeListTotal;
	}

	public void insert(NoticeDto insertDto) {
		noticeMapper.insert(insertDto);
	}

	public NoticeDto read(String noticeCode) {
		NoticeDto noticeDto = noticeMapper.read(noticeCode);

		return noticeDto;
	}

	public void updateViewcnt(String noticeCode) {
		noticeMapper.updateViewcnt(noticeCode);

	}

	public void update(NoticeDto updateDto) {
		noticeMapper.update(updateDto);
	}

	public void delete(String noticeCode) {
		noticeMapper.delete(noticeCode);
	}

}
