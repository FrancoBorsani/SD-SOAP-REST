package com.ecommerce.ecommerce.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCompra;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comprador_id", nullable=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User comprador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vendedor_id", nullable=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User vendedor;

	@Column(name = "total")
	private double total;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="pedido")
	private Set<Item> listaItems;
	
	@Column(name = "createdat")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updatedat")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@Column(name = "idTarjetaUsada")
	private int idTarjetaUsada;
	
	@Column(name = "estadoDeEnvio")
	private String estadoDeEnvio;
	
	@Column(name = "codigoDeSeguimiento")
	private String codigoDeSeguimiento;
	
	@Column(name = "estadoDeCompra", nullable=true)
	private String estadoDeCompra;
	
	@Column(name = "direccionDeEntrega")
	private String direccionDeEntrega;

	public Pedido() {}

	public Pedido(long idCompra, User comprador, User vendedor, double total, Set<Item> listaItems, int idTarjetaUsada, String direccionDeEntrega) {
		super();
		this.idCompra = idCompra;
		this.comprador = comprador;
		this.vendedor = vendedor;
		this.total = total;
		this.listaItems = listaItems;
		this.idTarjetaUsada = idTarjetaUsada;
		this.direccionDeEntrega = direccionDeEntrega;
	}

	public Pedido(double total, Set<Item> listaItems) {
		super();
		this.total = total;
		this.listaItems = listaItems;
	}

	public Pedido(double total, Set<Item> listaItems, int idTarjetaUsada) {
		super();
		this.total = total;
		this.listaItems = listaItems;
		this.idTarjetaUsada = idTarjetaUsada;
	}
	
	public long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(long idCompra) {
		this.idCompra = idCompra;
	}

	public User getComprador() {
		return comprador;
	}

	public void setComprador(User comprador) {
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public int getIdTarjetaUsada() {
		return idTarjetaUsada;
	}

	public void setIdTarjetaUsada(int idTarjetaUsada) {
		this.idTarjetaUsada = idTarjetaUsada;
	}

	public String getEstadoDeEnvio() {
		return estadoDeEnvio;
	}

	public void setEstadoDeEnvio(String estadoDeEnvio) {
		this.estadoDeEnvio = estadoDeEnvio;
	}

	public String getCodigoDeSeguimiento() {
		return codigoDeSeguimiento;
	}

	public void setCodigoDeSeguimiento(String codigoDeSeguimiento) {
		this.codigoDeSeguimiento = codigoDeSeguimiento;
	}

	public String getEstadoDeCompra() {
		return estadoDeCompra;
	}

	public void setEstadoDeCompra(String estadoDeCompra) {
		this.estadoDeCompra = estadoDeCompra;
	}

	public String getDireccionDeEntrega() {
		return direccionDeEntrega;
	}

	public void setDireccionDeEntrega(String direccionDeEntrega) {
		this.direccionDeEntrega = direccionDeEntrega;
	}

	@Override
	public String toString() {
		return "Pedido [idCompra=" + idCompra + ", comprador=" + comprador + ", vendedor=" + vendedor + ", total="
				+ total + ", listaItems=" + listaItems + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", idTarjetaUsada=" + idTarjetaUsada + "]";
	}
	
}