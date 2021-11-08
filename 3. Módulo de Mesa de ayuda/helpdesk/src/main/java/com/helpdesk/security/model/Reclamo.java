package com.helpdesk.security.model;

import javax.persistence.*;

@Entity
@Table(name = "reclamo")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String estado;
    private String reclamo;
    private int idCompra;
    private String decision;

    public Reclamo() {
    }

    public Reclamo(Long id, String estado, String reclamo, int idCompra, String decision) {
        this.id = id;
        this.estado = estado;
        this.reclamo = reclamo;
        this.idCompra = idCompra;
        this.decision = decision;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getReclamo() {
        return reclamo;
    }

    public void setReclamo(String reclamo) {
        this.reclamo = reclamo;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}