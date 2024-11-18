/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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

    public agregarLibroController(Libro libroModelo) {
        this.vista = vista;
        this.libroModelo = libroModelo;

        // añadir los eventos a los botones
        
        System.out.println("Botón agregar");
        this.vista.getBtn_agregarLibro().addActionListener(this);
        this.vista.getrBtn_presado().addActionListener(this);
        this.vista.getrBtn_disponible().addActionListener(this);
        //no hace falta añadir el set visible aqui porque loa ñadimos en librosViewController
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.getBtn_agregarLibro()) {
            System.out.println("Has pulsado el botón de agregar");
            try {
                String isbn = vista.getTxt_isbn().getText();
                String titulo = vista.getTxt_titulo().getText();
                String autor = vista.getTxt_autor().getText();
                String editorial = (String) vista.getcBox_editorial().getSelectedItem();
                float precio = Float.parseFloat(vista.getTxt_precio().getText());
                boolean disponible = vista.getrBtn_disponible().isSelected();
                
                libroModelo.setIsbn(isbn);
                libroModelo.setTitulo(titulo);
                libroModelo.setAutor(autor);
                libroModelo.setEditorial(editorial);
                libroModelo.setPrecio(precio);
                libroModelo.setDisponible(disponible);
                
                // Llamar al método de agregar libro en el modelo
                libroModelo.agregarLibro();
                
                JOptionPane.showMessageDialog(vista, "El libro se ha agregado con éxito.", 
                        "Libro Agregado", JOptionPane.INFORMATION_MESSAGE);
                
                // Limpiar los campos
                vista.getTxt_isbn().setText("");
                vista.getTxt_titulo().setText("");
                vista.getTxt_autor().setText("");
                vista.getTxt_precio().setText("");
                vista.getcBox_editorial().setSelectedIndex(0);
                vista.getrBtn_disponible().setSelected(true);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Por favor ingrese un valor numérico válido en el precio.", 
                        "Error en el Precio", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
