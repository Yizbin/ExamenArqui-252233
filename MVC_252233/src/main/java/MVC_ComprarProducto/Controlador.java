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
        try {
            modelo.agregarProducto(idProducto, cantidad);
        } catch (Exception e) {
            System.err.println("Controlador [Log]: Error al agregar producto - " + e.getMessage());
            throw e;
        }
    }

    public void ingresarTarjeta(String numero) throws Exception {
        try {
            modelo.registrarTarjeta(numero);
        } catch (Exception e) {
            System.err.println("Controlador [Log]: Error al registrar tarjeta - " + e.getMessage());
            throw e;
        }
    }

    public void pagar() throws Exception {
        try {
            modelo.procesarPago();
        } catch (Exception e) {
            System.err.println("Controlador [Log]: Error al procesar pago - " + e.getMessage());
            throw e;
        }
    }
}
