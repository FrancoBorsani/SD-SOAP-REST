package com.ecommerce.ecommerce.api;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.banca.BancaSoapClient;
import com.ecommerce.ecommerce.correo.CorreoRestClient;
import com.ecommerce.ecommerce.correo.EnvioResponse;
import com.ecommerce.ecommerce.entities.Item;
import com.ecommerce.ecommerce.entities.Pedido;
import com.ecommerce.ecommerce.entities.Producto;
import com.ecommerce.ecommerce.entities.Tarjeta;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repositories.IProductoRepository;
import com.ecommerce.ecommerce.services.CuentasService;
import com.ecommerce.ecommerce.services.IProductoService;
import com.ecommerce.ecommerce.services.PedidoService;
import com.ecommerce.ecommerce.services.TarjetaService;
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
	
	@Autowired
	CuentasService cuentaService;
	
	@Autowired
	IProductoService productoService;

	@Autowired
	IProductoRepository productoRepository;

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
	public ResponseEntity<Pedido> agregarPedido(@RequestBody Pedido newPedido) {
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

		if (validacion.equals("1")) {

			/****************************** CREAR ENVIO ASOCIADO AL PEDIDO **************************************/

			String descripcionPedido = "";
			
			Producto producto = null;

			for (Item item : newPedido.getListaItems()) {
				descripcionPedido += item.getProducto().getDescripcion() + " x " + item.getCantidad();
				
				//ACTUALIZAR STOCK DE PRODUCTOS
				producto = this.productoRepository.traerProductoPorId(item.getProducto().getIdProducto());
				producto.setCantidadVendida(producto.getCantidadVendida() + item.getCantidad());

				producto.setStock(producto.getStock() - item.getCantidad());
				
				this.productoRepository.save(producto);
			}
			
			this.pedidoService.guardarPedido(newPedido);

			System.out.println("ID COMPRA:");
			
			System.out.println(newPedido.getIdCompra());
			
			EnvioResponse envio = CorreoRestClient.callCreateEnvioAPI(descripcionPedido, newPedido.getComprador().getDni(), newPedido.getIdCompra() + "" ,
					newPedido.getVendedor().getApellido() + " " + newPedido.getVendedor().getNombre());

			newPedido.setCodigoDeSeguimiento(envio.getCodigoDeSeguimiento());
			newPedido.setEstadoDeEnvio(envio.getEstado());

			/***************************************************************************************************/
			//Le descuento de la cuenta la plata
			banca.transferir_plata(tarjetaUsada.getNumero() , -newPedido.getTotal());

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
		
		for(Pedido p : pedidos) {
			p.setEstadoDeEnvio(CorreoRestClient.callGetEnvioByCodigoAPI(p.getCodigoDeSeguimiento()).getEstado());
			System.out.println(p.getEstadoDeEnvio());
		}

		return pedidos;

	}

	@GetMapping("/getPedidosReclamables")
	public List<Pedido> getPedidosReclamables() {
		List<Pedido> pedidos = getPedidosByVendedorOCliente();
		int days = 5;

		for(Pedido p : pedidos){
			//Si ya pasaron mas de N horas, no es un producto reclamable. Si no esta entregado, entonces tampoco lo mostramos.
			if(ChronoUnit.HOURS.between(p.getCreatedAt(), LocalDateTime.now())>(days*24) || p.getEstadoDeEnvio().equalsIgnoreCase("Entregado")){
				pedidos.remove(p);
			}
		}

		return pedidos;
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable("id") int idCompra) {
		
    	Pedido pedido = pedidoService.findByIdCompra(idCompra);
    	    	
    	if(pedido == null ) return new ResponseEntity<Pedido>(pedido, HttpStatus.NOT_FOUND);
    	    	
    	EnvioResponse envio = CorreoRestClient.callGetEnvioByCodigoAPI(pedido.getCodigoDeSeguimiento());
    	    	
    	if(envio != null) {
    		pedido.setEstadoDeEnvio(envio.getEstado());
    		pedidoService.guardarPedido(pedido);
    	} 

		return new ResponseEntity<Pedido>(pedido, pedido == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}

	@GetMapping("/reclamos/{id}")
    public ResponseEntity<Pedido> getPedidoByIdReclamos(@PathVariable("id") int idCompra) {

    	Pedido pedido = pedidoService.findByIdCompra(idCompra);

		return new ResponseEntity<Pedido>(pedido, pedido == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@PostMapping("/cancelar")
	public ResponseEntity<Pedido> cancelarPedido(@RequestParam(name="idCompra") int idCompra, @RequestParam(name="total") double total) {
    	Pedido pedido = pedidoService.findByIdCompra(idCompra);
		Tarjeta tarjetaUsada = tarjetaService.findById(pedido.getIdTarjetaUsada());

		String validacion = "";
		if (pedido.getEstadoDeCompra()!="Cancelado"){
			validacion = banca.transferir_plata(tarjetaUsada.getNumero(), pedido.getTotal());
		}
		else{
			throw new RuntimeException("El pedido ya fue cancelado.");
		}

		if (validacion.equals("1")) {

			/****************************** MODIFICAR ESTADO DEL ENVÍO A "CANCELADO" **************************************/
			
			pedido.setEstadoDeCompra("Cancelado");

			/***************************************************************************************************/

			return new ResponseEntity<>(this.pedidoService.actualizarPedido(pedido), HttpStatus.OK);
		} else {
		throw new RuntimeException("Error: Se ha producido un problema al intentar cancelar el pedido.");
		}

	}
	
	@PostMapping("/devolver")
	public ResponseEntity<Pedido> devolverPedido(@RequestParam(name="idCompra") int idCompra, @RequestParam(name="total") double total) {
    	
    	Pedido pedido = pedidoService.findByIdCompra(idCompra);
		Tarjeta tarjetaUsada = tarjetaService.findById(pedido.getIdTarjetaUsada());
		String validacion = "";

		if (pedido.getEstadoDeCompra() != "Reclamado") {
			validacion = banca.transferir_plata(tarjetaUsada.getNumero(), pedido.getTotal());
		} else{
			throw new RuntimeException("El dinero de este pedido ya fue devuelto.");
		}

		if (validacion.equals("1")) {

			/****************************** MODIFICAR ESTADO DEL ENVÍO A "RECLAMADO" **************************************/
			
			pedido.setEstadoDeCompra("Reclamado");

			/***************************************************************************************************/

			return new ResponseEntity<>(this.pedidoService.actualizarPedido(pedido), HttpStatus.OK);
		} else {
			throw new RuntimeException("Error: Se ha producido un problema al intentar devolver el dinero del pedido.");
		}
	}
}
