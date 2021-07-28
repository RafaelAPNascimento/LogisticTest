package br.com.lolfood.exception;

import java.util.Objects;

public class BusinessException extends RuntimeException {

    private String message;
    private int code;

    public BusinessException(String message, int code) {
        super(message);
        Objects.requireNonNull(code, "Code can not be null");
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
