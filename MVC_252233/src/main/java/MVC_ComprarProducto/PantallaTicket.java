/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC_ComprarProducto;

import javax.swing.*;

/**
 *
 * @author Abraham Coronel
 */
public class PantallaTicket extends JFrame implements ISuscriptor {

    private final Controlador controlador;
    private final IModeloVista modeloVista;

    public PantallaTicket(Controlador controlador, IModeloVista modeloVista) {
        this.controlador = controlador;
        this.modeloVista = modeloVista;
    }

    public void mostrarTicket() {
    }

    @Override
    public void update() {
        mostrarTicket();
    }

}
