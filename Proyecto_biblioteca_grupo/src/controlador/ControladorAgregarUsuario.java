/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.BaseDatos;
import modelo.Socio;
import vista.AgregarSocio;

/**
 *
 * @author Laura Sánchez
 */
public class ControladorAgregarUsuario implements ActionListener{
    private AgregarSocio vista;
    private Socio modelo;
    private ControladorMostrarSocio vistaOriginal;
    
    
    // Contructores
    //--------------------------------------------------------------------------
    public ControladorAgregarUsuario(){
        this.vista = new AgregarSocio();
        this.modelo = new Socio();
        inicializarAL();
        this.vista.setVisible(true);
    }
    
    public ControladorAgregarUsuario(AgregarSocio vista, Socio modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    
    // Getters & Setters
    //--------------------------------------------------------------------------
    public AgregarSocio getVista() {
        return vista;
    }

    public void setVista(AgregarSocio vista) {
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
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.vista.getBtn_registrar()){
            if(comprobarCampos()){
                agregarCliente();
            }  
            else JOptionPane.showMessageDialog(this.vista, "Faltan campos por rellenar", "Registro erróneo",JOptionPane.ERROR_MESSAGE);
                
        }
    }
    
    public void inicializarAL(){
        this.vista.getBtn_registrar().addActionListener(this);
    }  
    
    
    
    public Boolean comprobarCampos(){
       Boolean valido = true;
       String error = "";
       
       this.vista.getLbl_dni().setForeground(Color.BLACK);
       this.vista.getLbl_nombre().setForeground(Color.BLACK);
       this.vista.getLbl_apellidos().setForeground(Color.BLACK);
       this.vista.getLbl_telefono().setForeground(Color.BLACK);
       this.vista.getLbl_email().setForeground(Color.BLACK);
       this.vista.getLbl_ciudad().setForeground(Color.BLACK);
       this.vista.getLbl_calle().setForeground(Color.BLACK);
       this.vista.getLbl_cuentaBancaria().setForeground(Color.BLACK);
       
       if(this.vista.getTxt_dni().getText().isEmpty()){
          valido = false;
           error.concat("Inserte un DNI\n");
           this.vista.getLbl_dni().setForeground(Color.red);
       }
       if(this.vista.getTxt_nombre().getText().isEmpty()){
           valido = false;
           error.concat("Inserte un nombre\n");
           this.vista.getLbl_nombre().setForeground(Color.red);
       }
       if(this.vista.getTxt_apellidos().getText().isEmpty()){
           valido = false;
           error.concat("Inserte apellidos\n");
           this.vista.getLbl_apellidos().setForeground(Color.red);
       }
       if(this.vista.getTxt_telefono().getText().isEmpty()){
           valido = false;
           error.concat("Inserte un teléfono\n");
           this.vista.getLbl_telefono().setForeground(Color.red);
       }
       if(this.vista.getTxt_email().getText().isEmpty()){
           valido = false;
           error.concat("Inserte un email\n");
           this.vista.getLbl_email().setForeground(Color.red);
       }
       if(this.vista.getTxt_ciudad().getText().isEmpty()){
           valido = false;
           error.concat("Inserte una ciudad\n");
           this.vista.getLbl_ciudad().setForeground(Color.red);
       }
       if(this.vista.getTxt_calle().getText().isEmpty()){
           valido = false;
           error.concat("Inserte una calle\n");
           this.vista.getLbl_calle().setForeground(Color.red);
       }
       if(this.vista.getTxt_cuentaBancaria().getText().isEmpty()){
           valido = false;
           error.concat("Inserte una cuenta bancaria\n");
           this.vista.getLbl_cuentaBancaria().setForeground(Color.red);
       }
       return valido; 
    }
    
    public void agregarCliente(){
        String dni = this.vista.getTxt_dni().getText();
        String nombre = this.vista.getTxt_nombre().getText();
        String apellidos = this.vista.getTxt_apellidos().getText();
        String telefono = this.vista.getTxt_telefono().getText();
        String email = this.vista.getTxt_email().getText();
        String cBancaria = this.vista.getTxt_cuentaBancaria().getText();
        String ciudad = this.vista.getTxt_ciudad().getText();
        String calle = this.vista.getTxt_calle().getText();
        
        int biblioteca = ControllerUtils.idbiblioteca(this.vista.getcBox_biblioteca().getSelectedItem().toString());
        System.out.println(biblioteca);
        Boolean pago = this.vista.getCheck_pago().isSelected();
        String provincia = this.vista.getcBox_provincia().getSelectedItem().toString();
        
        Socio nuevoSocio = new Socio(String.valueOf(biblioteca), dni, nombre, apellidos, telefono, email, pago, provincia, 0, cBancaria);
        Socio.registrarSocio(nuevoSocio);
        if(Socio.obtenerSocioPorDNI(nuevoSocio.getDni()) != null){
            JOptionPane.showMessageDialog(this.vista, "Usuario agregado con éxito", "Agregado correctamente", JOptionPane.INFORMATION_MESSAGE);
            this.vista.dispose(); 
        }else JOptionPane.showMessageDialog(this.vista, "No se pudo agregar al usuario", "Error al agregar", JOptionPane.ERROR_MESSAGE);
    }
}