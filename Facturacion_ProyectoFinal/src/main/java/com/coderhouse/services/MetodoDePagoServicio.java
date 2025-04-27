package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.interfaces.CRUDInterface;
import com.coderhouse.models.MetodoDePago;
import com.coderhouse.repository.MetodoDePagoRepository;

import jakarta.transaction.Transactional;

@Service
public class MetodoDePagoServicio implements CRUDInterface<MetodoDePago, Long> {

	@Autowired
	private MetodoDePagoRepository metodoDePagoRepository;
	
	@Override
	public List<MetodoDePago> findAll() {
		return metodoDePagoRepository.findAll();
	}

	@Override
	public MetodoDePago findById(Long id) {
		return metodoDePagoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Metodo de Pago no encontrado"));
	}

	@Override
	@Transactional
	public MetodoDePago save(MetodoDePago metodoDePagoNuevo) {
		// TODO Auto-generated method stub
		return metodoDePagoRepository.save(metodoDePagoNuevo);
	}

	@Override
	@Transactional
	public MetodoDePago update(Long id, MetodoDePago metodoDePagoActualizado) {
		MetodoDePago metodoDePago = metodoDePagoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Metodo de Pago no encontrado"));
		
		if(metodoDePagoActualizado.getNombre() != null && !metodoDePagoActualizado.getNombre().isEmpty()) {
			metodoDePago.setNombre(metodoDePagoActualizado.getNombre());
		}
		if(metodoDePagoActualizado.getCostoAgrgado() != 0) {
			metodoDePago.setCostoAgrgado(metodoDePagoActualizado.getCostoAgrgado());
		}
		return metodoDePagoRepository.save(metodoDePago);
	}

	@Override
	public void deleteById(Long id) {
		if(!metodoDePagoRepository.existsById(id)) {
			throw new IllegalArgumentException("Metodo de Pago no encontrado");
		}
		metodoDePagoRepository.deleteById(id);
	}
}
