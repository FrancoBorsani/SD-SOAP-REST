package com.ecommerce.ecommerce.services;

import java.util.List;

import com.ecommerce.ecommerce.entities.Producto;

public interface IProductoService {

    public List<Producto> getAll();
    
    public List<Producto> getAllProductosVisibles();
    
    public Producto getProductoById(long id);
    
    public List<Producto> searchProduct(String keyword);
    
    public List<Producto> findByCategoria(String idCategoria);
    
    public List<Producto> findByVendedor(String idVendedor);
    
    public List<Producto> findByRangeOfPrice(double min, double max);

    void deleteProducto(int idProducto);
}