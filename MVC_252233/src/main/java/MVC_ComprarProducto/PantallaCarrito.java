/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC_ComprarProducto;

import Entidades.DetalleCompra;
import Entidades.Producto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Abraham Coronel
 */
public class PantallaCarrito extends JFrame implements ISuscriptor {

    private final Controlador controlador;
    private final IModeloVista modeloVista;

    private JPanel panelProductos;
    private JPanel panelResumen;
    private JLabel lblTotal;
    private JButton btnPagar;

    public PantallaCarrito(Controlador controlador, IModeloVista modeloVista) {
        this.controlador = controlador;
        this.modeloVista = modeloVista;

        configurarUI();
    }

    private void configurarUI() {
        setTitle("Catalogo de Productos");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        panelProductos = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelProductos.setBackground(Color.WHITE);
        JScrollPane scrollProductos = new JScrollPane(panelProductos);
        scrollProductos.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                "Catalogo de Productos",
                0, 0, new Font("Arial", Font.BOLD, 20)));
        scrollProductos.getViewport().setBackground(Color.WHITE);
        add(scrollProductos, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(Color.WHITE);

        panelResumen = new JPanel();
        panelResumen.setLayout(new BoxLayout(panelResumen, BoxLayout.Y_AXIS));
        panelResumen.setBackground(Color.WHITE);
        JScrollPane scrollResumen = new JScrollPane(panelResumen);
        scrollResumen.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                "Resumen de compra",
                0, 0, new Font("Arial", Font.BOLD, 16)));
        scrollResumen.setPreferredSize(new Dimension(800, 180));
        panelInferior.add(scrollResumen, BorderLayout.CENTER);

        JPanel panelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        panelTotal.setBackground(Color.WHITE);
        panelTotal.setBorder(BorderFactory.createTitledBorder("Resumen Total"));

        lblTotal = new JLabel("Total acumulado: $0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        panelTotal.add(lblTotal);

        btnPagar = new JButton("Pagar");
        btnPagar.setFont(new Font("Arial", Font.BOLD, 14));
        btnPagar.addActionListener(e -> clickPagar());
        panelTotal.add(btnPagar);

        panelInferior.add(panelTotal, BorderLayout.SOUTH);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public void mostrarProductos() {
        panelProductos.removeAll();
        List<Producto> disponibles = modeloVista.getProductosDisponibles();

        if (disponibles != null) {
            for (Producto p : disponibles) {
                JPanel card = new JPanel();
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                card.setPreferredSize(new Dimension(180, 200));
                card.setBackground(Color.WHITE);

                JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
                topPanel.setOpaque(false);
                JButton btnAgregar = new JButton("Añadir");
                btnAgregar.setFont(new Font("Arial", Font.PLAIN, 10));
                btnAgregar.addActionListener(e -> clickAgregarProducto(p.getId(), 1));
                topPanel.add(btnAgregar);

                String rutaImagen = "";
                switch (p.getId()) {
                    case 1:
                        rutaImagen = "img/Shampoo.png";
                        break;
                    case 2:
                        rutaImagen = "img/Cepillo.jpg";
                        break;
                    case 3:
                        rutaImagen = "img/Ps5.png";
                        break;
                    case 4:
                        rutaImagen = "img/Rtx.png";
                        break;
                    case 5:
                        rutaImagen = "img/Doritos.jpg";
                        break;
                    default:
                        rutaImagen = "img/default.png";
                        break;
                }

                JLabel lblImagen = new JLabel("", SwingConstants.CENTER);
                ImageIcon icono = obtenerImagenEscalada(rutaImagen, 160, 100);

                if (icono != null) {
                    lblImagen.setIcon(icono);
                } else {
                    lblImagen.setText("Sin Imagen");
                    lblImagen.setOpaque(true);
                    lblImagen.setBackground(new Color(240, 240, 240));
                }

                lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
                lblImagen.setPreferredSize(new Dimension(160, 100));
                lblImagen.setMaximumSize(new Dimension(160, 100));

                JLabel lblNombre = new JLabel(p.getNombre());
                lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
                lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));

                JLabel lblPrecio = new JLabel(String.format("Precio: $%.2f", p.getCosto()));
                lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);
                lblPrecio.setFont(new Font("Arial", Font.BOLD, 14));

                card.add(topPanel);
                card.add(lblImagen);
                card.add(Box.createVerticalStrut(10));
                card.add(lblNombre);
                card.add(Box.createVerticalStrut(5));
                card.add(lblPrecio);
                card.add(Box.createVerticalStrut(10));

                panelProductos.add(card);
            }
        }
        panelProductos.revalidate();
        panelProductos.repaint();
    }

    public void mostrarSeleccionados() {
        panelResumen.removeAll();
        List<DetalleCompra> seleccionados = modeloVista.getProductosSeleccionados();

        if (seleccionados != null && !seleccionados.isEmpty()) {
            for (DetalleCompra detalle : seleccionados) {
                JPanel fila = new JPanel(new BorderLayout());
                fila.setBackground(Color.WHITE);
                fila.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
                fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

                String textoIzq = String.format("<html><b>%s</b><br><span style='font-size:9px; color:gray;'>Cantidad: %d</span></html>",
                        detalle.getProducto().getNombre(),
                        detalle.getCantidad());
                JLabel lblProducto = new JLabel(textoIzq);

                JPanel panelDerecho = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
                panelDerecho.setBackground(Color.WHITE);

                JLabel lblSubtotal = new JLabel(String.format("Subtotal: $%.2f", detalle.getSubtotal()));
                lblSubtotal.setFont(new Font("Arial", Font.BOLD, 12));

                JButton btnEliminar = new JButton("X");
                btnEliminar.setFont(new Font("Arial", Font.BOLD, 10));
                btnEliminar.setForeground(Color.RED);
                btnEliminar.setMargin(new Insets(2, 5, 2, 5));
                btnEliminar.addActionListener(e -> clickEliminarProducto(detalle.getProducto().getId()));

                panelDerecho.add(lblSubtotal);
                panelDerecho.add(btnEliminar);

                fila.add(lblProducto, BorderLayout.WEST);
                fila.add(panelDerecho, BorderLayout.EAST);

                panelResumen.add(fila);
            }
        } else {
            JLabel lblVacio = new JLabel("El carrito esta vacio");
            lblVacio.setForeground(Color.GRAY);
            panelResumen.add(lblVacio);
        }

        lblTotal.setText(String.format("Total acumulado: $%.2f", modeloVista.getTotal()));
        panelResumen.revalidate();
        panelResumen.repaint();
    }

    public void clickAgregarProducto(int idProducto, int cantidad) {
        controlador.seleccionarProducto(idProducto, cantidad);
    }

    public void clickEliminarProducto(int idProducto) {
        controlador.eliminarProducto(idProducto);
    }

    public void clickPagar() {
        String numTarjeta = pedirTexto("Ingresar Pago", "Por favor, ingrese su numero de tarjeta:");
        if (numTarjeta != null && !numTarjeta.trim().isEmpty()) {
            controlador.procesarCompra(numTarjeta);
        }
    }

    private ImageIcon obtenerImagenEscalada(String ruta, int ancho, int alto) {
        try {
            ImageIcon iconoOriginal = new ImageIcon(ruta);

            if (iconoOriginal.getIconWidth() > 0) {
                java.awt.Image imgEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);
                return new ImageIcon(imgEscalada);
            } else {
                System.err.println("No se encontro la imagen en: " + ruta);
            }
        } catch (Exception e) {
            System.err.println("Error al procesar la imagen: " + ruta);
        }
        return null;
    }

    private void mostrarMensaje(String titulo, String mensaje, int tipoMensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipoMensaje);
    }

    private String pedirTexto(String titulo, String mensaje) {
        return JOptionPane.showInputDialog(this, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
    }

    @Override
    public void update() {
        mostrarProductos();
        mostrarSeleccionados();

        boolean tieneProductos = !modeloVista.getProductosSeleccionados().isEmpty();
        btnPagar.setEnabled(tieneProductos);

    }

    @Override
    public void notificarError(String mensaje) {
        mostrarMensaje("Error", mensaje, JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void notificarExito(String titulo, String mensaje) {
        mostrarMensaje(titulo, mensaje, JOptionPane.INFORMATION_MESSAGE);
    }

}
