package com.qeeka.operate;

/**
 * Created by Neal on 8/2 0002.
 */
public enum QueryResultType {
    UNIQUE("Unique"), LIST("List");

    private String value;

    QueryResultType(String value) {
        this.value = value;
    }
}
