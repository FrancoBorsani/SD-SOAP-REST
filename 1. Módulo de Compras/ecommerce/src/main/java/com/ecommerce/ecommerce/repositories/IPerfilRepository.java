package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("perfilRepository")
public interface IPerfilRepository extends JpaRepository<Perfil, Serializable>{
	@Query(nativeQuery=true,value="select * from Perfil where id=(:idPerfil)")
	public abstract Perfil  findById(int idPerfil);
	
	@Query(nativeQuery=true,value="select * from Perfil where username=(:username)")
	public abstract Perfil findByUsername(String username);
	
}