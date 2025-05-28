package com.coderhouse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.interfaces.CRUDInterface;
import com.coderhouse.models.Producto;
import com.coderhouse.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoServicio implements CRUDInterface<Producto, Long> {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public Producto findById(Long id) {
		return productoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Producto no encontrado"));
	}

	@Override
	@Transactional
	public Producto save(Producto productoNuevo) {
		// TODO Auto-generated method stub
		return productoRepository.save(productoNuevo);
	}

	@Override
	@Transactional
	public Producto update(Long id, Producto productoActualizado) {
		Producto producto = productoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Producto no encontrado"));
		
		if(productoActualizado.getNombre() != null && !productoActualizado.getNombre().isEmpty()) {
			producto.setNombre(productoActualizado.getNombre());
		}
		if(productoActualizado.getPrecioUnitario() != 0) {
			producto.setPrecioUnitario(productoActualizado.getPrecioUnitario());
		}
		if(productoActualizado.getStock() != 0) {
			producto.setStock(productoActualizado.getStock());
		}
		return productoRepository.save(producto);
	}

	@Override
	public void deleteById(Long id) {
		if(!productoRepository.existsById(id)) {
			throw new IllegalArgumentException("Producto no encontrado");
		}
		productoRepository.deleteById(id);
	}


	public Boolean validarProductos(Producto p, Integer c){

		Boolean state = false; 

		Optional<Producto> producto = productoRepository.findById(p.getId());

		// Recibo el producto (valido si existe o no ) y la cantidad (valido si hay stock para ese mismo.)
		if (producto.isPresent()){
			Producto prod = producto.get();

			if (prod.getStock() >= c){
				System.out.println("" + "stock: " + prod.getStock() + " CANTIDAD: " + c);
				this.descontarStock(prod, c); // Actualizo stock 
				state = true;
			} else {
				state = false;
			}

		} else {
			state = false;
		}

		return state;
		

	}

	public void descontarStock(Producto p, Integer cantidadDes){
		
		Integer nuevoStock = (p.getStock() - cantidadDes);
		p.setStock(nuevoStock);
		productoRepository.save(p);

	}







}
