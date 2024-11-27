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
import modelo.Libro;
import vista.agregarLibro;

/**
 *
 * @author Paula
 */
public class agregarLibroController implements ActionListener{
    private agregarLibro vista;
    private Libro libroModelo;

    public agregarLibroController(agregarLibro vista,Libro libroModelo) {
        this.vista = vista;
        this.libroModelo = libroModelo;

        // añadir los eventos a los botones
        
        System.out.println("Botón agregar");
        this.vista.getBtn_agregarLibro().addActionListener(this);
        this.vista.getrBtn_presado().addActionListener(this);
        this.vista.getrBtn_disponible().addActionListener(this);
        this.vista.getrBtn_disponible().addActionListener(this);
        //no hace falta añadir el set visible aqui porque loa ñadimos en librosViewController
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.getBtn_agregarLibro()) {
            if (comprobarCampos()) {
                agregarLibro();
                JOptionPane.showMessageDialog(this.vista, "Libro registrado con éxito", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                JOptionPane.showMessageDialog(this.vista, "No se pudo registrar el libro", "Registro erróneo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void inicializarAL() {
        this.vista.getBtn_agregarLibro().addActionListener(this);
    }

    public Boolean comprobarCampos() {
        Boolean valido = true;

        // Limpiar errores previos
        this.vista.getLbl_titulo().setForeground(Color.BLACK);
        this.vista.getLbl_autor().setForeground(Color.BLACK);
        this.vista.getLbl_isbn().setForeground(Color.BLACK);
        this.vista.getLbl_editorial().setForeground(Color.BLACK);
        this.vista.getLbl_precio().setForeground(Color.BLACK);
        
        // Comprobar campos vacíos
        if (this.vista.getTxt_titulo().getText().isEmpty()) {
            valido = false;
            this.vista.getLbl_titulo().setForeground(Color.red);
        }
        if (this.vista.getTxt_autor().getText().isEmpty()) {
            valido = false;
            this.vista.getLbl_autor().setForeground(Color.red);
        }
        if (this.vista.getTxt_isbn().getText().isEmpty()) {
            valido = false;
            this.vista.getLbl_isbn().setForeground(Color.red);
        }
        if (this.vista.getTxt_editorial().getText().isEmpty()) {
            valido = false;
            this.vista.getLbl_editorial().setForeground(Color.red);
        }
        if (this.vista.getTxt_precio().getText().isEmpty()) {
            valido = false;
            this.vista.getLbl_precio().setForeground(Color.red);
        }
        
        return valido;
    }

    public void agregarLibro() {
        String titulo = this.vista.getTxt_titulo().getText();
        String autor = this.vista.getTxt_autor().getText();
        String isbn = this.vista.getTxt_isbn().getText();
        String editorial = this.vista.getTxt_editorial().getText();
        String precioString = this.vista.getTxt_precio().getText();
        
        // Validar y convertir el precio de String a float
    float precio = 0;
    try {
        precio = Float.parseFloat(precioString);
    } catch (NumberFormatException e) {
        System.out.println("Error al convertir el precio: " + precioString);
        // Aquí podrías poner un valor por defecto o mostrar un mensaje de error
    }
    
        int bibliotecaId = ControllerUtils.idbibliotecaPaula(this.vista.getcBox_ubicacion().getSelectedItem().toString()); 

        Libro nuevoLibro = new Libro(0, isbn, titulo, autor, editorial, precio, bibliotecaId, true);
        
        Libro.registrarLibro(nuevoLibro); 
    }
}
