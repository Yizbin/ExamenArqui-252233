/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import MVC_ComprarProducto.Controlador;
import MVC_ComprarProducto.ModeloCompra;
import MVC_ComprarProducto.PantallaCarrito;
import MVC_ComprarProducto.PantallaTicket;
import javax.swing.SwingUtilities;

/**
 *
 * @author Abraham Coronel
 */
public class Ensamblador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ModeloCompra modelo = new ModeloCompra();
        Controlador controlador = new Controlador(modelo);

        PantallaCarrito pantallaCarrito = new PantallaCarrito(controlador, modelo);
        PantallaTicket pantallaTicket = new PantallaTicket(controlador, modelo);

        modelo.agregarSuscriptor(pantallaCarrito);
        modelo.agregarSuscriptorPago(pantallaTicket);

        pantallaCarrito.update();

        SwingUtilities.invokeLater(() -> {
            pantallaCarrito.setLocationRelativeTo(null);
            pantallaCarrito.setVisible(true);
        });

    }
}
