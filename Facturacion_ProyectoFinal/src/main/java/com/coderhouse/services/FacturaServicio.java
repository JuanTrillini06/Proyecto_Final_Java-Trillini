package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.CompraDTO;
import com.coderhouse.interfaces.CRUDInterface;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Factura;
import com.coderhouse.models.Producto;
import com.coderhouse.repository.FacturaRepository;
import com.coderhouse.repository.ClienteRepository;
import com.coderhouse.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class FacturaServicio implements CRUDInterface<Factura, Long> {

	@Autowired
	private FacturaRepository facturaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public List<Factura> findAll() {
		return facturaRepository.findAll();
	}

	@Override
	public Factura findById(Long id) {
		return facturaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Factura no encontrada"));
	}

	@Override
	@Transactional
	public Factura save(Factura facturaeNuevo) {
		// TODO Auto-generated method stub
		return facturaRepository.save(facturaeNuevo);
	}

	@Override
	@Transactional
	public Factura update(Long id, Factura facturaActualizado) {
		Factura factura = facturaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Factura no encontrada"));
		
		if(facturaActualizado.getNumeroFactura() != 0) {
			factura.setNumeroFactura(facturaActualizado.getNumeroFactura());
		}
		if(facturaActualizado.getCantidad() != 0) {
			factura.setCantidad(facturaActualizado.getCantidad());
		}

		if(facturaActualizado.getSubTotal() != 0) {
			factura.setSubTotal(facturaActualizado.getSubTotal());
		}
		return facturaRepository.save(factura);
	}

	@Override
	public void deleteById(Long id) {
		if(!facturaRepository.existsById(id)) {
			throw new IllegalArgumentException("Factura no encontrada");
		}
		facturaRepository.deleteById(id);
	}
	
	@Transactional
	public Factura compra(CompraDTO dto) {
		
		Factura factura = facturaRepository.findById(dto.getFacturaId()).orElseThrow(() -> new IllegalArgumentException("Factura no encontrada"));
		
		Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
		
		Producto producto = productoRepository.findById(dto.getProductoId()).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
		
		cliente.getFacturas().add(factura);
		
		factura.getProductos().add(producto);
		
		clienteRepository.save(cliente);
		
		return facturaRepository.save(factura);
			
	}

}
