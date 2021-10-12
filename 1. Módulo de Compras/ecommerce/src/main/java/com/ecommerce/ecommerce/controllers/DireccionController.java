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

import com.ecommerce.ecommerce.entities.Domicilio;
import com.ecommerce.ecommerce.implementation.PerfilService;
import com.ecommerce.ecommerce.repositories.IDomicilioRepository;
import com.ecommerce.ecommerce.repositories.IUserRepository;
import com.ecommerce.ecommerce.repositories.IUserRoleRepository;

@Controller
public class DireccionController {
	
	@Autowired
	@Qualifier("domicilioRepository")
	private IDomicilioRepository domicilioRepository;
	
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	
	@PostMapping("/altaDireccion")
	public RedirectView altaDireccion( @ModelAttribute("domicilio") Domicilio newDomicilio, RedirectAttributes redirectAttrs) {
		
		String username = "";
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if( principal instanceof UserDetails) {
	    	username = ((UserDetails)principal).getUsername();
	    }
	    
	    newDomicilio.setUser(userRepository.findByUsername(username));
		
		domicilioRepository.save(newDomicilio);		

		
		redirectAttrs.addFlashAttribute("mensaje","Domicilio registrado");

		return new RedirectView("/profile");
	}
	
	
	
}