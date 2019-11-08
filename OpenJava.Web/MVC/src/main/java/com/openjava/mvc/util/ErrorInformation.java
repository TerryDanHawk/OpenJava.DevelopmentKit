package com.openjava.mvc.util;

public class ErrorInformation {

    private String errorCode;
    private String errorMessage;

    public ErrorInformation(String errorcode,String errormessage)
    {
        errorCode=errorcode;
        errorMessage=errormessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorInformation(Exception ex)
    {
        errorCode="1021";
        errorMessage=ex.getMessage();
    }
}
