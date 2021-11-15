package com.ecommerce.ecommerce.api;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.ecommerce.banca.BancaSoapClient;
import com.ecommerce.ecommerce.correo.CorreoRestClient;
import com.ecommerce.ecommerce.correo.EnvioResponse;
import com.ecommerce.ecommerce.entities.Tarjeta;
import com.ecommerce.ecommerce.services.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.Item;
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

	@PostMapping("/agregar")
	public ResponseEntity<Pedido> agregarPedido(@RequestBody Pedido newPedido) {

		String username = "";

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		User u = usuarioService.traerUser(username);
		newPedido.setComprador(u);

		double totalGastado = 0; // getTotalGastado();

		Tarjeta tarjetaUsada = tarjetaService.findById(newPedido.getIdTarjetaUsada());
		String validacion = banca.validar_limite_mensual(tarjetaUsada.getNumero(), tarjetaUsada.getTipo(),
				newPedido.getTotal(), totalGastado);

		if (validacion.equals("1")) {

			/****************************** CREAR ENVIO ASOCIADO AL PEDIDO **************************************/

			String descripcionPedido = "";

			for (Item item : newPedido.getListaItems()) {
				descripcionPedido += item.getProducto().getDescripcion() + " x " + item.getCantidad();
			}

			EnvioResponse envio = CorreoRestClient.callCreateEnvioAPI(descripcionPedido, newPedido.getComprador().getDni(), "58",
					newPedido.getComprador().getApellido() + " " + newPedido.getVendedor().getNombre());
			
			newPedido.setCodigoDeSeguimiento(envio.getCodigoDeSeguimiento());
			newPedido.setEstadoDeEnvio(envio.getEstado());

			/***************************************************************************************************/

			return new ResponseEntity<>(this.pedidoService.guardarPedido(newPedido), HttpStatus.OK);
		} else {
			if (tarjetaUsada.getTipo().equals("debito")) {
				throw new RuntimeException("Error: Saldo insuficiente.");
			} else {
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

		List<String> roles = user.getUserRoles().stream().map(item -> item.getRole()).collect(Collectors.toList());

		List<Pedido> pedidos = roles.get(0) != "ROLE_USER" ? pedidoService.findByComprador(user.getId())
				: pedidoService.findByVendedor(user.getId());

		return pedidos;

	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable("id") int idCompra) {
		
    	Pedido pedido = pedidoService.findByIdCompra(idCompra);
    	
    	if(pedido == null ) return new ResponseEntity<Pedido>(pedido, HttpStatus.NOT_FOUND);
    	
    	EnvioResponse envio = CorreoRestClient.callGetEnvioByCodigoAPI(pedido.getCodigoDeSeguimiento());
    	
    	System.out.println(envio);
    	
    	if(envio != null) {
    		pedido.setEstadoDeEnvio(envio.getEstado());
    		pedidoService.guardarPedido(pedido);
    	}

		return new ResponseEntity<Pedido>(pedido, pedido == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
		
	}

}
