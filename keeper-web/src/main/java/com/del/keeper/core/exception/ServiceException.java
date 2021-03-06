package com.del.keeper.core.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = -7599283850896719004L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
