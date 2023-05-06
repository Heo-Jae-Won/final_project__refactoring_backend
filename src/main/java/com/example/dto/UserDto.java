package com.example.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDto {
	
	private int userCode;
	@NotBlank @Max(15)
	private String userId;
	@NotBlank
	private String userPass;
	@NotBlank
	private String userName;
	@NotBlank @Max(60)
	private String userNickname;
	@NotBlank 
	@Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
	private String userEmail;
	@Pattern(regexp="\\d{3}-\\d{3,4}-\\d{4}")
	private String userTel;
	@NotBlank
	private String userAddress;
	@NotBlank
	private String userGender;
	private String userProfile;
	@NotBlank
	private String userBirth;
	private String userStatus;
	private double userPoint;
	private String userRole;

}
