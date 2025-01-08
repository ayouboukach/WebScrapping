package com.mahakim.app.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahakim.app.request.model.RespenseRecevied;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReadDataFromJsonFile {

	private final ObjectMapper mapper = new ObjectMapper();

	public RespenseRecevied getDataFromJsonFile(String path)
			throws StreamReadException, DatabindException, IOException {
		RespenseRecevied respenseRecevied = mapper.readValue(
				new File(path + ".json"), RespenseRecevied.class);
		return respenseRecevied;
	}

}
