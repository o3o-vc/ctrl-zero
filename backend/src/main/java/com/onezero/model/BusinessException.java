package com.onezero.model;

public class BusinessException extends RuntimeException {
    private String type;
    public BusinessException(String type, String message) {
        super(message);
    }
    public String getType() {
        return this.type;
    }
}
