package com.ecommerce.ecommerce.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommerce.ecommerce.entities.Domicilio;
import com.ecommerce.ecommerce.entities.Perfil;
import com.ecommerce.ecommerce.entities.Tarjeta;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.helpers.ViewRouteHelpers;
import com.ecommerce.ecommerce.repositories.IDomicilioRepository;
import com.ecommerce.ecommerce.repositories.ITarjetaRepository;
import com.ecommerce.ecommerce.repositories.IUserRepository;

@Controller
public class RegistroController {
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Autowired
	@Qualifier("domicilioRepository")
	private IDomicilioRepository domicilioRepository;
	
	@Autowired
	@Qualifier("tarjetaRepository")
	private ITarjetaRepository tarjetaRepository;
	
	
	@GetMapping("/registro")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.REGISTRO);
		
		return mAV;
	}
	
	@GetMapping("/iniciar")
	public ModelAndView iniciar() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.INICIAR);
		
		return mAV;
	}
	
}