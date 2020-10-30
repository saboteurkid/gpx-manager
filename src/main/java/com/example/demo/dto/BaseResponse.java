package com.example.demo.dto;


import java.util.List;

public class BaseResponse<D, E extends ErrorInfo> {
    private List<E> errors;
    private D data;
}
