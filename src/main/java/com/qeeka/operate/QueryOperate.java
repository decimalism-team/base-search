package com.qeeka.operate;

/**
 * Created by neal.xu on 7/29 0029.
 */
public enum QueryOperate {
    EQUALS("="), NO_EQUALS("<>"), LIKE("like"), NOT_LIKE("no like");

    private String value;

    QueryOperate(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
