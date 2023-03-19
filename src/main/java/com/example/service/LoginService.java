package com.example.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AdminDao;
import com.example.dto.AdminLoginDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {
	private final AdminDao adminaDao;
	
	public int login(AdminLoginDto adminLoginDto, HttpSession session) {
		int result = 0;

		AdminLoginDto ReadVO = adminaDao.read(adminLoginDto.getAid());

		if (ReadVO == null) 
			throw new Error("No data from DB");
		

		// Login success
		if (ReadVO.getApass().equals(adminLoginDto.getApass())) {
			result = 1;
			session.setAttribute("adminId", adminLoginDto.getAid());
		}
		return result;
	}
	
	public void logout(HttpSession session) {
		session.removeAttribute("adminId");
	}

}
