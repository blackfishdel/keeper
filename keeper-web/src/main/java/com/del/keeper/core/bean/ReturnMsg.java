package com.del.keeper.core.bean;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * controller默认消息返回体
 *
 * @author xie
 */
public class ReturnMsg {

    private int code;
    private String message;

    public ReturnMsg() {
        super();
    }

    public ReturnMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 构建返回json数据
     *
     * @param httpStatus
     * @return
     */
    public static ResponseEntity<ReturnMsg> building(HttpStatus httpStatus) {
        return new ResponseEntity<>(new ReturnMsg(httpStatus.value(), httpStatus.getReasonPhrase()), httpStatus);
    }

    /**
     * 构建返回json数据
     *
     * @param httpStatus
     * @param message
     * @return
     */
    public static ResponseEntity<ReturnMsg> building(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new ReturnMsg(httpStatus.value(), message), httpStatus);
    }
}
