package com.coderhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coderhouse.dto.CompraDTO; 


import com.coderhouse.models.Factura;
import com.coderhouse.services.FacturaServicio;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

	@Autowired
	private FacturaServicio facturaServicio;
	
	@GetMapping
	public List<Factura> getAllFacturas(){
		return facturaServicio.findAll();
	}
	
	@GetMapping("/{facturaId}")
	public ResponseEntity<String> getFacturaById(@PathVariable Long facturaId){
		try {
			Factura factura = facturaServicio.findById(facturaId);
			return ResponseEntity.ok(factura.toString()); //200
	}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
	}catch(Exception err) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
	}
	}
	
	@PostMapping("/create")
	public Factura createFactura(@RequestBody Factura factura) {
		return facturaServicio.save(factura);
	}
	
	@PutMapping("/{facturaId}")
	public ResponseEntity<Factura> updateFacturaById(@PathVariable Long facturaId, @RequestBody Factura facturaActualizado) {
		try {
			Factura factura = facturaServicio.update(facturaId, facturaActualizado);
			return ResponseEntity.ok(factura); //200
		}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
	
	@DeleteMapping("/{facturaId}")
	public ResponseEntity<Void> deleteFacturaById(@PathVariable Long facturaId) {
		try {
			facturaServicio.deleteById(facturaId);
			return ResponseEntity.noContent().build(); //404
		}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
	
	@PostMapping("/compra")
	public ResponseEntity<?> CompraDTO(@RequestBody CompraDTO dto) {
		try {
			
			Factura factura = facturaServicio.compra(dto);
			return ResponseEntity.ok(factura.toString()); // 200 - Devuelve el detalle de la factura que se creo.
		
		
		}catch (IllegalStateException err) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(err.getMessage()); // 409
		
		
		} catch (IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); // 404
		
		
		} catch (Exception err) {
			return ResponseEntity.internalServerError().build(); // 500
		}
	}
}
