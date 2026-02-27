/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MVC_ComprarProducto;

import Entidades.DetalleCompra;
import Entidades.Producto;
import Entidades.Tarjeta;
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

}
