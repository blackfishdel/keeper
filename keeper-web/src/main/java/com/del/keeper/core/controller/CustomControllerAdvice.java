package com.del.keeper.core.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.del.keeper.core.bean.ReturnMsg;
import com.del.keeper.core.exception.ControllerException;
import com.del.keeper.core.exception.ServiceException;

/**
 * RestController捕获异常统一处理
 *
 * @author xie
 */
@ControllerAdvice
public class CustomControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(CustomControllerAdvice.class);

    /**
     * 统一异常返回
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> processConrollerException(Exception ex) {
        if (ex instanceof ControllerException) {
            ControllerException ce = (ControllerException) ex;
            Throwable throwable = ex.getCause();
            if (throwable != null && throwable instanceof ServiceException) {
                Throwable throwable2 = throwable.getCause();
                if (throwable2 != null) {
                    log.warn(throwable2.getMessage(), throwable2);
                }
            } else if (throwable != null) {
                log.warn(throwable.getMessage(), throwable);
            }

            if (ce.getHttpStatus() != null && ce.getHttpStatus() == HttpStatus.PARTIAL_CONTENT
                    && ce.getAccount() != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", HttpStatus.PARTIAL_CONTENT.value());
                map.put("message", ex.getMessage());
                return new ResponseEntity<>(map, ce.getHttpStatus());
            }

            if (ce.getHttpStatus() != null) {
                return ReturnMsg.building(ce.getHttpStatus(), ex.getMessage());
            }
            return ReturnMsg.building(HttpStatus.PRECONDITION_FAILED, ex.getMessage());
        }
        log.warn(ex.getMessage(), ex);
        return ReturnMsg.building(HttpStatus.PRECONDITION_FAILED, ex.getMessage());
    }
}
