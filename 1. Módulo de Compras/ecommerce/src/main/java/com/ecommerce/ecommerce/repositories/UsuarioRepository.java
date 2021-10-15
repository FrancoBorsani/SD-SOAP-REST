package com.ecommerce.ecommerce.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entities.User;

@Repository
public interface UsuarioRepository extends CrudRepository<User, Long> {

}
