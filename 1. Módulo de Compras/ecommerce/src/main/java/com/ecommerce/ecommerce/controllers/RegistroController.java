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
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.entities.UserRole;
import com.ecommerce.ecommerce.helpers.ViewRouteHelpers;
import com.ecommerce.ecommerce.implementation.PerfilService;
import com.ecommerce.ecommerce.repositories.IDomicilioRepository;
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
	@Qualifier("domicilioRepository")
	private IDomicilioRepository domicilioRepository;
	
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
				
		if(userRepository.findByUsername(newUSer.getUsername()) != null) {
			System.out.println("La cuenta ya existe");
			redirectAttrs.addFlashAttribute("mensaje","Ya existe un usuario con ese nombre de usuario");
			redirectAttrs.addFlashAttribute("clase", "danger");
			return new RedirectView("/registro");
		} else {
			System.out.println("REGISTRADO");
			newUSer.setEnabled(true);
			userRepository.save(newUSer);
			userRoleRepository.save(new UserRole(userRepository.findByUsername(newUSer.getUsername()),"ROLE_USER"));			
			
			perfilService.addNewProfile(newUSer);
		}
		
		
		redirectAttrs.addFlashAttribute("mensaje","Usuario registrado con éxito");

		return new RedirectView("/");
	}
	
	@GetMapping("/iniciar")
	public ModelAndView iniciar() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.INICIAR);
		
		return mAV;
	}
	
	@GetMapping("/login")	
	public String login(Model model,	
						@RequestParam(name="error",required=false) String error,	
						@RequestParam(name="logout", required=false) String logout,
						RedirectAttributes redirectAttrs) {	

		if(error!=null){
			model.addAttribute("error", error);
			model.addAttribute("mensaje","Error: No se ha podido autenticar el usuario correctamente. Verifique las credenciales.");
		}
			
		model.addAttribute("logout", logout);
		
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		}
		System.out.println(username);
		if(!username.isEmpty()) {
			return "redirect:/";
		}
		else {
			return "login/index";
		}
	}	

	@GetMapping("/loginsuccess")	
	public String loginCheck() {	
		return "redirect:/";	
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    return "redirect:/login?logout"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public ModelAndView viewProfile() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PROFILE_INDEX);
	    	
		String username = "";
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if( principal instanceof UserDetails) {
	    	username = ((UserDetails)principal).getUsername();
	    }
	    
	    User usuarioBuscado = userRepository.findByUsername(((UserDetails)principal).getUsername());
		if (usuarioBuscado != null) {
			List<Domicilio> domicilios = domicilioRepository.findByIdUser(usuarioBuscado.getId());

			mAV.addObject("domicilios", domicilios);
		}
	    
	    
	    	
	    User currentUser = userRepository.findByUsername(username);
	    mAV.addObject("perfil", perfilService.findById(currentUser.getId()));
	    return mAV;
	}
	
	@PostMapping("/updateProfile")
	public RedirectView updateProfile(@ModelAttribute("perfil") Perfil editPerfil, RedirectAttributes redirectAttrs) {
    	try {
			perfilService.updateProfile(editPerfil);
			User perfilUser = userRepository.findByUsername(editPerfil.getUsername());
			perfilUser.setNombre(editPerfil.getNombre());
			perfilUser.setApellido(editPerfil.getApellido());
			perfilUser.setDni(editPerfil.getDni());
			userRepository.save(perfilUser);
			redirectAttrs.addFlashAttribute("mensaje", "Perfil actualizado correctamente");
		}catch (Exception e) {
			redirectAttrs.addFlashAttribute("mensaje", "Problemas al actualizar el perfil");
		}
		
		return new RedirectView("/profile");
	}
	
}