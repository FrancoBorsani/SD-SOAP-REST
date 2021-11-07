package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Pedido;
import com.ecommerce.ecommerce.repositories.IPedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	IPedidoRepository pedidoRepository;
	
	public Pedido guardarPedido(Pedido pedido) {
		return pedidoRepository.save(pedido);			
	}

}
