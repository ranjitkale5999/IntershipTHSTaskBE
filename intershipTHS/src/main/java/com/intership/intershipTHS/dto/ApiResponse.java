package com.intership.intershipTHS.dto;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
}
