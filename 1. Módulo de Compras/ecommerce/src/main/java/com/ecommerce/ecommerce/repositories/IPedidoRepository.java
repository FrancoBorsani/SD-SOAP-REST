package com.ecommerce.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entities.Pedido;

@Repository("pedidoRepository")
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
	
    public abstract Pedido findByIdCompra(long idCompra);
	
    @Query(nativeQuery=true,value="SELECT * FROM Pedido as p WHERE p.vendedor_id = (:idVendedor)")
    public abstract List<Pedido> findByVendedor(long idVendedor);
    
    @Query(nativeQuery=true,value="SELECT * FROM Pedido as p WHERE p.comprador_id = (:idComprador)")
    public abstract List<Pedido> findByComprador(long idComprador);

    @Query(nativeQuery=true,value="SELECT * FROM Pedido as p WHERE MONTH(p.createdat) = (:month)")
    public abstract List<Pedido> findByMonth(int month);
	
}