/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MVC_ComprarProducto;

import Mock.DetalleCompra;
import Mock.Producto;
import Mock.Tarjeta;
import java.util.List;

/**
 *
 * @author Abraham Coronel
 */
public interface IModeloVista {

    public List<Producto> getProductosDisponibles();

    public List<DetalleCompra> getProductosSeleccionados();

    public double getTotal();

    public Tarjeta getDatosTarjeta();

    public String getMensajeEstado();
}
