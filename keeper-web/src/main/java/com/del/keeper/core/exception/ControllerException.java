package com.del.keeper.core.exception;

import org.springframework.http.HttpStatus;

import com.del.keeper.commons.entity.Account;

public class ControllerException extends RuntimeException {
    private static final long serialVersionUID = 6057967972174862513L;

    private HttpStatus httpStatus;
    private Account account;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ControllerException(HttpStatus httpStatus, Account account, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.account = account;
    }
}
