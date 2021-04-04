package com.example.spring.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFrom<T> {
    private int code;
    private String message;
    private T data;
    public static <T> ResponseFrom<T> buildResponse(T data){
        ResponseFrom<T> response = new ResponseFrom<>();
        response.data = data;
        response.code = 0;
        response.message = "okokokoko";
        return response;
    }
    public static <T> ResponseFrom<T> buildCustomResponse(T data,int code,String message){
        ResponseFrom<T> response = new ResponseFrom<T>();
        response.data = data;
        response.code = code;
        response.message = message;
        return response;
    }
}
