package com.ecommerce.ecommerce.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.ecommerce.banca.BancaSoapClient;
import com.ecommerce.ecommerce.entities.Tarjeta;
import com.ecommerce.ecommerce.services.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	TarjetaService tarjetaService;

	public double calcularTotalGastado(int idTarjetaUsada, LocalDateTime fecha){
		double totalGastado = 0;
		List<Pedido> pedidosDelMes;
		try {
			pedidosDelMes = pedidoService.findByMonth(fecha.getMonthValue());
		}
		catch(Exception e){
			return totalGastado;
		}
		if (pedidosDelMes.size()==0){
			return totalGastado;
		}

		for(Pedido p : pedidosDelMes){
			if(p.getIdTarjetaUsada()==idTarjetaUsada){
				totalGastado+=p.getTotal();
			}
		}
		return totalGastado;
	}

	@PostMapping("/agregar")
	public Pedido agregarPedido(@RequestBody Pedido newPedido) {
		
		String username = "";
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		User u = usuarioService.traerUser(username);
		newPedido.setComprador(u);

		Tarjeta tarjetaUsada = tarjetaService.findById(newPedido.getIdTarjetaUsada());
		double totalGastado = calcularTotalGastado(newPedido.getIdTarjetaUsada(), newPedido.getCreatedAt());
		String validacion = banca.validar_limite_mensual(tarjetaUsada.getNumero(), tarjetaUsada.getTipo(),
				newPedido.getTotal(), totalGastado);

		System.out.println(newPedido.getVendedor());

		if (validacion.equals("1")){
			return this.pedidoService.guardarPedido(newPedido);
		}
		else{
			if (tarjetaUsada.getTipo().equals("debito")){
				throw new RuntimeException("Error: Saldo insuficiente.");
			}
			else{
				throw new RuntimeException("Error: Esta tarjeta de credito ya supero el limite mensual.");
			}
		}
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
