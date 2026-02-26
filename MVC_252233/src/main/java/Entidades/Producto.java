/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Abraham Coronel
 */
public class Producto {

    private final int id;
    private final String nombre;
    private final double costo;

    public Producto(int id, String nombre, double costo) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
    }

    public void validar() throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre del producto no puede estar vacío.");
        }
        if (costo < 0) {
            throw new Exception("El costo del producto no puede ser negativo.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public double getCosto() {
        return costo;
    }

    public int getId() {
        return id;
    }

}
