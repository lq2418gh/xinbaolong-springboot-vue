package com.bit.sc.module.restTemplate.vo;

import com.bit.common.ResultCode;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author liuyancheng
 * @create 2018-11-29 9:35
 */
@Data
public class ResultDTO<T> {
    private int code;
    private String message;
    private T data;

    public ResultDTO(){

    }

    public ResultDTO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultDTO(T data) {
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
