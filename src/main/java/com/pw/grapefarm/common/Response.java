package com.pw.grapefarm.common;

import lombok.Data;

@Data
public class Response<T> {
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
}
