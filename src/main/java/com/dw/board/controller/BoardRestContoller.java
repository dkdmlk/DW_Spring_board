package com.dw.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardRestContoller {
	@GetMapping("/")
	public String callHellowrold(){
		return "Hello World";
	}
}
