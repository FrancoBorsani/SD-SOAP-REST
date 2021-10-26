package com.ecommerce.ecommerce.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {

    public abstract Producto findByIdProducto(long idProducto);
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p where p.visible = true")
    public List<Producto> getAllProductosVisibles();

}