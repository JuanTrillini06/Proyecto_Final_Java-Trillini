package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.interfaces.CRUDInterface;
import com.coderhouse.models.Factura;
import com.coderhouse.repository.FacturaRepository;

import jakarta.transaction.Transactional;

@Service
public class FacturaServicio implements CRUDInterface<Factura, Long> {

	@Autowired
	private FacturaRepository facturaRepository;
	
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
		
		if(facturaActualizado.getNombreEmpresa() != null && !facturaActualizado.getNombreEmpresa().isEmpty()) {
			factura.setNombreEmpresa(facturaActualizado.getNombreEmpresa());
		}
		if(facturaActualizado.getNumeroFactura() != 0) {
			factura.setNumeroFactura(facturaActualizado.getNumeroFactura());
		}
		if(facturaActualizado.getMontoTotal() != 0) {
			factura.setMontoTotal(facturaActualizado.getMontoTotal());
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
}
