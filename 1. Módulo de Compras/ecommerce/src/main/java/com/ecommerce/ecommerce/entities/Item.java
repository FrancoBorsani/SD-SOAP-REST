package com.ecommerce.ecommerce.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="item")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idItem;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Producto producto;
	
	@Column(name = "cantidad")
	private int cantidad;

	@ManyToOne
	@JoinColumn(name = "fk_pedido", nullable = false, updatable = false)
	private Pedido pedido;

	public Item() { }

	public Item(long idItem, Producto producto, int cantidad, Pedido pedido) {
		super();
		this.idItem = idItem;
		this.producto = producto;
		this.cantidad = cantidad;
		this.pedido = pedido;
	}

	public long getIdItem() {
		return idItem;
	}

	public void setIdItem(long idItem) {
		this.idItem = idItem;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}