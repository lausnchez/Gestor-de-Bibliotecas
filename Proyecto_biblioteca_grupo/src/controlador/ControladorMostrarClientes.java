/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Socio;
import vista.MostrarClientes;

/**
 *
 * @author Laura Sánchez
 */
public class ControladorMostrarClientes {
    private MostrarClientes vista;
    private Socio modelo;

    // Constructores
    //--------------------------------------------------------------------------
    public ControladorMostrarClientes() {
        this.vista = new MostrarClientes();
        this.modelo = modelo;
    }
    
    // Getters & Setters
    //--------------------------------------------------------------------------
    public MostrarClientes getVista() {
        return vista;
    }

    public void setVista(MostrarClientes vista) {
        this.vista = vista;
    }

    public Socio getModelo() {
        return modelo;
    }

    public void setModelo(Socio modelo) {
        this.modelo = modelo;
    }
    
    // Métodos
    //--------------------------------------------------------------------------
    
    
}
