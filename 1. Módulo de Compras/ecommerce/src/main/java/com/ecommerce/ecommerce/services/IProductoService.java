package com.ecommerce.ecommerce.services;

import java.util.List;

import com.ecommerce.ecommerce.entities.Producto;

public interface IProductoService {

    public List<Producto> getAll();
    
    public List<Producto> getAllProductosVisibles();
    
    public Producto getProductoById(long id);
    
}