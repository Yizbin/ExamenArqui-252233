/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC_ComprarProducto;

import Mock.DetalleCompra;
import Mock.GeneradorMock;
import Mock.Producto;
import Mock.Tarjeta;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void agregarProducto(int idProducto, int cantidad) throws Exception {
        Producto productoEncontrado = null;
        for (Producto p : productosDisponibles) {
            if (p.getId() == idProducto) {
                productoEncontrado = p;
                break;
            }
        }

        if (productoEncontrado == null) {
            throw new Exception("El producto seleccionado no existe en el catálogo.");
        }

        DetalleCompra nuevoDetalle = new DetalleCompra(productoEncontrado, cantidad);
        nuevoDetalle.validar();

        productosSeleccionados.add(nuevoDetalle);
        calcularTotal();
        this.mensajeEstado = "Producto agregado correctamente.";
        notificarSuscriptores();
    }

    @Override
    public void registrarTarjeta(String numero) throws Exception {
        String numeroLimpio = numero.replace("-", "").replace(" ", "");

        List<String> tarjetasValidas = Arrays.asList(
                "1234567890123456",
                "4152313456789012"
        );

        if (!tarjetasValidas.contains(numeroLimpio)) {
            throw new Exception("La tarjeta ingresada no existe o fue declinada por el banco.");
        }

        Tarjeta nuevaTarjeta = new Tarjeta(numeroLimpio, "Banamex", "Ciudad Obregon");

        nuevaTarjeta.validar();

        this.tarjetaActual = nuevaTarjeta;
        this.mensajeEstado = "Tarjeta registrada y verificada correctamente.";
        notificarSuscriptores();
    }

    @Override
    public void procesarPago() throws Exception {
        if (productosSeleccionados.isEmpty()) {
            throw new Exception("El carrito esta vacio. Agrega productos para comprar.");
        }
        if (tarjetaActual == null) {
            throw new Exception("Debes registrar una tarjeta valida antes de pagar.");
        }
        tarjetaActual.validar();

        this.mensajeEstado = "Pago procesado con exito por un total de $" + this.total;
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

}
