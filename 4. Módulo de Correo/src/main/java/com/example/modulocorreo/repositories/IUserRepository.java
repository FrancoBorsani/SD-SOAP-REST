package com.example.modulocorreo.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.modulocorreo.entities.User;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable> {

	public abstract List<User> findAll();

	public abstract User findByUsername(String username);

	public abstract User findUserByUsername(String username);
	
}