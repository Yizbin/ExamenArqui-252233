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

        productos.add(new Producto(1, "Shampoo Sedal", 45.50, "img/Shampoo.png"));
        productos.add(new Producto(2, "Cepillo Dental", 25.00, "img/Cepillo.jpg"));
        productos.add(new Producto(3, "Consola PS5", 9500.00, "img/Ps5.png"));
        productos.add(new Producto(4, "Tarjeta Grafica RTX 4090", 35000.00, "img/Rtx.png"));
        productos.add(new Producto(5, "Doritos Nacho", 18.50, "img/Doritos.jpg"));

        return productos;
    }

    public static Tarjeta obtenerTarjetaPrueba() {
        return new Tarjeta("4152-3134-5678-9012", "Banamex", "Ciudad Obregon");
    }

}
