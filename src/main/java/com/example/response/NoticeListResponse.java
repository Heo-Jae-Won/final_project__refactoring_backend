package com.example.response;

import java.util.List;

import com.example.dto.NoticeDto;

import lombok.Data;

@Data
public class NoticeListResponse {
	private List<NoticeDto> noticeList;
	private int noticeListTotal;

}
