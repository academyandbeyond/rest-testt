package com.resttest.framework.exceptions;

/**
 * Created by Sumana on 7/15/2016.
 */
public enum Errors {

    ERROR_TC("Test Case Name is either null or not defined.");

    private final String errorcode;

    private  Errors (String errorcode) {
        this.errorcode = errorcode;
    }

    public String getErrorMessage(){
        return errorcode;
    }
}
