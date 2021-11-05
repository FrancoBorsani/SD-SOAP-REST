package com.ecommerce.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.Tarjeta;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.entities.UserRole;
import com.ecommerce.ecommerce.services.TarjetaService;
import com.ecommerce.ecommerce.services.UsuarioService;


@RestController
@RequestMapping("tarjeta")
public class TarjetaRestController {
	
	
	@Autowired
    UsuarioService usuarioService;
	
	@Autowired
    TarjetaService tarjetaService;

	@PostMapping("/create")
	public Tarjeta createTarjeta(@RequestBody Tarjeta tarjeta) {	
		String username = "";
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if( principal instanceof UserDetails) {
	    	username = ((UserDetails)principal).getUsername();
	    }
	    
	    tarjeta.setUser(usuarioService.traerUser(username));
		
	    return tarjetaService.guardarTarjeta(tarjeta);
	}
}
