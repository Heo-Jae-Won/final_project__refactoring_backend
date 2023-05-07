package com.example.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.UserDto;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {

	private final UserService userService;

	@RequestMapping("/{userId}")
	public UserDto read(@PathVariable String userId) {
		return userService.read(userId);
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public int findPw(HttpServletResponse response, @RequestBody UserDto vo) throws Exception {
		int result = userService.findPw(response, vo);

		return result;
	}

	@RequestMapping(value = "/data/{userNickname}")
	public int checkDuplicatedUnickname(@PathVariable String userNickname) {
		int result = userService.checkDuplicatedUncikname(userNickname);

		return result;
	}

	@RequestMapping(value = "/restore", method = RequestMethod.POST)
	public void restore(@Valid UserDto restoreVO) {
		userService.restore(restoreVO);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public int login(@RequestBody @Valid UserDto loginVO) {
		int result = userService.userLoginStatus(loginVO);
		return result;
	}

	@RequestMapping(value = "/id/{userEmail}/{userName}")
	public String findUserId(@PathVariable String userEmail, @PathVariable String userName) {
		String search = userService.searchId(userEmail, userName);
		return search;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestPart(value = "data") UserDto updateVO,
			@RequestPart(value = "file", required = false) MultipartFile multi) throws Exception {

		userService.update(multi, updateVO);
	}

	@RequestMapping(value = "/status", method = RequestMethod.PUT)
	public void deactivate(@RequestBody String userId) {

		userService.deactivate(userId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void insert(@RequestPart(value = "data") UserDto insertVO, @RequestPart(value = "file") MultipartFile multi)
			throws Exception {
		userService.insert(multi, insertVO);

	}

	@RequestMapping(value = "/password", method = RequestMethod.PATCH)
	public void updatePw(@RequestBody @Valid UserDto updatepwVO) throws Exception {
		userService.updatePw(updatepwVO);
	}

	@RequestMapping("/authentication/{userTel}")
	public String sendAuthSMS(@PathVariable String userTel) {
		String authNum = userService.sendAuthSMS(userTel);

		return authNum;
	}

}
