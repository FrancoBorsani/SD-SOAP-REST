package com.ecommerce.ecommerce.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {

    @Query("SELECT p from Producto p where p.idProducto = :idProducto")
    public abstract Producto findByIdProducto(@Param("idProducto") long idProducto);
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p where p.stock > 0")
    public List<Producto> getAllProductosVisibles();
    
    @Query("SELECT p FROM Producto p WHERE CONCAT(p.nombre, ' ', p.descripcion) LIKE %?1% and p.stock > 0")
    public abstract List<Producto> searchProduct(String keyword);
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p WHERE p.categoria_id_categoria = :categoria and p.stock > 0")
    public abstract List<Producto> findByCategoria(@Param("categoria") String categoria);
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p WHERE p.vendedor_id = (:idVendedor) and p.stock > 0")
    public abstract List<Producto> findByVendedor(String idVendedor);

}