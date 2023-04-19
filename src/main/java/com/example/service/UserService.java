package com.example.service;

import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.dao.UserDao;
import com.example.dto.UserDto;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;
	Pattern patternTel = Pattern.compile("\\d{3}-\\d{3,4}-\\d{4}");

	public UserDto read(String userId) {
		UserDto userDto = userDao.read(userId);

		return userDto;
	}

	public void update(MultipartHttpServletRequest multi, UserDto updateVO) throws Exception {
		if (multi.getFile("file") == null) {
			userDao.update(updateVO);
			return;
		}

		String path = "/upload/project/";
		MultipartFile file = multi.getFile("file");

		String contentType = file.getContentType();
		if (!contentType.contains("image/png") && !contentType.contains("image/jpeg")) {
			throw new Exception("imagefile only accepted for jpeg,png");
		}

		File newFile = new File(path + file.getOriginalFilename());
		if (!newFile.exists()) {
			file.transferTo(newFile);
		}
		updateVO.setUserProfile("/upload/project/" + file.getOriginalFilename());

		Matcher matcherTel = patternTel.matcher(updateVO.getUserTel());
		if (matcherTel.matches() == false) {
			throw new Exception("does not satisfy tel pattern condition");
		}

		Pattern patternEmail = Pattern
				.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");
		Matcher matcherEmail = patternEmail.matcher(updateVO.getUserEmail());
		if (matcherEmail.matches() == false) {
			throw new Exception("does not satisfy condition");
		}
		userDao.update(updateVO);
	}

	public void insert(MultipartHttpServletRequest multi, UserDto insertVO) throws Exception {
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
		insertVO.setUserProfile("/upload/project/" + file.getOriginalFilename());

		Pattern patternPassword = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,10}$");
		Matcher matcherPassword = patternPassword.matcher(insertVO.getUserPass());
		if (matcherPassword.matches() == false) {
			throw new Exception("does not satisfy password pattern");
		}

		insertVO.setUserPass(passwordEncoder.encode(insertVO.getUserPass()));

		Matcher matcherTel = patternTel.matcher(insertVO.getUserTel());
		if (matcherTel.matches() == false) {
			throw new Exception("does not satisfy tel pattern");
		}

		Pattern patternEmail = Pattern
				.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");
		Matcher matcherEmail = patternEmail.matcher(insertVO.getUserEmail());
		if (matcherEmail.matches() == false) {
			throw new Exception("does not satisfy email pattern");
		}

		userDao.insert(insertVO);
	}

	public void deactivate(String userId) {
		userDao.deactivate(userId);
	}

	public void restore(UserDto restoreVO) {
		userDao.restore(restoreVO);

	}

	public void updatePw(UserDto updatepwVO) throws Exception {

		Pattern patternPassword = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,10}$");
		Matcher matcherPassword = patternPassword.matcher(updatepwVO.getUserPass());

		if (matcherPassword.matches() == false) {
			throw new Exception("does not satisfy password pattern");
		}

		updatepwVO.setUserPass(passwordEncoder.encode(updatepwVO.getUserPass()));

		userDao.updatePw(updatepwVO);
	}

	public void updateUpoint(String receiver) {
		userDao.updateUpoint(receiver);

	}

	public String sendAuthSMS(String utel) {
		String api_key = "NCSMCV56UK5PEOAS";
		String api_secret = "OGMHGG1T3JCBITC9FWHORKVFI8BLGDIW";
		Message coolSMS = new Message(api_key, api_secret);
		HashMap<String, String> params = new HashMap<>();

		String authNum = "";
		// 10 digits six number
		for (int i = 0; i < 6; i++) {
			String random = Integer.toString(new Random().nextInt(10));
			authNum += random;
		}

		params.put("to", utel);
		params.put("from", "010-7591-3370");
		params.put("type", "SMS");
		params.put("text", "인증번호는 [ " + authNum + " ]입니다.");
		params.put("app_version", "test app 1.2");

		try {
			coolSMS.send(params);
		} catch (CoolsmsException e) {
			System.out.println("UserDAOImpl - authPhoneNumber/errormessgae : " + e.getMessage());
			System.out.println("UserDAOImpl - authPhoneNumbe/errorcode : " + e.getCode());
		}

		return authNum;
	}

	public void sendEmail(UserDto vo, String div) throws Exception {
		// Mail Server
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "mullonmarket@gmail.com";
		String hostSMTPpwd = "btaa yqrx khuw gbqc";

		// from EMail
		String fromEmail = "okmarket@icia.com";
		String fromName = "허재원";
		String subject = "비밀번호";
		String msg = "test";

		if (div.equals("findpw")) {
			subject = "물론마켓 임시 비밀번호입니다";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += vo.getUserId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += vo.getUserPass() + "</p></div>";
		}

		// user email address
		String mail = vo.getUserEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465); // using naver, 587

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();

		} catch (Exception e) {
			System.out.println("fail sendmail : " + e);
		}
	}

	// find password
	public int findPw(HttpServletResponse response, UserDto vo) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		UserDto userInfo = userDao.read(vo.getUserId());
		int result = 0;

		// no registered id
		if (userInfo == null)
			result = 1;

		// no registered email
		else if (!vo.getUserEmail().equals(userInfo.getUserEmail()))
			result = 2;
		else {

			// temporary password generated
			String upass = "";
			int leftLimit = 97; // letter 'a'
			int rightLimit = 122; // letter 'z'
			int targetStringLength = 3;
			Random random = new Random();
			String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
			upass += generatedString;
			for (int i = 0; i < 2; i++)
				upass += (int) (Math.random() * 26) + 97;

			vo.setUserPass(upass);
			sendEmail(vo, "findpw");

			// password update
			vo.setUserPass(passwordEncoder.encode(upass));
			userDao.updatePw(vo);

			// send password to registered email
			result = 3;
		}
		return result;
	}

	public int checkDuplicatedUncikname(String unickname) {
		String CheckUnickname = userDao.readUnickname(unickname);
		int result = 0;

		// when result=1, no duplicate
		if (CheckUnickname == null) {
			result = 1;
		}
		return result;
	}

	public String searchId(String uemail, String uname) {
		String search = userDao.readUemail(uemail, uname);

		if (search == null) {
			return "";
		}
		return search;
	}

	public int userLoginStatus(UserDto loginDto) {
		int result;
		UserDto ReadVO = read(loginDto.getUserId());

		if (ReadVO == null) {
			result = 0;
		} else if (ReadVO.getUserStatus().equals("0")) {
			result = 1;
		} else if (passwordEncoder.matches(loginDto.getUserPass(), ReadVO.getUserPass())) {
			result = 2;
		} else {
			result = 3;
		}
		return result;
	}

}
