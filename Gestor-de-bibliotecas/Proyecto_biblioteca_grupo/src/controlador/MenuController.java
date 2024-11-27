/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Libro;
import vista.MenuView;
import vista.librosView;

/**
 *
 * @author Paula
 */
public class MenuController implements ActionListener{
    private MenuView menuView;
    private librosView librosView;
    
      public MenuController(MenuView menuView) {
        this.menuView=menuView;
        System.out.println("MenuController iniciado"); 

        // Registrar botones
        this.menuView.getBtn_socios().addActionListener(this);
        this.menuView.getBtn_prestamos().addActionListener(this);
        this.menuView.getBtn_consultas().addActionListener(this);
        this.menuView.getBtn_libros().addActionListener(this);
        this.menuView.getBtn_sanciones().addActionListener(this);
    
}

    @Override
    public void actionPerformed(ActionEvent e) {
    // Verificar si el botón presionado es el de "Libros"
        if (e.getSource() == menuView.getBtn_libros()) {
            System.out.println("Botón 'Libros' presionado");

            // Crear instancia del modelo Libro
            Libro libroModelo = new Libro();

            librosView  librosView= new librosView(); // Crear instancia de la vista libros
            System.out.println("Abriendo vista de libros..."); 
            LibrosViewController controller = new LibrosViewController(librosView, libroModelo); // Crear el controlador de la vista de libros
            librosView.setVisible(true);
        } 
        else if (e.getSource() == menuView.getBtn_socios()) {
            System.out.println("Botón socio presionado");
            new ControladorMostrarsocio();
        } else if (e.getSource() == menuView.getBtn_prestamos()) {
            new ControladorMostrarPrestamos();
            System.out.println("Botón Préstamos presionado");
        } else if (e.getSource() == menuView.getBtn_sanciones()) {
            System.out.println("Botón sanciones presionado");
        }
    }
}


