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

    public void seleccionarProducto(int idProducto, int cantidad) throws Exception {
        modelo.agregarProducto(idProducto, cantidad);
    }

    public void procesarCompra(String numeroTarjeta) throws Exception {
        modelo.procesarCompraCompleta(numeroTarjeta);
    }

    public void eliminarProducto(int idProducto) throws Exception {
        modelo.eliminarProducto(idProducto);
    }
}
