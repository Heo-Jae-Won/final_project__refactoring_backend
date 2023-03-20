package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.EventReplyDao;
import com.example.dto.EventReplyDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EventReplyService {

	private final EventReplyDao eventReplyDao;

	public void userDelete(int ercode) {
		eventReplyDao.delete(ercode);
		eventReplyDao.updateUserCondition(ercode);
	}

	public void adminDelete(int ercode) {
		eventReplyDao.adminDelete(ercode);
		eventReplyDao.updateAdminCondition(ercode);
	}

	public List<EventReplyDto> list(int page, int num, int ecode) {
		List<EventReplyDto> eventReplyList = eventReplyDao.list(page, num, ecode);

		return eventReplyList;
	}

	public void update(EventReplyDto eventReplyDto) {
		eventReplyDao.update(eventReplyDto);

	}

	public void insert(EventReplyDto eventReplyDto) {
		eventReplyDao.insert(eventReplyDto);
	}

	public void delete(int eventReplyCode) {
		eventReplyDao.delete(eventReplyCode);
	}

	public int getTotal(int eventReplyCode) {
		int eventReplyTotal = eventReplyDao.getTotal(eventReplyCode);
		return eventReplyTotal;

	}

	public void allDelete(int eventCode) {
		eventReplyDao.allDelete(eventCode);

	}

	public void updateUserCondition(int eventReplyCode) {
		eventReplyDao.updateUserCondition(eventReplyCode);
	}

	public void updateAdminCondition(int eventReplyCode) {
		eventReplyDao.updateAdminCondition(eventReplyCode);
	}

}
