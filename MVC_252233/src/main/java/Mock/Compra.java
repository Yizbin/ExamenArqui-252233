/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mock;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abraham Coronel
 */
public class Compra {

    private final List<DetalleCompra> detalles;
    private Tarjeta tarjeta;
    private double total;
    private String estado;

    public Compra() {
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(DetalleCompra detalle) throws Exception {
        if (detalle == null) {
            throw new Exception("No se puede agregar un detalle vacío a la compra.");
        }
        detalle.validar();
        this.detalles.add(detalle);
    }

    public void calcularTotal() {
        this.total = 0;
        for (DetalleCompra detalle : detalles) {
            this.total += detalle.getSubtotal();
        }
    }

    public void confirmar() throws Exception {
        if (detalles.isEmpty()) {
            throw new Exception("El carrito está vacío. Agrega productos antes de pagar.");
        }
        this.estado = "Confirmada";
    }

    public double getTotal() {
        return total;
    }
}
