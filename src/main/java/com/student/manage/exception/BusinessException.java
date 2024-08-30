package com.student.manage.exception;

/**
 * @author zqj
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Throwable e) {
        super(e);
    }
}
