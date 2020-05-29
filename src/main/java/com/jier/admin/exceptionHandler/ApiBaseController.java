package com.jier.admin.exceptionHandler;



import com.jier.admin.entity.LayUITable;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Restful类API
 * @author liuye
 * @since 1.0
 */
@ControllerAdvice
public  class ApiBaseController {


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public LayUITable<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		//ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		// 注意哦，这里返回类型是自定义响应体
		return LayUITable.responseData(1001, "参数校验失败", 0,allErrors);
	}

	@ExceptionHandler(value=RuntimeException.class)
	@ResponseBody
	public LayUITable<String> runtimeExceptionHandler(RuntimeException e, HttpServletResponse response){


		return LayUITable.responseData(1002, e.getMessage());
	}
	@ExceptionHandler(value= BindException.class)
	@ResponseBody
	public LayUITable bindExceptionHandler(BindException e, HttpServletResponse response){

		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		return LayUITable.responseData(1004, e.getMessage(),0,allErrors);
	}

	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public LayUITable exceptionHandler(Exception e, HttpServletResponse response){

		return LayUITable.responseData(1003, e.getMessage());
	}
}
