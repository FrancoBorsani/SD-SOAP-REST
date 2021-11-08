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
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.Categoria;
import com.ecommerce.ecommerce.services.ICategoriaService;

@RestController
@RequestMapping("/api/v1/categorias")
@CrossOrigin("*")
public class CategoriaRestController {
	
	@Autowired
	@Qualifier("categoriaService")
    private ICategoriaService categoriaService;
	
    @GetMapping("")
    public List<Categoria> getAllCategorias() {
    	return categoriaService.getAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable("id") int idCategoria) {
    	
    	Categoria categoria = categoriaService.getCategoriaById(idCategoria);
    	
		if (categoria == null) {
			return new ResponseEntity<Categoria>(categoria, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
		}
   
    }
    
}