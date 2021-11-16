package com.ecommerce.ecommerce.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entities.Cuentas;

@Repository
public interface CuentasRepository extends CrudRepository<Cuentas, Long> {
	
	@Query(nativeQuery=true,value="select * from cuentas where user_id=(:idUser)")
	public Cuentas findByUser(int idUser);
	
}