package com.ecommerce.ecommerce.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCompra;
	
	@Column(name = "idComprador")
	private int comprador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User vendedor;

	@Column(name = "total")
	private double total;

	@OneToMany(fetch=FetchType.LAZY,mappedBy="pedido")
	private Set<Item> listaItems;

	public Pedido() {}

	public Pedido(long idCompra, int comprador, User vendedor, double total) {
		super();
		this.idCompra = idCompra;
		this.comprador = comprador;
		this.vendedor = vendedor;
		this.total = total;
	}

	public long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(long idCompra) {
		this.idCompra = idCompra;
	}

	public int getComprador() {
		return comprador;
	}

	public void setComprador(int comprador) {
		this.comprador = comprador;
	}

	public User getVendedor() {
		return vendedor;
	}

	public void setVendedor(User vendedor) {
		this.vendedor = vendedor;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Set<Item> getListaItems() {
		return listaItems;
	}

	public void setListaItems(Set<Item> listaItems) {
		this.listaItems = listaItems;
	}

	@Override
	public String toString() {
		return "Pedido [idCompra=" + idCompra + ", total=" + total + "]";
	}


	
}