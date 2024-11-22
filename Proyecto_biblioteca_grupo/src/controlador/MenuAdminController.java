/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Bibliotecario;
import modelo.Libro;
import vista.MenuAdminView;
import vista.bibliotecariosView;
import vista.librosView;

/**
 *
 * @author Paula
 */
public class MenuAdminController implements ActionListener{
    private MenuAdminView menuAdminView;
    private librosView librosView;
    
      public MenuAdminController(MenuAdminView menuAdminView) {
        this.menuAdminView=menuAdminView;
        this.librosView=librosView;

        // Registrar botones
        this.menuAdminView.getBtn_biblioteca().addActionListener(this);
        this.menuAdminView.getBtn_prestamos().addActionListener(this);
        this.menuAdminView.getBtn_consultas().addActionListener(this);
        this.menuAdminView.getBtn_libros().addActionListener(this);
        this.menuAdminView.getBtn_socios().addActionListener(this);
        this.menuAdminView.getBtn_sanciones().addActionListener(this);
        this.menuAdminView.getBtn_bibliotecario().addActionListener(this);
    
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuAdminView.getBtn_biblioteca()) {
            System.out.println("Botón Biblioteca presionado");
            new ControladorMostrarBibliotecas();
        } else if (e.getSource() == menuAdminView.getBtn_prestamos()) {
            System.out.println("Botón Préstamos presionado");
        }else if (e.getSource() == menuAdminView.getBtn_bibliotecario()) {
           //crear instancia del modelo libro
           System.out.println("Botón bibliotecario presionado");
            Bibliotecario bibliotecarioModelo=new Bibliotecario();
            bibliotecariosView bibliotecariosView = new bibliotecariosView();
            BibliotecariosController controller = new BibliotecariosController(bibliotecariosView, bibliotecarioModelo); // Pasa la vista y modelo necesarios
            librosView.setVisible(true);
        } else if (e.getSource() == menuAdminView.getBtn_libros()) {
            System.out.println("Botón libros presionado");
            //crear instancia del modelo libro
            Libro libroModelo = new Libro();
            librosView librosView = new librosView();
             LibrosViewController controller = new LibrosViewController(librosView, libroModelo); // Pasa la vista y modelo necesarios
            librosView.setVisible(true);
        } else if (e.getSource() == menuAdminView.getBtn_sanciones()) {
            System.out.println("Botón sanciones presionado");
        }
    }
}
