package com.coderhouse.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false, unique = true)
	private String codigo;
	
	@Column(nullable = false)
	private float precioUnitario;
	
	@Column(nullable = false)
	private int stock;

	@ManyToMany
	@JoinTable(
			name = "producto_detalle_factura",
			joinColumns = @JoinColumn(name = "idProducto"),
			inverseJoinColumns = @JoinColumn(name = "idDetalleFactura")
			)
	private List<DetalleFactura> detallesFacturas;
	public Producto() {
		super();
	}

	public Producto(String nombre, String codigo, float precioUnitario, int stock) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.precioUnitario = precioUnitario;
		this.stock = stock;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	

	public List<DetalleFactura> getDetallesFacturas() {
		return detallesFacturas;
	}

	public void setDetallesFacturas(List<DetalleFactura> detallesFacturas) {
		this.detallesFacturas = detallesFacturas;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", precioUnitario=" + precioUnitario
				+ ", stock=" + stock + "]";
	}
	
	
}
