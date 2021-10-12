package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable> {

	@Query("SELECT u FROM User u JOIN FETCH u.userRoles WHERE u.username = (:username)")
	public abstract User findByUsernameAndFetchUserRolesEagerly(@Param("username") String username);
	
	public User findByUsername(String username);
	
	@Query("SELECT username FROM User u WHERE u.username = (:username)")
	public abstract String userNameByEmailInUsername(@Param("username") String username);

	@Query(nativeQuery=true,value="select * from User where id=(:idUser)")
	public User findByIdUser(int idUser);
	
	
}