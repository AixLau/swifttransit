package com.aix.swifttransit.common.mvc.advice;

import com.aix.swifttransit.common.core.util.CommonResult;
import com.aix.swifttransit.common.mvc.annotation.IgnoreResponseAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 实现统一响应
 */
@RestControllerAdvice(value = "com.aix.swifttransit")
public class CommonResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断当前处理的方法所在的类或方法上是否被 IgnoreResponseAdvice 注解标记。
     * 如果类上存在该注解，返回 false，表示不支持应用自定义的响应处理逻辑。
     * 如果类和方法上都没有 IgnoreResponseAdvice 注解，返回 true，表示支持应用自定义的响应处理逻辑。
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class) &&
                (returnType.getMethod() == null || !returnType.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        CommonResult<Object> result = CommonResult.ok();
        if (ObjectUtils.isEmpty(body)) {
            return response;
        } else {
            result.setData(body);
        }
        return result;
    }
}
