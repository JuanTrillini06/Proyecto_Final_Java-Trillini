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

import com.coderhouse.models.Vendedor;
import com.coderhouse.services.VendedorServicio;



@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

	@Autowired
	private VendedorServicio vendedorServicio;
	
	@GetMapping
	public List<Vendedor> getAllVendedores(){
		return vendedorServicio.findAll();
	}
	
	@GetMapping("/{vendedorId}")
	public ResponseEntity<Vendedor> getVendedorById(@PathVariable Long vendedorId){
		try {
			Vendedor vendedor = vendedorServicio.findById(vendedorId);
			return ResponseEntity.ok(vendedor); //200
	}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
	}catch(Exception err) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
	}
	}
	
	@PostMapping("/create")
	public Vendedor createVendedor(@RequestBody Vendedor vendedor) {
		return vendedorServicio.save(vendedor);
	}
	
	@PutMapping("/{vendedorId}")
	public ResponseEntity<Vendedor> updateVendedorById(@PathVariable Long vendedorId, @RequestBody Vendedor vendedorActualizado) {
		try {
			Vendedor vendedor = vendedorServicio.update(vendedorId, vendedorActualizado);
			return ResponseEntity.ok(vendedor); //200
		}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
	
	@DeleteMapping("/{vendedorId}")
	public ResponseEntity<Void> deleteVendedorById(@PathVariable Long vendedorId) {
		try {
			vendedorServicio.deleteById(vendedorId);
			return ResponseEntity.noContent().build(); //404
		}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
}
