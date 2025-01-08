package com.mahakim.app.request.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespenseRecevied {

	@JsonProperty("code")
	private int code; // 200, 201, 400, 500

	@JsonProperty("status")
	private boolean status;

	@JsonProperty("message")
	private String message;

	@JsonProperty("data")
	private Object data;

	public RespenseRecevied(int httpStatusCode, boolean httpStatus, String message) {
		super();
		this.code = httpStatusCode;
		this.status = httpStatus;
		this.message = message;
	}

	public RespenseRecevied(Object objectType) {
		this.data = objectType;
	}
}
