package com.ecommerce.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.Pedido;
import com.ecommerce.ecommerce.services.PedidoService;
import com.ecommerce.ecommerce.services.UsuarioService;

@RestController
@RequestMapping("pedido")
@CrossOrigin("*")
public class PedidoRestController {
	@Autowired
	UsuarioService usuarioService;

	@Autowired
	PedidoService pedidoService;

	@PostMapping("/agregar")
	public Pedido agregarPedido(@RequestBody Pedido newPedido) {
		String username = "";
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}
		
		newPedido.setComprador(usuarioService.traerUser(username).getId());

		return this.pedidoService.guardarPedido(newPedido);
	}
}
