/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC_ComprarProducto;

import Entidades.DetalleCompra;
import Entidades.GeneradorMock;
import Entidades.Producto;
import Entidades.Tarjeta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abraham Coronel
 */
public class ModeloCompra implements IControlModelo, IModeloVista {

    private List<Producto> productosDisponibles;
    private final List<DetalleCompra> productosSeleccionados;
    private Tarjeta tarjetaActual;
    private double total;
    private String mensajeEstado;
    private String errorEstado;
    private final List<ISuscriptor> listaSuscriptores;

    public ModeloCompra() {
        this.productosDisponibles = new ArrayList<>();
        this.productosSeleccionados = new ArrayList<>();
        this.listaSuscriptores = new ArrayList<>();
        this.total = 0.0;
        obtenerProductos();
    }

    public final void obtenerProductos() {
        this.productosDisponibles = GeneradorMock.obtenerProductosDisponibles();
    }

    public void calcularTotal() {
        this.total = 0;
        for (DetalleCompra detalle : productosSeleccionados) {
            this.total += detalle.getSubtotal();
        }
        notificarSuscriptores();
    }

    public void agregarSuscriptor(ISuscriptor suscriptor) {
        listaSuscriptores.add(suscriptor);
    }

    private void notificarSuscriptores() {
        for (ISuscriptor suscriptor : listaSuscriptores) {
            suscriptor.update();
        }
    }

    @Override
    public void agregarProducto(int idProducto, int cantidad) {
        try {
            Producto productoEncontrado = buscarProductoPorId(idProducto);
            if (productoEncontrado == null) {
                throw new Exception("Producto no encontrado.");
            }

            DetalleCompra nuevoDetalle = new DetalleCompra(productoEncontrado, cantidad);
            nuevoDetalle.validar();

            productosSeleccionados.add(nuevoDetalle);
            calcularTotal();

            this.errorEstado = null;
            this.mensajeEstado = "Producto agregado: " + productoEncontrado.getNombre();

        } catch (Exception e) {
            this.errorEstado = e.getMessage();
            this.mensajeEstado = null;
        }

        notificarSuscriptores();
    }

    @Override
    public void registrarTarjeta(String numero) {
        try {
            String numLimpio = numero.replace("-", "").replace(" ", "");

            Tarjeta nuevaTarjeta = new Tarjeta(numLimpio, "Banamex", "Ciudad Obregón");
            nuevaTarjeta.validar();

            java.util.List<String> tarjetasValidas = java.util.Arrays.asList("1234567890123456", "4152313456789012");
            if (!tarjetasValidas.contains(numLimpio)) {
                throw new Exception("Tarjeta declinada o inexistente en el sistema.");
            }

            this.tarjetaActual = nuevaTarjeta;
            this.errorEstado = null;
            this.mensajeEstado = "Tarjeta registrada correctamente.";

        } catch (Exception e) {
            this.tarjetaActual = null;
            this.errorEstado = e.getMessage();
            this.mensajeEstado = null;
        }

        notificarSuscriptores();
    }

    @Override
    public void procesarPago() {
        if (productosSeleccionados.isEmpty()) {
            this.errorEstado = "El carrito está vacío. Agrega productos para pagar.";
            this.mensajeEstado = null;
        } else if (tarjetaActual == null) {
            this.errorEstado = "Debes registrar una tarjeta válida antes de pagar.";
            this.mensajeEstado = null;
        } else {
            this.mensajeEstado = "¡Pago procesado con éxito por un total de $" + this.total + "!";
            this.errorEstado = null;
        }

        notificarSuscriptores();
    }

    @Override
    public void eliminarProducto(int idProducto) {
        try {
            DetalleCompra detalleAEliminar = null;
            for (DetalleCompra detalle : productosSeleccionados) {
                if (detalle.getProducto().getId() == idProducto) {
                    detalleAEliminar = detalle;
                    break;
                }
            }

            if (detalleAEliminar != null) {
                productosSeleccionados.remove(detalleAEliminar);
                calcularTotal();

                this.errorEstado = null;
                this.mensajeEstado = "Producto eliminado del carrito.";
            } else {
                throw new Exception("El producto no se encontro en el carrito.");
            }

        } catch (Exception e) {
            this.errorEstado = e.getMessage();
            this.mensajeEstado = null;
        }

        notificarSuscriptores();
    }

    @Override
    public List<Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

    @Override
    public List<DetalleCompra> getProductosSeleccionados() {
        return productosSeleccionados;
    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public Tarjeta getDatosTarjeta() {
        return tarjetaActual;
    }

    @Override
    public String getMensajeEstado() {
        return mensajeEstado;
    }

    @Override
    public String getErrorEstado() {
        return errorEstado;
    }

    @Override
    public void limpiarMensajes() {
        this.errorEstado = null;
        this.mensajeEstado = null;
    }

    private Producto buscarProductoPorId(int idProducto) throws Exception {
        for (Producto p : productosDisponibles) {
            if (p.getId() == idProducto) {
                return p;
            }
        }
        throw new Exception("El producto seleccionado no existe en el catálogo.");
    }

}
