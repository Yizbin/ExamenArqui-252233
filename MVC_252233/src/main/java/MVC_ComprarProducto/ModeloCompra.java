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
    private final List<ISuscriptor> listaSuscriptores;
    private final List<ISuscriptorPago> listaSuscriptoresPago;

    public ModeloCompra() {
        this.productosDisponibles = new ArrayList<>();
        this.productosSeleccionados = new ArrayList<>();
        this.listaSuscriptores = new ArrayList<>();
        this.listaSuscriptoresPago = new ArrayList<>();
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
    }

    public void agregarSuscriptor(ISuscriptor suscriptor) {
        listaSuscriptores.add(suscriptor);
    }

    public void agregarSuscriptorPago(ISuscriptorPago suscriptorPago) {
        listaSuscriptoresPago.add(suscriptorPago);
    }

    private void notificarSuscriptores() {
        for (ISuscriptor suscriptor : listaSuscriptores) {
            suscriptor.update();
        }
    }

    @Override
    public void agregarProducto(int idProducto, int cantidad) throws Exception {
        Producto productoEncontrado = buscarProductoPorId(idProducto);

        DetalleCompra nuevoDetalle = new DetalleCompra(productoEncontrado, cantidad);
        nuevoDetalle.validar();

        productosSeleccionados.add(nuevoDetalle);
        calcularTotal();

        notificarSuscriptores();
    }

    @Override
    public void eliminarProducto(int idProducto) throws Exception {
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

            notificarSuscriptores();
        } else {
            throw new Exception("El producto no se encontro en el carrito.");
        }
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
    public void procesarCompraCompleta(String numeroTarjeta) throws Exception {
        if (productosSeleccionados.isEmpty()) {
            throw new Exception("El carrito esta vacio. Agrega productos para pagar.");
        }

        String numLimpio = numeroTarjeta.replace("-", "").replace(" ", "");

        Tarjeta nuevaTarjeta = new Tarjeta(numLimpio, "Banamex", "Ciudad Obregon");
        nuevaTarjeta.validar();

        java.util.List<String> tarjetasValidas = java.util.Arrays.asList("1234567890123456", "4152313456789012");
        if (!tarjetasValidas.contains(numLimpio)) {
            throw new Exception("Tarjeta declinada o inexistente en el sistema.");
        }

        this.tarjetaActual = nuevaTarjeta;

        for (ISuscriptorPago suscriptor : listaSuscriptoresPago) {
            suscriptor.compraExitosa();
        }

        this.productosSeleccionados.clear();
        this.total = 0.0;

        notificarSuscriptores();
    }
    
    @Override
    public void nofiticarError(String mensaje) {
       for (ISuscriptor suscriptor : listaSuscriptores) {
            suscriptor.notificarError(mensaje);
        }
    }

    @Override
    public void notificarExito(String titulo, String mensaje) {
        for (ISuscriptor suscriptor : listaSuscriptores) {
            suscriptor.notificarExito(titulo, mensaje);
        }
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
