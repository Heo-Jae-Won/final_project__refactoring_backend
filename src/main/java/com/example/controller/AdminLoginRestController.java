package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AdminLoginDto;
import com.example.service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminLoginRestController {



	private final LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public int loginPost(AdminLoginDto loginVO, HttpSession session) {
		int loginStatus = loginService.login(loginVO, session);

		return loginStatus;

	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(HttpSession session) {
		
		loginService.logout(session);
	}

}
