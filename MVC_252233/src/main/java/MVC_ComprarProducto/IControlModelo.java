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

    public void registrarTarjeta(String numero) throws Exception;

    public void procesarPago() throws Exception;
}
