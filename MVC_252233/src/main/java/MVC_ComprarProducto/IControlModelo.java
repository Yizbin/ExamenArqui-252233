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

    public void agregarProducto(int idProducto, int cantidad) throws Exception;

    public void procesarCompraCompleta(String numeroTarjeta) throws Exception;

    public void eliminarProducto(int idProducto) throws Exception;
    
    public void nofiticarError(String mensaje);
    public void notificarExito(String titulo, String mensaje);
}
