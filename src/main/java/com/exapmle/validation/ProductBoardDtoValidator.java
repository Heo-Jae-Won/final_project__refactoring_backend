package com.exapmle.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.example.dto.ProductBoardDto;

@Component
public class ProductBoardDtoValidator implements ConstraintValidator<ValidProductBoardDto, String> {

	public void initialize(ProductBoardDtoValidator constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value == null) {
			return false;
		}
		return value.length() <= 15;
		// Custom validation logic for your DTO
	}

}
