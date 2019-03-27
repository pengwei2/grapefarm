package com.pw.grapefarm.commons;

import lombok.Data;

@Data
public class Response {
    private Integer statusCode;

    private String statusDesc;

    private String data;

    public Response() {
    }

    public Response(Integer statusCode, String statusDesc) {
        this(statusCode,statusDesc,"");
    }

    public Response(Integer statusCode, String statusDesc, String data) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
        this.data = data;
    }
}
