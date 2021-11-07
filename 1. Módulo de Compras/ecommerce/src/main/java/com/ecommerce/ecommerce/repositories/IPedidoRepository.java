package com.ecommerce.ecommerce.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entities.Pedido;

@Repository("pedidoRepository")
public interface IPedidoRepository extends CrudRepository<Pedido, Long> {

	
}