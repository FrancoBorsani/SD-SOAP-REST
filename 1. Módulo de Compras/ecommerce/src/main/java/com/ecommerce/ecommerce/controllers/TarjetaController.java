package com.ecommerce.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommerce.ecommerce.entities.Tarjeta;
import com.ecommerce.ecommerce.repositories.ITarjetaRepository;
import com.ecommerce.ecommerce.repositories.IUserRepository;

@Controller
public class TarjetaController {
	
	@Autowired
	@Qualifier("tarjetaRepository")
	private ITarjetaRepository tarjetaRepository;
	
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	/* REEMPLAZADO POR EL MÉTODO QUE SE ENCUENTRA EN EL PACKAGE API -> TarjetaRestController
	@PostMapping("/altaTarjeta")
	public RedirectView altaTarjeta( @ModelAttribute("tarjeta") Tarjeta newTarjeta, RedirectAttributes redirectAttrs) {
		
		String username = "";
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if( principal instanceof UserDetails) {
	    	username = ((UserDetails)principal).getUsername();
	    }
	    
	    newTarjeta.setUser(userRepository.findByUsername(username));
		
		tarjetaRepository.save(newTarjeta);		

		redirectAttrs.addFlashAttribute("mensaje","Tarjeta registrada");

		return new RedirectView("/profile");
	}*/
	
	
	
}