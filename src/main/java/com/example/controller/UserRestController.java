package com.example.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.dto.UserDto;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserRestController {

	private final UserService userService;

	@RequestMapping("/{uid}")
	public UserDto read(@PathVariable String uid) {
		
		return userService.read(uid);
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public int findPw(HttpServletResponse response, @RequestBody UserDto vo) throws Exception {
		int result = userService.findPw(response, vo);

		return result;
	}

	@RequestMapping(value = "/data/{unickname}")
	public int checkDuplicatedUnickname(@PathVariable String unickname) {
		int result = userService.checkDuplicatedUncikname(unickname);

		return result;
	}

	@RequestMapping(value = "/restore", method = RequestMethod.POST)
	public void restore(UserDto restoreVO) {
		userService.restore(restoreVO);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public int login(@RequestBody UserDto loginVO) {
		int result = userService.userLoginStatus(loginVO);

		return result;
	}

	@RequestMapping(value = "/id/{uemail}/{uname}")
	public String search(@PathVariable String uemail, @PathVariable String uname) {
		String search = userService.searchId(uemail, uname);

		return search;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(MultipartHttpServletRequest multi, UserDto updateVO) throws Exception {

		userService.update(multi, updateVO);
	}

	@RequestMapping(value = "/condition", method = RequestMethod.PUT)
	public void deactivate(@RequestBody String uid) {
		
		userService.deactivate(uid);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void insert(MultipartHttpServletRequest multi, UserDto insertVO) throws Exception {
		userService.insert(multi, insertVO);

	}

	@RequestMapping(value = "/password", method = RequestMethod.PATCH)
	public void updatePw(@RequestBody UserDto updatepwVO) throws Exception {

		userService.updatePw(updatepwVO);
	}

	@RequestMapping("/authentification/{utel}")
	public String sendAuthSMS(@PathVariable String utel) {
		String authNum = userService.sendAuthSMS(utel);

		return authNum;
	}

}
