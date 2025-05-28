package com.coderhouse.dto;

import java.util.ArrayList;

import com.coderhouse.models.Cliente;

public class CompraDTO {
	
	// Atrb
	private Cliente cliente;
	private ArrayList<LineasDTO> lineas; 

	//Meth
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente c) {
		this.cliente = c;
	}

	public ArrayList<LineasDTO> getLineas() {
		return lineas;
	}

	public void setLineas(ArrayList<LineasDTO> lineasComprobante) {
		this.lineas = lineasComprobante;
	}

}
