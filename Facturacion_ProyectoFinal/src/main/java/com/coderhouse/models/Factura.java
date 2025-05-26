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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "facturas")
public class Factura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private int numeroFactura;
	
	@Column(nullable = false)
	private int cantidad;
	
	
	@Column(nullable = false)
	private float subTotal;
	
	
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;
	
    @ManyToMany
    @JoinTable(
        name = "factura_producto",
        joinColumns = @JoinColumn(name = "factura_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;


	
	public Factura() {
		super();
	}

	public Factura(int numeroFactura,int cantidad,  float subTotal) {
		super();
		this.numeroFactura = numeroFactura;
		this.cantidad = cantidad;
		this.subTotal = subTotal;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

	@Override
	public String toString() {
		return "Factura [numeroFactura=" + numeroFactura + ", cantidad=" + cantidad + ", subTotal=" + subTotal
				+ ", cliente=" + cliente + "]";
	}	
}
