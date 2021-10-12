package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable> {

	@Query("SELECT u FROM User u JOIN FETCH u.userRoles WHERE u.usuario = (:usuario)")
	public abstract User findByUsernameAndFetchUserRolesEagerly(@Param("usuario") String usuario);
	
	@Query("SELECT u FROM User u WHERE u.usuario = (:usuario)")	
	public abstract User findByUsuario(@Param("usuario") String usuario);
	
	@Query("SELECT usuario FROM User u WHERE u.usuario = (:usuario)")
	public abstract String userNameByEmailInUsername(@Param("usuario") String username);
	
}