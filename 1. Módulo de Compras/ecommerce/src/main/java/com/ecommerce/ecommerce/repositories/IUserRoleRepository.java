package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("userRoleRepository")
public interface IUserRoleRepository extends JpaRepository<UserRole, Serializable>{
	public abstract UserRole findById(int idRole);
	
	@Query(nativeQuery=true,value="SELECT  * FROM user_role as ur WHERE ur.user_id = (:idUser)")
	public UserRole findByIdUser(int idUser);
}