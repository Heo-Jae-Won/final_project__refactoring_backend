package com.example.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.UserDto;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	@RequestMapping("/{userId}")
	public UserDto read(@PathVariable String userId) {
		return userService.read(userId);
	}

	@PostMapping(value = "/password")
	public int findPw(HttpServletResponse response, @RequestBody UserDto vo) throws Exception {

		return userService.findPw(response, vo);
	}

	@RequestMapping(value = "/data/{userNickname}")
	public int checkDuplicatedUnickname(@PathVariable String userNickname) {

		return userService.checkDuplicatedUncikname(userNickname);
	}

	@PostMapping(value = "/restore")
	public void restore(@Valid UserDto restoreVO) {
		userService.restore(restoreVO);
	}

	@PostMapping(value = "/login")
	public int login(@RequestBody @Valid UserDto loginVO) {

		return userService.userLoginStatus(loginVO);
	}

	@RequestMapping(value = "/id/{userEmail}/{userName}")
	public String findUserId(@PathVariable String userEmail, @PathVariable String userName) {

		return userService.searchId(userEmail, userName);
	}

	@PostMapping(value = "/update")
	public void update(@RequestPart(value = "data") UserDto updateVO,
			@RequestPart(value = "file", required = false) MultipartFile multi) throws Exception {

		userService.update(multi, updateVO);
	}

	@PutMapping(value = "/status")
	public void deactivate(@RequestBody String userId) {

		userService.deactivate(userId);
	}

	@RequestMapping(value = "")
	public void insert(@RequestPart(value = "data") UserDto insertVO, @RequestPart(value = "file") MultipartFile multi)
			throws Exception {
		userService.insert(multi, insertVO);

	}

	@PatchMapping(value = "/password")
	public void updatePw(@RequestBody @Valid UserDto updatepwVO) throws Exception {
		userService.updatePw(updatepwVO);
	}

	@RequestMapping("/authentication/{userTel}")
	public String sendAuthSMS(@PathVariable String userTel) {

		return userService.sendAuthSMS(userTel);
	}

}
