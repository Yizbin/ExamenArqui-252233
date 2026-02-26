/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Abraham Coronel
 */
public class Tarjeta {

    private final String numero;
    private final String bancoEmisor;
    private final String ciudad;

    public Tarjeta(String numero, String bancoEmisor, String ciudad) {
        this.numero = numero;
        this.bancoEmisor = bancoEmisor;
        this.ciudad = ciudad;
    }

    public boolean validar() throws Exception {
        if (numero == null || numero.trim().isEmpty() || numero.length() < 16) {
            throw new Exception("El numero de tarjeta es invalido. Debe contener al menos 16 digitos.");
        }
        return true;
    }

    public String getBanco() {
        return bancoEmisor;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getNumero() {
        return numero;
    }

    public String getBancoEmisor() {
        return bancoEmisor;
    }
    
    
}
