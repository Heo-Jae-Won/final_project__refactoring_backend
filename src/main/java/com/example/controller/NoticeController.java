package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.NoticeDto;
import com.example.response.NoticeListResponse;
import com.example.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	
	@RequestMapping(value = "/notice/list", method = RequestMethod.GET)
	public String list(int page, int num, String searchType, String keyword, Model model) {
		List<NoticeDto> list = noticeService.getList(page, num, searchType, keyword);
		int last = (noticeService.getTotal(searchType, keyword) % 6) == 0
				? (noticeService.getTotal(searchType, keyword) / 6)
				: (noticeService.getTotal(searchType, keyword) / 6) + 1;
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("keyword", keyword);
		model.addAttribute("num", 6);
		model.addAttribute("last", last);

		return "notice/list";
	}

	@RequestMapping(value = "/notice/read/{noticeCode}", method = RequestMethod.GET)
	public String read(Model model, @PathVariable String noticeCode) {
		model.addAttribute("noticeDto", noticeService.read(noticeCode));

		return "notice/read";
	}

	@RequestMapping(value = "/notice/insert", method = RequestMethod.GET)
	public String insert(Model model, NoticeDto vo, HttpSession session) {
		model.addAttribute("adminId", session.getAttribute("adminId"));

		return "notice/insert";
	}

	@ResponseBody
	@RequestMapping("/api/notice")
	public NoticeListResponse list(int page, int num, String searchType, String keyword) throws Exception {
		NoticeListResponse noticeListResponse = new NoticeListResponse();
		noticeListResponse.setNoticeList(noticeService.getList(page, num, searchType, keyword));
		noticeListResponse.setNoticeListTotal(noticeService.getTotal(searchType, keyword));

		return noticeListResponse;
	};
	
	@ResponseBody
	@RequestMapping(value = "/api/notice", method = RequestMethod.POST)
	public void insert(@RequestBody NoticeDto vo) {
		noticeService.insert(vo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/notice/{ncode}")
	public NoticeDto read(@PathVariable String ncode) {
		return noticeService.read(ncode);
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/notice/{ncode}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String ncode) {
		noticeService.delete(ncode);
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/notice", method = RequestMethod.PATCH)
	public void update(@RequestBody NoticeDto vo) {
		noticeService.update(vo);
	}

}
