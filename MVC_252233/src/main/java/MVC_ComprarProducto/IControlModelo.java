/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MVC_ComprarProducto;

/**
 *
 * @author Abraham Coronel
 */
public interface IControlModelo {

    public void agregarProducto(int idProducto, int cantidad);

    public void registrarTarjeta(String numero);

    public void procesarPago();
    
    public void eliminarProducto(int idProducto);
}
