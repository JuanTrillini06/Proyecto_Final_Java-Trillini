package com.coderhouse.dao;

import org.springframework.stereotype.Service;

import com.coderhouse.models.Cliente;
import com.coderhouse.models.DetalleFactura;
import com.coderhouse.models.Factura;
import com.coderhouse.models.MetodoDePago;
import com.coderhouse.models.Producto;
import com.coderhouse.models.Vendedor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class DaoFacturacion {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void persistirCliente(Cliente cliente) {
		em.persist(cliente);
	}
	
	@Transactional
	public void persistirVendedor(Vendedor vendedor) {
		em.persist(vendedor);
	}
	
	@Transactional
	public void persistirProducto(Producto producto) {
		em.persist(producto);
	}
	
	@Transactional
	public void persistirFactura(Factura factura) {
		em.persist(factura);
	}
	
	@Transactional
	public void persistirMetodoDePago(MetodoDePago metodoDePago) {
		em.persist(metodoDePago);
	}
	
	@Transactional
	public void persistirDetalleFactura(DetalleFactura detalleFactura) {
		em.persist(detalleFactura);
	}
}
