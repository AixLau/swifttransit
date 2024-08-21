package com.aix.swifttransit.common.mvc.advice;

import com.aix.swifttransit.common.core.util.CommonResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    // 处理 @Valid 或 @Validated 注解验证失败抛出的异常
    // 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public CommonResult<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        return CommonResult.failed(msg);
    }

    // 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler({ConstraintViolationException.class})
    public CommonResult<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return CommonResult.failed(ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResult<String> handlerCommerceException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        CommonResult<String> result = CommonResult.failed();
        result.setMessage(e.getMessage());
        log.error(e.getMessage());
        return result;
    }

}
