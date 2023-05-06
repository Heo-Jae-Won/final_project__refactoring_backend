package com.example.enumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum User {
	NOT_VALID(0),
	PERMITTED(1),
	DEACTIVATED(2),
	NOT_DUPLICATED(3);
	
	private final int status;
	
}
