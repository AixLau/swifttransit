package com.aix.swifttransit.common.mvc.advice;

import com.aix.swifttransit.common.core.util.CommonResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public CommonResult<String> handlerCommerceException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        CommonResult<String> result = CommonResult.failed();
        result.setMessage(e.getMessage());
        log.error(e.getMessage());
        return result;
    }

}
