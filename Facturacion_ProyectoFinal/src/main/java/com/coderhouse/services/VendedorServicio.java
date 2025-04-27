package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.interfaces.CRUDInterface;
import com.coderhouse.models.Vendedor;
import com.coderhouse.repository.VendedorRepository;

import jakarta.transaction.Transactional;

@Service
public class VendedorServicio implements CRUDInterface<Vendedor, Long> {

	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Override
	public List<Vendedor> findAll() {
		return vendedorRepository.findAll();
	}

	@Override
	public Vendedor findById(Long id) {
		return vendedorRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Vendedor no encontrado"));
	}

	@Override
	@Transactional
	public Vendedor save(Vendedor vendedorNuevo) {
		// TODO Auto-generated method stub
		return vendedorRepository.save(vendedorNuevo);
	}

	@Override
	@Transactional
	public Vendedor update(Long id, Vendedor vendedorActualizado) {
		Vendedor vendedor = vendedorRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Vendedor no encontrado"));
		
		if(vendedorActualizado.getNombre() != null && !vendedorActualizado.getNombre().isEmpty()) {
			vendedor.setNombre(vendedorActualizado.getNombre());
		}
		if(vendedorActualizado.getApellido() != null && !vendedorActualizado.getApellido().isEmpty()) {
			vendedor.setApellido(vendedorActualizado.getApellido());
		}
		if(vendedorActualizado.getEdad() != 0) {
			vendedor.setEdad(vendedorActualizado.getEdad());
		}
		if(vendedorActualizado.getDni() != 0) {
			vendedor.setDni(vendedorActualizado.getDni());
		}
		if(vendedorActualizado.getLegajo() != null && !vendedorActualizado.getLegajo().isEmpty()) {
			vendedor.setLegajo(vendedorActualizado.getLegajo());
		}
		return vendedorRepository.save(vendedor);
	}

	@Override
	public void deleteById(Long id) {
		if(!vendedorRepository.existsById(id)) {
			throw new IllegalArgumentException("Vendedor no encontrado");
		}
		vendedorRepository.deleteById(id);
	}
}
