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

import com.coderhouse.models.Producto;
import com.coderhouse.services.ProductoServicio;



@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private ProductoServicio productoServicio;
	
	@GetMapping
	public List<Producto> getAllProductos(){
		return productoServicio.findAll();
	}
	
	@GetMapping("/{productoId}")
	public ResponseEntity<Producto> getProductoById(@PathVariable Long productoId){
		try {
			Producto producto = productoServicio.findById(productoId);
			return ResponseEntity.ok(producto); //200
	}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
	}catch(Exception err) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
	}
	}
	
	@PostMapping("/create")
	public Producto createProducto(@RequestBody Producto producto) {
		return productoServicio.save(producto);
	}
	
	@PutMapping("/{productoId}")
	public ResponseEntity<Producto> updateProductoById(@PathVariable Long productoId, @RequestBody Producto productoActualizado) {
		try {
			Producto producto = productoServicio.update(productoId, productoActualizado);
			return ResponseEntity.ok(producto); //200
		}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
	
	@DeleteMapping("/{productoId}")
	public ResponseEntity<Void> deleteProductoById(@PathVariable Long productoId) {
		try {
			productoServicio.deleteById(productoId);
			return ResponseEntity.noContent().build(); //404
		}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
}
