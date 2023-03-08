package com.example.controller;

import java.io.File;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.dao.UserDAO;
import com.example.domain.UserVO;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
	
	 private final UserDAO userDao;
	 private final PasswordEncoder encoder;
	 private final UserService uservice;
	
	 @Autowired
	 public UserRestController(UserDAO userDao, PasswordEncoder encoder, UserService uservice) {
		 this.userDao=userDao;
		 this.encoder=encoder;
		 this.uservice=uservice;
	 }
	
	
	Pattern patternTel = Pattern.compile("\\d{3}-\\d{3,4}-\\d{4}");

	@RequestMapping("/{uid}")
	public UserVO read(@PathVariable String uid) {
		return userDao.read(uid);
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public int findPwPOST(HttpServletResponse response, @RequestBody UserVO vo) throws Exception {
		int result = uservice.findPw(response, vo);
		return result;
	}

	@RequestMapping(value = "/data/{unickname}")
	public int chk(@PathVariable String unickname) {
		String CheckUnickname = userDao.readUnickname(unickname);
		int result = 0;

		// when result=1, no duplicate
		if (CheckUnickname == null) {
			result = 1;
		}

		return result;
	}

	@RequestMapping(value = "/restore", method = RequestMethod.POST)
	public void restore(UserVO restoreVO) {
		userDao.restore(restoreVO);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public int login(@RequestBody UserVO loginVO) {
		
		
		int result;
		UserVO ReadVO = userDao.read(loginVO.getUid());

		if (ReadVO == null) {
			result = 0;
		} else if (ReadVO.getUcondition().equals("0")) {
			result = 1;
		} else if (encoder.matches(loginVO.getUpass(), ReadVO.getUpass())) {
			result = 2;
		} else {
			result = 3;
		}
		return result;
	}

	@RequestMapping(value = "/id/{uemail}/{uname}")
	public String search(@PathVariable String uemail, @PathVariable String uname) {
		String search = userDao.readUemail(uemail, uname);

		// when result=1, no duplicate
		if (search == null) {
			return "";
		}

		return search;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(MultipartHttpServletRequest multi, UserVO updateVO) throws Exception {

		if (multi.getFile("file") == null) {
			userDao.update(updateVO);
			return;
		}

		String path = "c:/upload/project/";
		MultipartFile file = multi.getFile("file");

		String contentType = file.getContentType();
		if (!contentType.contains("image/png") && !contentType.contains("image/jpeg")) {
			throw new Exception("imagefile only accepted for jpeg,png");
		}

		File newFile = new File(path + file.getOriginalFilename());
		if (!newFile.exists()) {
			file.transferTo(newFile);
		}
		updateVO.setUprofile("/upload/project/" + file.getOriginalFilename());

		Matcher matcherTel = patternTel.matcher(updateVO.getUtel());
		if (matcherTel.matches() == false) {
			throw new Exception("does not satisfy tel pattern condition");
		}

		Pattern patternEmail = Pattern
				.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");
		Matcher matcherEmail = patternEmail.matcher(updateVO.getUemail());
		if (matcherEmail.matches() == false) {
			throw new Exception("does not satisfy condition");
		}
		userDao.update(updateVO);
	}

	@RequestMapping(value = "/condition", method = RequestMethod.PUT)
	public void deactivate(@RequestBody String uid) {
		userDao.deactivate(uid);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void insert(MultipartHttpServletRequest multi, UserVO insertVO) throws Exception {
		if (multi.getFile("file") == null) {
			throw new Error("no Image file");
		}

		String path = "c:/upload/project/";
		MultipartFile file = multi.getFile("file");

		String contentType = file.getContentType();
		if (!contentType.contains("image/png") && !contentType.contains("image/jpeg")) {
			throw new Exception("imagefile only accepted for jpeg,png");
		}

		File newFile = new File(path + file.getOriginalFilename());
		if (!newFile.exists()) {
			file.transferTo(newFile);
		}
		insertVO.setUprofile("/upload/project/" + file.getOriginalFilename());

		Pattern patternPassword = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,10}$");
		Matcher matcherPassword = patternPassword.matcher(insertVO.getUpass());
		if (matcherPassword.matches() == false) {
			throw new Exception("does not satisfy password pattern");
		}

		insertVO.setUpass(encoder.encode(insertVO.getUpass()));

		Matcher matcherTel = patternTel.matcher(insertVO.getUtel());
		if (matcherTel.matches() == false) {
			throw new Exception("does not satisfy tel pattern");
		}

		Pattern patternEmail = Pattern
				.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");
		Matcher matcherEmail = patternEmail.matcher(insertVO.getUemail());
		if (matcherEmail.matches() == false) {
			throw new Exception("does not satisfy email pattern");
		}

		userDao.insert(insertVO);

	}

	@RequestMapping(value = "/password", method = RequestMethod.PATCH)
	public int updatePw(@RequestBody UserVO updatepwVO) throws Exception {

		Pattern patternPassword = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,10}$");
		Matcher matcherPassword = patternPassword.matcher(updatepwVO.getUpass());
		if (matcherPassword.matches() == false) {
			throw new Exception("does not satisfy password pattern");
		}

		updatepwVO.setUpass(encoder.encode(updatepwVO.getUpass()));
		return userDao.updatePw(updatepwVO);
	}

	@RequestMapping("/authentification/{utel}")
	public String sendAuthSMS(@PathVariable String utel) {
		String authNum = "";

		// 10 digits six number
		for (int i = 0; i < 6; i++) {
			String random = Integer.toString(new Random().nextInt(10));
			authNum += random;
		}
		userDao.authPhoneNumber(utel, authNum);

		return authNum;
	}

}
