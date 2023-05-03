package com.example.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorEnum {

	NO_PRODUCT("존재하지 않는 상품입니다."),
	NO_CONTENT("파일이 없습니다"),
	NOT_ACCEPTED_TYPE_IMAGE("jpeg, png 확장자로 올려주세요"),
	VALIDATED_FALSE("주어진 양식에 맞춰 작성해주세요");

	private final String message;
}
