package com.example.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.binding.BindingException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.dto.ExceptionDto;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class ExceptionAdvice {

	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR) // org.apache로 import하면 안됨.
	public ExceptionDto excepion(HttpServletRequest request, ServiceException serviceException) {
		ExceptionDto exceptionDto = new ExceptionDto();
		exceptionDto.setRequestURI(request.getRequestURI());
		exceptionDto.setMessage(serviceException.getMessage());
		return exceptionDto;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class) // requestBody
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ExceptionDto requestBodyException(HttpServletRequest request,
			MethodArgumentNotValidException methodArgumentNotValidException) {

		BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();

		log.info("들어옴?");
		StringBuilder stringBuilder = new StringBuilder();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			stringBuilder.append(fieldError.getField()).append(":");
			stringBuilder.append(fieldError.getDefaultMessage());
			stringBuilder.append(", ");
		}

		ExceptionDto exceptionDto = new ExceptionDto();
		exceptionDto.setRequestURI(request.getRequestURI());
		exceptionDto.setMessage(stringBuilder.toString());
		return exceptionDto;
	}
}
