package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "facturas")
public class Factura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombreEmpresa; // Colocar el nombre de la empresa para dejarla fija, se da la opcion para poder cambiarla dependiendo la empresa
	
	@Column(nullable = false, unique = true)
	private int numeroFactura;
	
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private float montoTotal;
	
	@ManyToOne
	@JoinColumn(name = "idVendedor")
	private Vendedor vendedor;
	
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "idMetodo")
	private MetodoDePago metodoDePago;
	
	@OneToMany(mappedBy = "factura")
	private List<DetalleFactura> detalles;
	
	public Factura() {
		super();
	}

	public Factura(String nombreEmpresa,int numeroFactura,  float montoTotal) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.numeroFactura = numeroFactura;
		this.montoTotal = montoTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public int getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public float getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}
	
	
	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

	public MetodoDePago getMetodoDePago() {
		return metodoDePago;
	}

	public void setMetodoDePago(MetodoDePago metodoDePago) {
		this.metodoDePago = metodoDePago;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<DetalleFactura> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleFactura> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", nombreEmpresa=" + nombreEmpresa + ", numeroFactura=" + numeroFactura
				+ ", montoTotal=" + montoTotal + "]";
	}

	
}
