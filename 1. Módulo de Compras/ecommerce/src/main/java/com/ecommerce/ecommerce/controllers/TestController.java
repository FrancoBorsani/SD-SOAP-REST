package com.ecommerce.ecommerce.controllers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TestController {
	
	//MÉTODO PARA COMPROBAR (CON UN REQUEST) QUE LOS TOKENS ESTÁN ANDANDO
	
	@GetMapping("/testeando")
	public String test() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		    for(GrantedAuthority rol:((UserDetails)principal).getAuthorities()) {
		    	System.out.println("ROL");
		    	System.out.println(rol);
		    }
		}
		return "ACCESO CONCEDIDO AL MÉTODO";
	}
}
