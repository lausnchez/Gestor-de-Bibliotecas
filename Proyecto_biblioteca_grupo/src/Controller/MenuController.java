/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
        this.librosView=librosView;

        // Registrar botones
        this.menuView.getBtn_socios().addActionListener(this);
        this.menuView.getBtn_prestamos().addActionListener(this);
        this.menuView.getBtn_consultas().addActionListener(this);
        this.menuView.getBtn_libros().addActionListener(this);
        this.menuView.getBtn_socios().addActionListener(this);
        this.menuView.getBtn_sanciones().addActionListener(this);
    
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuView.getBtn_socios()) {
            System.out.println("Botón Biblioteca presionado");
        } else if (e.getSource() == menuView.getBtn_prestamos()) {
            System.out.println("Botón Préstamos presionado");
        } else if (e.getSource() == menuView.getBtn_libros()) {
            System.out.println("Botón libros presionado");
            //crear instancia del modelo libro
            Libro libroModelo = new Libro();
            librosView librosView = new librosView();
             LibrosViewController controller = new LibrosViewController(librosView, libroModelo); // Pasa la vista y modelo necesarios
            librosView.setVisible(true);
        } else if (e.getSource() == menuView.getBtn_sanciones()) {
            System.out.println("Botón sanciones presionado");
        }
    }
}


