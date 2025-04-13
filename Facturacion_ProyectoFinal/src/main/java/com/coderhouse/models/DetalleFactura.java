package com.coderhouse.models;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalleFacturas")
public class DetalleFactura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, insertable=false, updatable=false)
	private Long idFactura;
	
	@Column(nullable = false)
	private Long idProducto;
	
	@Column(nullable = false)
	private Long idMetodoDePago;
	
	@Column()
	private String tipoDeFactura;
	
	@Column(nullable = false)
	private int cantidad;
	
	@Column(nullable = false)
	private int subtotal;
	
	@ManyToMany(mappedBy = "detallesFacturas")
	private List<Producto> productos;

	@ManyToOne
	@JoinColumn(name = "idFactura")
	private Factura factura;
	
	public DetalleFactura() {
		super();
	}

	public DetalleFactura(Long idFactura, Long idProducto, Long idMetodoDePago, String tipoDeFactura, int cantidad,
			int subtotal) {
		super();
		this.idFactura = idFactura;
		this.idProducto = idProducto;
		this.idMetodoDePago = idMetodoDePago;
		this.tipoDeFactura = tipoDeFactura;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	
	public Long getIdMetodoDePago() {
		return idMetodoDePago;
	}

	public void setIdMetodoDePago(Long idMetodoDePago) {
		this.idMetodoDePago = idMetodoDePago;
	}

	
	public String getTipoDeFactura() {
		return tipoDeFactura;
	}

	public void setTipoDeFactura(String tipoDeFactura) {
		this.tipoDeFactura = tipoDeFactura;
	}
	
	
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "DetalleFactura [id=" + id + ", idFactura=" + idFactura + ", idProducto=" + idProducto
				+ ", idMetodoDePago=" + idMetodoDePago + ", cantidad=" + cantidad + ", subtotal=" + subtotal + "]";
	}

	
}
