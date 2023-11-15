package com.example.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.NoticeDto;
import com.example.response.NoticeListResponse;
import com.example.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;

	@RequestMapping("/api/notice")
	public NoticeListResponse list(int page, int num, String searchType, String keyword) throws Exception {
		NoticeListResponse noticeListResponse = new NoticeListResponse();
		noticeListResponse.setNoticeList(noticeService.getList(page, num, searchType, keyword));
		noticeListResponse.setNoticeListTotal(noticeService.getTotal(searchType, keyword));

		return noticeListResponse;
	}
	
	@PostMapping(value = "/api/notice")
	public void insert(@RequestBody NoticeDto insertDTO) {
		noticeService.insert(insertDTO);
	}
	
	@RequestMapping(value = "/api/notice/{noticeCode}")
	public NoticeDto read(@PathVariable String noticeCode) {
		return noticeService.read(noticeCode);
	}
	
	@DeleteMapping(value = "/api/notice/{noticeCode}")
	public void delete(@PathVariable String noticeCode) {
		noticeService.delete(noticeCode);
	}
	
	@PatchMapping(value = "/api/notice")
	public void update(@RequestBody NoticeDto updateDTO) {
		noticeService.update(updateDTO);
	}

}
