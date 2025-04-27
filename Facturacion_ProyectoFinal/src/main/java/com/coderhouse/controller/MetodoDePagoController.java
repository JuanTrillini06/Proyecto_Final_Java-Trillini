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

import com.coderhouse.models.MetodoDePago;
import com.coderhouse.services.MetodoDePagoServicio;

@RestController
@RequestMapping("/api/metodosDePago")
public class MetodoDePagoController {

	@Autowired
	private MetodoDePagoServicio metodoDePagoServicio;
	
	@GetMapping
	public List<MetodoDePago> getAllMetodoDePago(){
		return metodoDePagoServicio.findAll();
	}
	
	@GetMapping("/{metodoDePagoId}")
	public ResponseEntity<MetodoDePago> getMetodoDePagoById(@PathVariable Long metodoDePagoId){
		try {
			MetodoDePago metodoDePago = metodoDePagoServicio.findById(metodoDePagoId);
			return ResponseEntity.ok(metodoDePago); //200
	}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
	}catch(Exception err) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
	}
	}
	
	@PostMapping("/create")
	public MetodoDePago createMetodoDePago(@RequestBody MetodoDePago metodoDePago) {
		return metodoDePagoServicio.save(metodoDePago);
	}
	
	@PutMapping("/{metodoDePagoId}")
	public ResponseEntity<MetodoDePago> updateMetodoDePagoById(@PathVariable Long metodoDePagoId, @RequestBody MetodoDePago metodoDePagoActualizado) {
		try {
			MetodoDePago metodoDePago = metodoDePagoServicio.update(metodoDePagoId, metodoDePagoActualizado);
			return ResponseEntity.ok(metodoDePago); //200
		}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deleteMetodoDePagoById(@PathVariable Long metodoDePagoId) {
		try {
			metodoDePagoServicio.deleteById(metodoDePagoId);
			return ResponseEntity.noContent().build(); //404
		}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
}
