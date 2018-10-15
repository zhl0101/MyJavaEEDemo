package com.zhl.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController1 {
	@RequestMapping("/info1")
	public String info(){
		return "HelloWorld";
	}

}
