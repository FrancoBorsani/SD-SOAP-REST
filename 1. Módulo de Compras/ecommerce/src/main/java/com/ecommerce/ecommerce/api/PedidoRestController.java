package com.ecommerce.ecommerce.api;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.ecommerce.banca.BancaSoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.Pedido;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.services.PedidoService;
import com.ecommerce.ecommerce.services.UsuarioService;

@RestController
@RequestMapping("api/v1/pedido")
@CrossOrigin("*")
public class PedidoRestController {
	
	@Autowired
	UsuarioService usuarioService;

	@Autowired
	PedidoService pedidoService;

	@Autowired
	BancaSoapClient banca;


	@PostMapping("/agregar")
	public Pedido agregarPedido(@RequestBody Pedido newPedido) {
		
		String username = "";
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		User u = usuarioService.traerUser(username);
		newPedido.setComprador(u);

		//String validacion = banca.validar_limite_mensual(Long.valueOf(), tipo_tarjeta, newPedido.getTotal(), total_gastado);
		
		return this.pedidoService.guardarPedido(newPedido);
	}
	
	@GetMapping("/getByVendedorOCliente")
	public List<Pedido> getPedidosByVendedorOCliente() {
		
		String username = "";
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}
		
		User user = usuarioService.traerUser(username);
				
        List<String> roles = user.getUserRoles().stream()
        		.map(item -> item.getRole())
        		.collect(Collectors.toList());
        
        
        List<Pedido> pedidos = roles.get(0) != "ROLE_USER" ? pedidoService.findByComprador(user.getId()) : pedidoService.findByVendedor(user.getId());
		                
        return pedidos;
        
	}
	
	
}
