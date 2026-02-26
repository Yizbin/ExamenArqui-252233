/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Abraham Coronel
 */
public class DetalleCompra {

    private final Producto producto;
    private final int cantidad;

    public DetalleCompra(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public void validar() throws Exception {
        if (producto == null) {
            throw new Exception("El producto seleccionado no es valido.");
        }
        producto.validar(); 
        
        if (cantidad <= 0) {
            throw new Exception("La cantidad a comprar debe ser al menos 1.");
        }
    }

    public double getSubtotal() {
        return producto.getCosto() * cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

}
