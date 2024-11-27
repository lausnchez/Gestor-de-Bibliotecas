/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Biblioteca;
import modelo.Prestamo;
import modelo.Socio;
import vista.AgregarPrestamo;

/**
 *
 * @author Laura Sánchez
 */
public class ControladorAgregarPrestamos implements ActionListener{
    private AgregarPrestamo vista;
    private Prestamo modelo;
    
    public ControladorAgregarPrestamos(){
        this.vista = new AgregarPrestamo();
        this.modelo = new Prestamo();
        inicializarAL();
        llenarCBoxBibliotecas();
        this.vista.setVisible(true);
    }
    
    
    // Métodos
    //--------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.vista.getBtn_agregar()){
            System.out.println("Agregar");
        }
        if(e.getSource() == this.vista.getcBox_bibliotecas()){
            this.vista.getcBox_socios().removeAllItems();
            
            List<Socio> socioPorBiblio = Socio.obtenerSocioPorBibliotecas(this.vista.getcBox_bibliotecas().getSelectedIndex());
            List<String> socios = Socio.obtenerNombresSociosDesdeLista(socioPorBiblio);
        }
        
    }
    
    public void inicializarAL(){
        this.vista.getBtn_agregar().addActionListener(this);
        this.vista.getcBox_bibliotecas().addActionListener(this);
        this.vista.getcBox_socios().addActionListener(this);
    }

    
    public void llenarCBoxBibliotecas(){
        this.vista.getcBox_bibliotecas().removeAllItems();
        List<String> bibliotecas = Biblioteca.recogerNombreBibliotecas();
        for(String valor: bibliotecas){
            this.vista.getcBox_bibliotecas().addItem(valor);
        }
    }
    
}
