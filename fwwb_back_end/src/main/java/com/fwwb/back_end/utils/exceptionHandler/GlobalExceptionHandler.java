package com.fwwb.back_end.utils.exceptionHandler;

import com.alibaba.fastjson.JSON;
import com.fwwb.back_end.utils.exceptionHandler.exception.DefinitionException;
import com.fwwb.back_end.utils.exceptionHandler.exception.ErrorEnum;
import com.fwwb.back_end.utils.resultUtils.ResponseResultBody;
import com.fwwb.back_end.utils.resultUtils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: back_end
 * @description: Global Exception Handler
 * @author: CodingLiOOT
 * @create: 2021-02-02 12:28
 * @version: 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity IllegalArgumentExceptionHandler(IllegalArgumentException e){
        return DefinitionExceptionHandler(new DefinitionException(ErrorEnum.BODY_NOT_MATCH));
    }

    @ExceptionHandler(value = DefinitionException.class)
    @ResponseBody
    public ResponseEntity DefinitionExceptionHandler(DefinitionException de){
        return ResponseEntity
                .status(de.getResultCode())
                .body(Result.fail(de));
    }



//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ResponseEntity<Result> OtherExceptionHandler(Exception e){
//        return ResponseEntity
//                .status(ErrorEnum.INTERNAL_SERVER_ERROR.getResultCode())
//                .body(Result.fail(ErrorEnum.INTERNAL_SERVER_ERROR));
//    }
}
