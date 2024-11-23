/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Biblioteca;
import modelo.Socio;
import vista.AgregarBibliotecas;

/**
 *
 * @author laura.sanchez
 */
public class ControladorAgregarBibliotecas implements ActionListener{
    private AgregarBibliotecas vista;
    private Biblioteca modelo;
    private Biblioteca actualizarBiblio = null;
    
    // Contructores
    //--------------------------------------------------------------------------
    // Agregar
    public ControladorAgregarBibliotecas(){
        this.vista = new AgregarBibliotecas();
        this.modelo = new Biblioteca();
        inicializarAL();
        this.vista.setVisible(true);
    }
    
    // Actualizar
    public ControladorAgregarBibliotecas(Biblioteca actualizarBiblio){
        this.vista = new AgregarBibliotecas();
        this.actualizarBiblio = actualizarBiblio;
        this.modelo = new Biblioteca();
        inicializarAL();
        // Ediciones de la vista
        this.vista.getBtn_agregar().setText("Actualizar");
        this.vista.getTxt_nombre().setEnabled(false);
        this.vista.getTxt_calle().setEnabled(false);
        this.vista.getTxt_ciudad().setEnabled(false);
        this.vista.getcBox_provincias().setEnabled(false);
        
        this.vista.getTxt_nombre().setText(actualizarBiblio.getNombre());
        this.vista.getTxt_ciudad().setText(actualizarBiblio.getCiudad());
        this.vista.getTxt_calle().setText(actualizarBiblio.getCalle());
        this.vista.getTxt_telefono().setText(actualizarBiblio.getTelefono());
        this.vista.getTxt_email().setText(actualizarBiblio.getEmail());
        this.vista.getcBox_provincias().setSelectedItem(actualizarBiblio.getProvincia());
                
        this.vista.setVisible(true);
    }

    // Métodos
    // -------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.actualizarBiblio == null){
            // Agregar
            if(e.getSource() == this.vista.getBtn_agregar()){
                if(comprobarCampos()){
                    //agregarCliente();
                }  
                else JOptionPane.showMessageDialog(this.vista, "Faltan campos por rellenar", "Registro erróneo",JOptionPane.ERROR_MESSAGE);
                }
        }else{
            // Actualizar
            if(e.getSource() == this.vista.getBtn_agregar()){
                    if(comprobarCampos()){
                       actualizarBiblio.setTelefono(this.vista.getTxt_telefono().getText());
                       actualizarBiblio.setEmail(this.vista.getTxt_email().getText());
                       int id = actualizarBiblio.getIdBiblioteca();
                       String nombre = actualizarBiblio.getNombre();
                       String telefono = actualizarBiblio.getTelefono();
                       String email = actualizarBiblio.getEmail();
                       Biblioteca.actualizarBiblioteca(id, nombre, telefono, email);
                       this.vista.dispose();
                    }  
                    else JOptionPane.showMessageDialog(this.vista, "Faltan campos por rellenar", "Actualización errónea",JOptionPane.ERROR_MESSAGE);
            }    
        }
    }
    
    public void inicializarAL(){
        this.vista.getBtn_agregar().addActionListener(this);
    }
    
    public Boolean comprobarCampos(){
       Boolean valido = true;
       
       this.vista.getLbl_nombre().setForeground(Color.BLACK);
       this.vista.getLbl_ciudad().setForeground(Color.BLACK);
       this.vista.getLbl_calle().setForeground(Color.BLACK);
       this.vista.getLbl_telefono().setForeground(Color.BLACK);
       this.vista.getLbl_email().setForeground(Color.BLACK);
       
       if(this.vista.getTxt_nombre().getText().isEmpty()){
          valido = false;
           this.vista.getLbl_nombre().setForeground(Color.red);
       }
       if(this.vista.getTxt_ciudad().getText().isEmpty()){
          valido = false;
           this.vista.getLbl_ciudad().setForeground(Color.red);
       }
       if(this.vista.getTxt_calle().getText().isEmpty()){
          valido = false;
           this.vista.getLbl_calle().setForeground(Color.red);
       }
       if(this.vista.getTxt_email().getText().isEmpty()){
          valido = false;
           this.vista.getLbl_email().setForeground(Color.red);
       }
       if(this.vista.getTxt_telefono().getText().isEmpty()){
          valido = false;
           this.vista.getLbl_telefono().setForeground(Color.red);
       }
       if(this.vista.getcBox_provincias().getSelectedItem() == null){
           valido = false;
           this.vista.getLbl_provincia().setForeground(Color.red);
       }
       return valido; 
    }

}
