package com.ecommerce.ecommerce.services;

import java.util.List;

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
	
	public List<Pedido> getAllPedidos() {
		return pedidoRepository.findAll();
	}
	
	public List<Pedido> findByVendedor(long idVendedor) {
		return pedidoRepository.findByVendedor(idVendedor);			
	}
	
	public List<Pedido> findByComprador(long idComprador) {
		return pedidoRepository.findByComprador(idComprador);			
	}
	
    public Pedido findByIdCompra(long idCompra) {
    	return pedidoRepository.findByIdCompra(idCompra);
    }

	public List<Pedido> findByMonth(int month) { return pedidoRepository.findByMonth(month); }
}
