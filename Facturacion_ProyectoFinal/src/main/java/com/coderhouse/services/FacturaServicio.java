package com.coderhouse.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.CompraDTO;
import com.coderhouse.dto.LineasDTO;
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

	@Autowired
	private ProductoServicio productoServicio; // Servicio para validar el producto.
	
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
	
		// Comprobante que se va a ir cargando los datos y va a ser devuelto. 
		Factura comprobante = new Factura();
		
		Optional<Cliente> c = clienteRepository.findById(dto.getCliente().getId());
		ArrayList<Producto> listaProductos = new ArrayList<>();
		Integer cantidad = 0;
		Float subtotal = 0.0f; 


		// Validamos cliente
		if (c.isPresent()){

			Cliente cli = c.get();
			comprobante.setCliente(cli);

			// Validamos productos
			for (LineasDTO l : dto.getLineas()){

				if(productoServicio.validarProductos(l.getProducto(), l.getCantidad())){
					
					// Obtengo el obj producto.
					Optional<Producto> pOptional = productoRepository.findById(l.getProducto().getId());
					listaProductos.add(pOptional.get());

					cantidad+=l.getCantidad();
					subtotal+=pOptional.get().getPrecioUnitario();

					
					comprobante.setProductos(listaProductos);
				
				} else {
					break; 
				}

			}
			comprobante.setCantidad(cantidad);
			comprobante.setSubTotal(subtotal);
			comprobante.setNumeroFactura((int) (Math.random()*200)+1); //Numero de factura aleatorio
			comprobante.setFecha(LocalDate.now()); // Fecha en la que se crea el comprobante. 
			facturaRepository.save(comprobante); // Registro el comprobante

		} else {
			
			
		
		}

		return comprobante;
			
	}

}
