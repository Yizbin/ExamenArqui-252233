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
        try {
            modelo.agregarProducto(idProducto, cantidad);
            modelo.notificarExito("Añadido", "Producto agregado al carrito");
        } catch (Exception ex) {
            modelo.nofiticarError(ex.getMessage());
        }
    }

    public void procesarCompra(String numeroTarjeta) {
       try {
            modelo.procesarCompraCompleta(numeroTarjeta);
            modelo.notificarExito("Éxito", "¡Pago procesado con éxito!");
        } catch (Exception ex) {
            modelo.nofiticarError(ex.getMessage());
        }
    }

    public void eliminarProducto(int idProducto) {
        try {
            modelo.eliminarProducto(idProducto);
        } catch (Exception ex) {
            modelo.nofiticarError(ex.getMessage());
        }
    }
}
