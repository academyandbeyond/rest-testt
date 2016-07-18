package com.resttest.framework.exceptions;

/**
 * Created by Sumana on 7/15/2016.
 */
public class RestAPIException extends Exception {
    private String message;

    public RestAPIException(){
        super();
    }

    public RestAPIException(String message){
        super(message);
        this.message=message;
    }

    public RestAPIException(Throwable cause) {
        super(cause);
    }
}
