/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package MVC_ComprarProducto;

/**
 *
 * @author Abraham Coronel
 */
public interface ISuscriptor {

    public void update();
    
    public void notificarError(String mensaje);
    public void notificarExito(String titulo, String mensaje);
}
