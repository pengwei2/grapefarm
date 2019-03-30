package com.pw.grapefarm.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response<T> {
    public static final int COMMON_SUCCESS_CODE = 1;
    public static final int COMMON_ERROR_CODE = 2;

    private Integer statusCode;

    private String statusDesc;

    private T data;

    public Response() {
    }

    public Response(Integer statusCode, String statusDesc, T data) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
        this.data = data;
    }

    public static Response<Map> cResponse(Integer statusCode, String statusDesc){
        return new Response<>(statusCode,statusDesc,new HashMap());
    }
}
