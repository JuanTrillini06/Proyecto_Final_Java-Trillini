package com.coderhouse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coderhouse.dao.DaoFacturacion;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.DetalleFactura;
import com.coderhouse.models.Factura;
import com.coderhouse.models.MetodoDePago;
import com.coderhouse.models.Producto;
import com.coderhouse.models.Vendedor;

@SpringBootApplication
public class FacturacionProyectoFinalApplication implements CommandLineRunner{
	
	@Autowired
	private DaoFacturacion dao;
	
	public static void main(String[] args) {
		SpringApplication.run(FacturacionProyectoFinalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		try {
				
				Cliente cliente1 = new Cliente("Juan","Trillini","juanjose_207@hotmail.com",43601621,155222888);
				
				Vendedor vendedor1 = new Vendedor("Jose", "Pedro", 35, 66999555, "L66999555");
				
				Producto producto1 = new Producto("PantalonJean", "P001", 22000, 600);
				Producto producto2 = new Producto("PantalonJean", "P002", 27000, 250);
				Producto producto3 = new Producto("RemeraAlgodon", "R001", 11000, 1500);
				Producto producto4 = new Producto("SueterLana", "S001", 32000, 100);
				Producto producto5 = new Producto("RemeraAlgodon", "R002", 10500, 1750);
				
				Factura factura1 = new Factura("JavaCoder",1, 00000); // El ultimo valor es un campo calculado
				
				MetodoDePago metodoDePago1 = new MetodoDePago("Credito", 20);
				MetodoDePago metodoDePago2 = new MetodoDePago("Debito", 0);
				MetodoDePago metodoDePago3 = new MetodoDePago("Efectivo", -5);
				
				dao.persistirCliente(cliente1);
				
				dao.persistirVendedor(vendedor1);
				
				dao.persistirProducto(producto1);
				dao.persistirProducto(producto2);
				dao.persistirProducto(producto3);
				dao.persistirProducto(producto4);
				dao.persistirProducto(producto5);
				
				dao.persistirFactura(factura1);
				
				dao.persistirMetodoDePago(metodoDePago1);
				dao.persistirMetodoDePago(metodoDePago2);
				dao.persistirMetodoDePago(metodoDePago3);
				
				// No se crean detalles de facturas ya que se requiere realizar la llamada a la base de datos para hacer un Get de los Ids correspondientes
				
		}catch(Exception err) {
			err.printStackTrace(System.err);
		}
		
	}

}
