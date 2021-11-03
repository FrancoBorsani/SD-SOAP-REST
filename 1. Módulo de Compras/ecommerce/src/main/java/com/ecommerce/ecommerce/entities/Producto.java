package com.ecommerce.ecommerce.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProducto;

    @Column(name = "precio")
    private double precio;
    
    @Column(name = "descripcioncorta")
    private String descripcionCorta;

    @Column(name = "descipcionlarga")
    private String descripcionLarga;

    @Column(name = "visible")
    private boolean visible;
    
    @Column(name = "imagen")
    private String imagen;
    
    @Column(name = "stock")
    private int stock;
    
    @OneToOne(cascade = CascadeType.MERGE)
	private Categoria categoria;
	
    public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Producto() { }

	public Producto(long idProducto, double precio, String descripcionCorta, String descripcionLarga, boolean visible,
			String imagen, int stock, Categoria categoria) {
		super();
		this.idProducto = idProducto;
		this.precio = precio;
		this.descripcionCorta = descripcionCorta;
		this.descripcionLarga = descripcionLarga;
		this.visible = visible;
		this.imagen = imagen;
		this.stock = stock;
		this.categoria = categoria;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", precio=" + precio + ", descripcionCorta=" + descripcionCorta
				+ ", descripcionLarga=" + descripcionLarga + ", visible=" + visible + ", imagen=" + imagen + ", stock="
				+ stock + ", categoria=" + categoria + "]";
	}
	
}