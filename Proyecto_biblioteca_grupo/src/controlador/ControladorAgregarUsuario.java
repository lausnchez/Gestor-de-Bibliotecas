/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.BaseDatos;
import modelo.Biblioteca;
import modelo.Socio;
import vista.AgregarSocio;

/**
 *
 * @author Laura Sánchez
 */
public class ControladorAgregarUsuario implements ActionListener{
    private AgregarSocio vista;
    private Socio modelo;
    private Socio actualizarSocio = null;
    
    
    // Contructores
    //--------------------------------------------------------------------------
    // Agregar
    public ControladorAgregarUsuario(){
        this.vista = new AgregarSocio();
        this.modelo = new Socio();
        inicializarAL();
        
        this.vista.setVisible(true);
    }
    
    // Actualizar
    public ControladorAgregarUsuario(Socio actualizarSocio){
        this.vista = new AgregarSocio();
        this.modelo = new Socio();
        inicializarAL();
        // Ediciones de la vista
        this.vista.getBtn_registrar().setText("Actualizar");
        this.vista.getTxt_dni().setEnabled(false);
        this.vista.getTxt_dni().setText(actualizarSocio.getDni());
        this.vista.getTxt_nombre().setEnabled(false);
        this.vista.getTxt_nombre().setText(actualizarSocio.getNombre());
        this.vista.getTxt_apellidos().setEnabled(false);
        this.vista.getTxt_apellidos().setText(actualizarSocio.getApellidos());
        this.vista.getTxt_cuentaBancaria().setText(actualizarSocio.getCuentaBancaria());
        this.vista.getTxt_email().setText(actualizarSocio.getEmail());
        this.vista.getTxt_telefono().setText(actualizarSocio.getTelefono());
        this.vista.getCheck_pago().setSelected(actualizarSocio.isPago());
        this.vista.getcBox_biblioteca().setSelectedItem(actualizarSocio.getBiblioteca());
        this.vista.getcBox_provincia().setSelectedItem(actualizarSocio.getProvincia());
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
        if(e.getSource() == this.vista.getcBox_provincia()){
            this.vista.getcBox_biblioteca().removeAllItems();
            List<String> bibliotecas = Biblioteca.recogerBibliotecasPorProvincia(this.vista.getcBox_provincia().getSelectedItem().toString());
            for(String valor: bibliotecas){
                this.vista.getcBox_biblioteca().addItem(valor);
            }
        }
    }
    
    public void inicializarAL(){
        this.vista.getBtn_registrar().addActionListener(this);
        this.vista.getcBox_provincia().addActionListener(this);
    }  
    
    
    
    public Boolean comprobarCampos(){
       Boolean valido = true;
       String error = "";
       
       this.vista.getLbl_dni().setForeground(Color.BLACK);
       this.vista.getLbl_nombre().setForeground(Color.BLACK);
       this.vista.getLbl_apellidos().setForeground(Color.BLACK);
       this.vista.getLbl_telefono().setForeground(Color.BLACK);
       this.vista.getLbl_email().setForeground(Color.BLACK);
       this.vista.getLbl_cuentaBancaria().setForeground(Color.BLACK);
       this.vista.getLbl_biblioteca().setForeground(Color.BLACK);
       this.vista.getLbl_provincia().setForeground(Color.BLACK);
       
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
       if(this.vista.getTxt_cuentaBancaria().getText().isEmpty()){
           valido = false;
           error.concat("Inserte una cuenta bancaria\n");
           this.vista.getLbl_cuentaBancaria().setForeground(Color.red);
       }
       if(this.vista.getcBox_provincia().getSelectedIndex() == 0){
           valido = false;
           error.concat("Seleccione una provincia\n");
           this.vista.getLbl_provincia().setForeground(Color.red);
       }
       if(this.vista.getcBox_biblioteca().getSelectedItem().equals(" ")){
           valido = false;
           error.concat("Seleccione una biblioteca\n");
           this.vista.getLbl_biblioteca().setForeground(Color.red);
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