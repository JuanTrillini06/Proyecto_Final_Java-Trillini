package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.interfaces.CRUDInterface;
import com.coderhouse.models.DetalleFactura;
import com.coderhouse.repository.DetalleFacturaRepository;

import jakarta.transaction.Transactional;

@Service
public class DetalleFacturaServicio implements CRUDInterface<DetalleFactura, Long> {

	@Autowired
	private DetalleFacturaRepository detalleFacturaRepository;
	
	@Override
	public List<DetalleFactura> findAll() {
		return detalleFacturaRepository.findAll();
	}

	@Override
	public DetalleFactura findById(Long id) {
		return detalleFacturaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Detalle de Factura no encontrado"));
	}

	@Override
	@Transactional
	public DetalleFactura save(DetalleFactura detalleFacturaNuevo) {
		// TODO Auto-generated method stub
		return detalleFacturaRepository.save(detalleFacturaNuevo);
	}

	@Override
	@Transactional
	public DetalleFactura update(Long id, DetalleFactura detalleFacturaActualizado) {
		DetalleFactura detalleFactura = detalleFacturaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Detalle de Factura no encontrado"));
		
		if(detalleFacturaActualizado.getIdProducto() != 0) {
			detalleFactura.setIdProducto(detalleFacturaActualizado.getIdProducto());
		}
		if(detalleFacturaActualizado.getIdMetodoDePago() != 0) {
			detalleFactura.setIdMetodoDePago(detalleFacturaActualizado.getIdMetodoDePago());
		}
		if(detalleFacturaActualizado.getTipoDeFactura() != null && !detalleFacturaActualizado.getTipoDeFactura().isEmpty()) {
			detalleFactura.setTipoDeFactura(detalleFacturaActualizado.getTipoDeFactura());
		}
		if(detalleFacturaActualizado.getCantidad() != 0) {
			detalleFactura.setCantidad(detalleFacturaActualizado.getCantidad());
		}
		if(detalleFacturaActualizado.getSubtotal() != 0) {
			detalleFactura.setSubtotal(detalleFacturaActualizado.getSubtotal());
		}
		return detalleFacturaRepository.save(detalleFactura);
	}

	@Override
	public void deleteById(Long id) {
		if(!detalleFacturaRepository.existsById(id)) {
			throw new IllegalArgumentException("Detalle de Factura no encontrado");
		}
		detalleFacturaRepository.deleteById(id);
	}
}
