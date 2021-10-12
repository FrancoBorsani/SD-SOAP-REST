package com.ecommerce.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.entities.UserRole;
import com.ecommerce.ecommerce.helpers.ViewRouteHelpers;
import com.ecommerce.ecommerce.implementation.PerfilService;
import com.ecommerce.ecommerce.repositories.IUserRepository;
import com.ecommerce.ecommerce.repositories.IUserRoleRepository;


@Controller
public class RegistroController {
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	
	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;
	
	
	@Autowired
	@Qualifier("perfilService")
	private PerfilService perfilService;
	
	@GetMapping("/registro")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.REGISTRO);
		
		return mAV;
	}
	
	
	@PostMapping("/registrarse")
	public RedirectView registrarse( @ModelAttribute("user") User newUSer, RedirectAttributes redirectAttrs) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		newUSer.setPassword(bCryptPasswordEncoder.encode(newUSer.getPassword())); 
				
		if(userRepository.findByUsuario(newUSer.getUsuario()) != null) {
			System.out.println("La cuenta ya existe");
			redirectAttrs.addFlashAttribute("mensaje","Ya existe un usuario con ese nombre de usuario");
			redirectAttrs.addFlashAttribute("clase", "danger");
			return new RedirectView("/registro");
		} else {
			System.out.println("REGISTRADO");
			userRepository.save(newUSer);
			userRoleRepository.save(new UserRole(userRepository.findByUsuario(newUSer.getUsuario()),"ROLE_USER"));			
			
			perfilService.addNewProfile(newUSer);
		}
		
		
		redirectAttrs.addFlashAttribute("mensaje","Usuario registrado con éxito");

		return new RedirectView("/");
	}
}