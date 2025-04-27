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

import com.coderhouse.models.Cliente;
import com.coderhouse.services.ClienteServicio;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteServicio clienteServicio;
	
	@GetMapping
	public List<Cliente> getAllClientes(){
		return clienteServicio.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long clienteId){
		try {
			Cliente cliente = clienteServicio.findById(clienteId);
			return ResponseEntity.ok(cliente); //200
	}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
	}catch(Exception err) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
	}
	}
	
	@PostMapping("/create")
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clienteServicio.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> updateClienteById(@PathVariable Long clienteId, @RequestBody Cliente clienteActualizado) {
		try {
			Cliente cliente = clienteServicio.update(clienteId, clienteActualizado);
			return ResponseEntity.ok(cliente); //200
		}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deleteClienteById(@PathVariable Long clienteId) {
		try {
			clienteServicio.deleteById(clienteId);
			return ResponseEntity.noContent().build(); //404
		}catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build(); //404
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}

}
