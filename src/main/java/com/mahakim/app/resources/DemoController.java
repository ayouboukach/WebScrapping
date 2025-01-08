package com.mahakim.app.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/demo") // <like this
public class DemoController {

	@RequestMapping("/getData") // <-like this
	public String demo() {
		return "Hello SSL";
	}
}