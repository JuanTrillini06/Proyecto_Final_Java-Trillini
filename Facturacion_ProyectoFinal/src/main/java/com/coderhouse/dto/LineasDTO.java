package com.coderhouse.dto;

import com.coderhouse.models.Producto;

public class LineasDTO {
    
    private int cantidad;
    private Producto producto;


    public int getCantidad(){
        return cantidad;
    }

    public void setCantidad(Integer c){
        this.cantidad = c;
    }

    
    public Producto getProducto(){
        return producto;
    }

    public void setProducto(Producto p){
        this.producto = p ;
    }

    


}
