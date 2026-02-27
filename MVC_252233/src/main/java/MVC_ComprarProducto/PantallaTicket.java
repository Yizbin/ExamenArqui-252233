/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC_ComprarProducto;

import Entidades.DetalleCompra;
import Entidades.Tarjeta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Abraham Coronel
 */
public class PantallaTicket extends JFrame implements ISuscriptorPago {

    private final IModeloVista modeloVista;
    private JPanel panelContenido;

    public PantallaTicket(IModeloVista modeloVista) {
        this.modeloVista = modeloVista;
        configurarUI();
    }

    private void configurarUI() {
        setTitle("Recibo de Compra");
        setSize(400, 500);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        // IMPORTANTE: HIDE_ON_CLOSE para que al cerrar el ticket no se cierre toda la app
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Título superior
        JLabel lblTitulo = new JLabel("TICKET DE PAGO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // Contenedor principal estilo "lista"
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(panelContenido);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);

        // Botón inferior para cerrar
        JButton btnCerrar = new JButton("Cerrar Ticket");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCerrar.addActionListener(e -> setVisible(false));

        JPanel panelSur = new JPanel();
        panelSur.setBackground(Color.WHITE);
        panelSur.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelSur.add(btnCerrar);
        add(panelSur, BorderLayout.SOUTH);
    }

    private void mostrarTicket() {
        panelContenido.removeAll();
        List<DetalleCompra> seleccionados = modeloVista.getProductosSeleccionados();
        Tarjeta tarjeta = modeloVista.getDatosTarjeta();

        if (seleccionados == null || seleccionados.isEmpty() || tarjeta == null) {
            return;
        }

        // 1. Mensaje de Éxito y Datos de Tarjeta
        JLabel lblExito = new JLabel("¡Pago Aprobado!");
        lblExito.setFont(new Font("Arial", Font.BOLD, 16));
        lblExito.setForeground(new Color(0, 153, 51)); // Verde oscuro

        JPanel panelInfo = new JPanel(new GridLayout(3, 1));
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(0, 10, 10, 10)
        ));
        panelInfo.add(lblExito);

        // Extraemos los últimos 4 dígitos de la tarjeta de forma segura
        String numTarjeta = tarjeta.getNumero();
        String terminacion = numTarjeta.length() >= 4 ? numTarjeta.substring(numTarjeta.length() - 4) : numTarjeta;

        panelInfo.add(new JLabel("Método: Tarjeta terminación " + terminacion));
        panelInfo.add(new JLabel("Banco: " + tarjeta.getBanco() + " | " + tarjeta.getCiudad()));
        panelInfo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        panelContenido.add(panelInfo);
        panelContenido.add(Box.createVerticalStrut(10));

        // 2. Desglose de productos (Igual a tu Resumen)
        for (DetalleCompra detalle : seleccionados) {
            JPanel fila = new JPanel(new BorderLayout());
            fila.setBackground(Color.WHITE);
            fila.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
            fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));

            fila.add(new JLabel(detalle.getCantidad() + "x " + detalle.getProducto().getNombre()), BorderLayout.WEST);
            fila.add(new JLabel(String.format("$%.2f", detalle.getSubtotal())), BorderLayout.EAST);
            panelContenido.add(fila);
        }

        // 3. Total Final
        JPanel panelTotal = new JPanel(new BorderLayout());
        panelTotal.setBackground(Color.WHITE);
        panelTotal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel lblTotalTxt = new JLabel("TOTAL COBRADO:");
        lblTotalTxt.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel lblTotalNum = new JLabel(String.format("$%.2f", modeloVista.getTotal()));
        lblTotalNum.setFont(new Font("Arial", Font.BOLD, 14));

        panelTotal.add(lblTotalTxt, BorderLayout.WEST);
        panelTotal.add(lblTotalNum, BorderLayout.EAST);
        panelTotal.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        panelContenido.add(Box.createVerticalStrut(10));
        panelContenido.add(panelTotal);

        panelContenido.revalidate();
        panelContenido.repaint();
    }

    @Override
    public void compraExitosa() {
        mostrarTicket();
        setVisible(true);
    }
}
