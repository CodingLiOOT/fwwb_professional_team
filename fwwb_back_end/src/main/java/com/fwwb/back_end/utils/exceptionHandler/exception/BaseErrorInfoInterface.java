package com.fwwb.back_end.utils.exceptionHandler.exception;

public interface BaseErrorInfoInterface {

    Integer getResultCode();

    String getResultMsg();

    Object getResultData();
}
