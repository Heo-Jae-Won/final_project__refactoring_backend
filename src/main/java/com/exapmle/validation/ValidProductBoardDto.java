package com.exapmle.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ProductBoardDtoValidator.class })
public @interface ValidProductBoardDto {
	String message() default "요구 사항을 만족해야 합니다!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
