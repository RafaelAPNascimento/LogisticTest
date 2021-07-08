package br.com.lolfood.exception;

public class BusinessException extends RuntimeException {

    private String message;
    private int code;

    public BusinessException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
