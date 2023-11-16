package com.example.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.UserDto;
import com.example.enumtype.User;
import com.example.exception.ErrorEnum;
import com.example.exception.ServiceException;
import com.example.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	public UserDto read(String userId) {
		UserDto userDto = userMapper.read(userId);
		return userDto;
	}

	public void update(MultipartFile multi, UserDto updateVO) throws Exception {
		if (multi == null) {
			userMapper.update(updateVO);
			return;
		}

		String path = "/upload/";

		String contentType = multi.getContentType();
		if (!contentType.contains("image/png") && !contentType.contains("image/jpeg")) {
			throw new ServiceException(ErrorEnum.NOT_ACCEPTED_TYPE_IMAGE);
		}

		File newFile = new File(path + multi.getOriginalFilename());
		if (!newFile.exists()) {
			multi.transferTo(newFile);
		}
		updateVO.setUserProfile(path + multi.getOriginalFilename());

		userMapper.update(updateVO);
	}

	public void insert(MultipartFile multi, UserDto insertVO) throws Exception {
		if (multi == null) {
			throw new ServiceException(ErrorEnum.NO_CONTENT);
		}

		String path = "/upload/";

		String contentType = multi.getContentType();
		if (!contentType.contains("image/png") && !contentType.contains("image/jpeg")) {
			throw new ServiceException(ErrorEnum.NOT_ACCEPTED_TYPE_IMAGE);
		}

		File newFile = new File(path + multi.getOriginalFilename());
		if (!newFile.exists()) {
			multi.transferTo(newFile);
		}
		insertVO.setUserProfile(path + multi.getOriginalFilename());

		insertVO.setUserPass(passwordEncoder.encode(insertVO.getUserPass()));
		userMapper.insert(insertVO);
	}

	public void deactivate(String userId) {
		userMapper.deactivate(userId);
	}

	public void restore(UserDto restoreVO) {
		userMapper.restore(restoreVO);

	}

	public void updatePw(UserDto updatepwVO) throws Exception {

		updatepwVO.setUserPass(passwordEncoder.encode(updatepwVO.getUserPass()));

		userMapper.updatePw(updatepwVO);
	}

	public void updateUpoint(String receiver) {
		userMapper.updateUpoint(receiver);

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
			System.out.println("userMapperImpl - authPhoneNumber/errormessgae : " + e.getMessage());
			System.out.println("userMapperImpl - authPhoneNumbe/errorcode : " + e.getCode());
		}

		return authNum;
	}

	public void sendEmail(UserDto vo, String div) throws Exception {
		final String bodyEncoding = "UTF-8"; // 콘텐츠 인코딩

		String subject = "비밀번호";
		String fromEmail = "ruincraim@gmail.com";
		String fromUsername = "SYSTEM MANAGER";
		String toEmail = vo.getUserEmail(); // 콤마(,)로 여러개 나열

		final String username = "ruincraim@gmail.com";
		final String password = "kpshicypeqmigmat";

		// 메일에 출력할 텍스트
		StringBuffer sb = new StringBuffer();
		sb.append("<div align='center' style='border:1px solid black; font-family:verdana'>");
		sb.append("<h3 style='color: blue;'>");
		sb.append(vo.getUserId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>");
		sb.append("<p>임시 비밀번호 : ");
		sb.append(vo.getUserPass() + "</p></div>");
		String html = sb.toString();

		// 메일 옵션 설정
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		// 아래 두 줄 추가했더니 javax.net.ssl.SSLHandshakeException: No appropriate protocol
		// (protocol is disabled or cipher suites are inappropriate
		// 오류가 사라짐. TLS를 뭔가 설정해야 하는듯.
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");

		props.put("mail.smtp.quitwait", "false");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		// Mail Server

		// user email address
		try {
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			};

			// 메일 세션 생성
			Session session = Session.getInstance(props, auth);

			// 메일 송/수신 옵션 설정. Message가 coolsms에서도 쓰여서 package명 전체를 가져와야 한다.
			javax.mail.Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail, fromUsername));
			message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false));
			message.setSubject(subject);
			message.setSentDate(new Date());

			// 메일 콘텐츠 설정
			Multipart mParts = new MimeMultipart();
			MimeBodyPart mTextPart = new MimeBodyPart();
			MimeBodyPart mFilePart = null;

			// 메일 콘텐츠 - 내용
			mTextPart.setText(html, bodyEncoding, "html");
			mParts.addBodyPart(mTextPart);

			// 메일 콘텐츠 설정
			message.setContent(mParts);

			// MIME 타입 설정
			MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
			CommandMap.setDefaultCommandMap(MailcapCmdMap);

			// 메일 발송
			Transport.send(message);
		} catch (Exception e) {
			System.out.println("fail sendmail : " + e);
		}
	}

	// find password
	public int findPw(HttpServletResponse response, UserDto vo) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		UserDto userInfo = userMapper.read(vo.getUserId());
		int result;

		// no registered id
		if (userInfo == null || !vo.getUserEmail().equals(userInfo.getUserEmail()))
			result = User.NOT_VALID.getStatus();

		// no registered email

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
			System.out.println(upass);
			vo.setUserPass(upass);
			sendEmail(vo, "findpw");

			// password update
			vo.setUserPass(passwordEncoder.encode(upass));
			userMapper.updatePw(vo);

			// send password to registered email
			result = User.PERMITTED.getStatus();
		}
		return result;
	}

	public int checkDuplicatedUncikname(String unickname) {
		String CheckUnickname = userMapper.readUnickname(unickname);
		int result = 0;

		// when result=1, no duplicate
		if (!StringUtils.hasText(CheckUnickname)) {
			result = User.NOT_DUPLICATED.getStatus();
		}
		return result;
	}

	public String searchId(String uemail, String uname) {
		String search = userMapper.readUemail(uemail, uname);

		if (!StringUtils.hasText(search)) {
			return "";
		}
		return search;
	}

	public int userLoginStatus(UserDto loginDto) {
		int result;
		UserDto ReadVO = read(loginDto.getUserId());

		if (ReadVO == null || !passwordEncoder.matches(loginDto.getUserPass(), ReadVO.getUserPass())) {
			result = User.NOT_VALID.getStatus();
		} else if (ReadVO.getUserStatus().equals("0")) {
			result = User.DEACTIVATED.getStatus();
		} else {
			result = User.PERMITTED.getStatus();
		}
		return result;
	}

}
