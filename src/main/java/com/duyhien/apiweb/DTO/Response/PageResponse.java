package com.duyhien.apiweb.DTO.Response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PageResponse<T> implements Serializable {
    private int code;

    private String message;

    private T result;
}
