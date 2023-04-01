package com.example.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserDao;
import com.example.dto.UserDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {
	private final UserDao userDao;
	
	public int login(UserDto userDto) {
		return 0;
	}
	
	public void logout(HttpSession session) {
	}

}
