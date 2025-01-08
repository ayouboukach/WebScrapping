package com.mahakim.app.response.model;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class HttpResponse {
	
    private int code; // 200, 201, 400, 500
    private HttpStatus status;
    private String message;
    private Map<?, ?> data;
	
    public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String message) {
		super();
		this.code = httpStatusCode;
		this.status = httpStatus;
		this.message = message;
	}
}
