package com.ecommerce.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping("/testeando")
	public String test() {
		
		return "ACCESO CONCEDIDO AL MÉTODO";
	}
}
