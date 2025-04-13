package com.coderhouse.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "metodosDePagos")
public class MetodoDePago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String nombre;
	
	@Column(nullable = false)
	private float costoAgrgado;
	
	@OneToMany(mappedBy = "metodoDePago")
	private List<Factura> facturas;

	public MetodoDePago() {
		super();
	}

	public MetodoDePago(String nombre, float costoAgrgado) {
		super();
		this.nombre = nombre;
		this.costoAgrgado = costoAgrgado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getCostoAgrgado() {
		return costoAgrgado;
	}

	public void setCostoAgrgado(float costoAgrgado) {
		this.costoAgrgado = costoAgrgado;
	}
	
	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	@Override
	public String toString() {
		return "MetodoDePago [id=" + id + ", nombre=" + nombre + ", costoAgrgado=" + costoAgrgado + "]";
	}
	
	
}
