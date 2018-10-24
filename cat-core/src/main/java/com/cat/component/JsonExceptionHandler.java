package com.cat.component;

import com.cat.utils.Result;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangxiaoqiang
 * @since 2018/10/23
 **/
@Slf4j
@ControllerAdvice
public class JsonExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<Object> defaultErrorHandler(HttpServletRequest request, Exception e) {
        if (e instanceof RuntimeException) {
            Result res = genErrorInfo("500", "0", e.getMessage());
                log.error(e.getMessage(), e);
            return res;
        }

        if (e instanceof IllegalArgumentException) {
            Result res = genErrorInfo("400", "ILLEGAL_ARGUMENT", e.getMessage());
            log.warn(e.getMessage(), e);
            return res;
        }

        String requestUrl = (request != null ? request.getRequestURI() : "requestUrl: ");
        log.error(requestUrl + ", errorMsg: " + e.getMessage(), e);
        return genErrorInfo("500", "INTERNAL_SERVER_ERROR", "系统异常");


    }
    private Result<Object> genErrorInfo(String code, String errCode, String message) {
        errCode = StringUtils.isEmpty(errCode) ? "SERVICE_EXCEPTION" : errCode;
        message = StringUtils.isEmpty(message) ? "系统异常" : message;

        Result res = Result.fail(code, message);
        res.setData(new ErrorInfo(errCode, message));

        return res;
    }

    @Data
    private static class ErrorInfo {
        private String errCode;
        private String message;

        private ErrorInfo(String errCode, String msg) {
            this.errCode = errCode;
            this.message = msg;
        }
    }

}
