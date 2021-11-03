package com.ecommerce.ecommerce.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Categoria;
import com.ecommerce.ecommerce.repositories.ICategoriaRepository;
import com.ecommerce.ecommerce.services.ICategoriaService;


@Service("categoriaService")
public class CategoriaService implements ICategoriaService {
	
    @Autowired
    @Qualifier("categoriaRepository")
    private ICategoriaRepository categoriaRepository;

	@Override
	public List<Categoria> getAll() {
		// TODO Auto-generated method stub
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria getCategoriaById(long id) {
		// TODO Auto-generated method stub
		return categoriaRepository.findByIdCategoria(id);
	}

}
