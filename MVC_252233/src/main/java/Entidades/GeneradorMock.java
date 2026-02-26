/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abraham Coronel
 */
public class GeneradorMock {

    public static List<Producto> obtenerProductosDisponibles() {
        List<Producto> productos = new ArrayList<>();

        productos.add(new Producto(1, "Shampoo", 20.00));
        productos.add(new Producto(2, "Cepillo", 35.00));
        productos.add(new Producto(3, "Ps5", 8500));
        productos.add(new Producto(4, "RTX 5090", 1500.00));
        productos.add(new Producto(5, "Doritos 43g", 25.00));

        return productos;
    }

    public static Tarjeta obtenerTarjetaPrueba() {
        return new Tarjeta("4152-3134-5678-9012", "Banamex", "Ciudad Obregon");
    }

    public static Compra obtenerCompraPrueba() {
        Compra compra = new Compra();

        try {
            Producto p1 = new Producto(1, "Producto A", 20.00);
            Producto p2 = new Producto(2, "Producto B", 35.00);

            compra.agregarDetalle(new DetalleCompra(p1, 2));
            compra.agregarDetalle(new DetalleCompra(p2, 1));

            compra.calcularTotal(); 
            compra.confirmar();
            
        } catch (Exception e) {
            System.err.println("Error al generar la compra mock: " + e.getMessage());
        }

        return compra;
    }
}
