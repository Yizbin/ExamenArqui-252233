/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC_ComprarProducto;

/**
 *
 * @author Abraham Coronel
 */
public class Controlador {

    private final IControlModelo modelo;

    public Controlador(IControlModelo modelo) {
        this.modelo = modelo;
    }

    public void seleccionarProducto(int idProducto, int cantidad) {
        modelo.agregarProducto(idProducto, cantidad);
    }

    public void procesarCompra(String numeroTarjeta) {
        modelo.procesarCompraCompleta(numeroTarjeta);
    }

    public void pagar() {
        modelo.procesarPago();
    }

    public void eliminarProducto(int idProducto) {
        modelo.eliminarProducto(idProducto);
    }
}
