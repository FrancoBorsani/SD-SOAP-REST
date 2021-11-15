package com.ecommerce.ecommerce.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.Producto;
import com.ecommerce.ecommerce.services.IProductoService;

@RestController
@RequestMapping("/api/v1/productos")
@CrossOrigin("*")
public class ProductoRestController {
	
	@Autowired
	@Qualifier("productoService")
    private IProductoService productoService;
	
    @GetMapping("")
    public List<Producto> getAllProductos() {
    	    	    	
    	return productoService.getAllProductosVisibles();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable("id") int idProducto) {
    	
    	Producto producto = productoService.getProductoById(idProducto);
    	
		if (producto == null) {
			return new ResponseEntity<Producto>(producto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		}
   
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Producto>> searchProduct(@RequestParam(name="keyword") String keyword) {
    	
    	List<Producto> productos = productoService.searchProduct(keyword);
    	    	
    	return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
   
    }
    
    @GetMapping("/getByCategoria")
    public ResponseEntity<List<Producto>> getByCategory(@RequestParam(name="idCategoria") String categoria) {
    	
    	List<Producto> productos = productoService.findByCategoria(categoria);
    	    	
    	return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
   
    }
    
    @GetMapping("/getByVendedor")
    public ResponseEntity<List<Producto>> getByVendedor(@RequestParam(name="idVendedor") String idVendedor) {
    	
    	List<Producto> productos = productoService.findByVendedor(idVendedor);
    	    	
    	return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
   
    }

}
