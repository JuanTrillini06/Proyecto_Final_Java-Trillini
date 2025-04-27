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

import com.coderhouse.models.DetalleFactura;
import com.coderhouse.services.DetalleFacturaServicio;

@RestController
@RequestMapping("/api/detallesFacturas")
public class DetalleFacturaController {
	
	@Autowired
	private DetalleFacturaServicio detalleFacturaServicio;
	
	@GetMapping
	public List<DetalleFactura> getAllDetalleFactura(){
		return detalleFacturaServicio.findAll();
	}
	
	@GetMapping("/{detalleFacturaId}")
	public ResponseEntity<DetalleFactura> getDetalleFacturaById(@PathVariable Long detalleFacturaId){
		try {
			DetalleFactura detalleFactura = detalleFacturaServicio.findById(detalleFacturaId);
			return ResponseEntity.ok(detalleFactura); //200
	}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
	}catch(Exception err) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
	}
	}
	
	@PostMapping("/create")
	public DetalleFactura createDetalleFactura(@RequestBody DetalleFactura detalleFactura) {
		return detalleFacturaServicio.save(detalleFactura);
	}
	
	@PutMapping("/{detalleFacturaId}")
	public ResponseEntity<DetalleFactura> updateDetalleFacturaById(@PathVariable Long detalleFacturaId, @RequestBody DetalleFactura detalleFacturaActualizado) {
		try {
			DetalleFactura detalleFactura = detalleFacturaServicio.update(detalleFacturaId, detalleFacturaActualizado);
			return ResponseEntity.ok(detalleFactura); //200
		}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
	
	@DeleteMapping("/{detalleFacturaId}")
	public ResponseEntity<Void> deleteDetalleFacturaById(@PathVariable Long detalleFacturaId) {
		try {
			detalleFacturaServicio.deleteById(detalleFacturaId);
			return ResponseEntity.noContent().build(); //404
		}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
}
