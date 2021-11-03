package com.ecommerce.ecommerce.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entities.Categoria;

@Repository("categoriaRepository")
public interface ICategoriaRepository extends JpaRepository<Categoria, Serializable> {
	
    public abstract Categoria findByIdCategoria(long idCategoria);

}
