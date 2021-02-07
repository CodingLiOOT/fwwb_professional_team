package com.fwwb.back_end.utils.exceptionHandler.exception;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: back_end
 * @description: 自定义异常类
 * @author: CodingLiOOT
 * @create: 2021-02-02 12:46
 * @version: 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Accessors(chain = true)
public class DefinitionException extends RuntimeException implements BaseErrorInfoInterface, Serializable {
    @NonNull
    protected Integer errorCode;
    @NonNull
    protected String errorMsg;
    protected Object errorData;

    public DefinitionException(ErrorEnum item){
        super();
        this.errorCode=item.getResultCode();
        this.errorMsg=item.getResultMsg();
    }
    public DefinitionException(ErrorEnum item,Object data){
        this(item);
        this.errorData=data;
    }
    @Override
    public Integer getResultCode() {
        return errorCode;
    }

    @Override
    public String getResultMsg() {
        return errorMsg;
    }

    @Override
    public Object getResultData() {
        return errorData;
    }
}
