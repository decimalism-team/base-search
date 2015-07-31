package com.qeeka.operate;

/**
 * Created by neal.xu on 7/29 0029.
 */
public enum QuerySpecialOperate {
    EQUALS("="), NO_EQUALS("<>"), LIKE("like"), NOT_LIKE("no like");

    private String value;

    QuerySpecialOperate(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
