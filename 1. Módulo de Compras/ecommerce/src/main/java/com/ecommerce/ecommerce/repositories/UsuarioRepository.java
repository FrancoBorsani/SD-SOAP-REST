package com.ecommerce.ecommerce.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entities.User;

@Repository
public interface UsuarioRepository extends CrudRepository<User, Long> {
	
	@Query(nativeQuery=true,value="select * from user where id=(:idUser)")
	public User findByIdUser(int idUser);
	
	@Query(nativeQuery=true,value="select * from user where username=(:username)")
	public User findByUsername(String username);
	
	
}
