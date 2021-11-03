package com.ecommerce.ecommerce.services;

import java.util.List;

import com.ecommerce.ecommerce.entities.Categoria;


public interface ICategoriaService {
	
    public List<Categoria> getAll();
        
    public Categoria getCategoriaById(long id);
    
}
