package com.pw.grapefarm.commons;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response {
    private Integer statusCode;

    private String statusDesc;

    private Map<String,Object> data;

    public Response() {
    }

    public Response(Integer statusCode, String statusDesc) {
        this(statusCode,statusDesc,new HashMap<>());
    }

    public Response(Integer statusCode, String statusDesc, Map<String, Object> data) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
        this.data = data;
    }
}
