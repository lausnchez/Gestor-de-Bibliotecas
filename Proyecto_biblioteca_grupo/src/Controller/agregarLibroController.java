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
import modelo.Biblioteca;

/**
 *
 * @author Paula
 */
public class agregarLibroController implements ActionListener{
    private agregarLibro vista;
    private Libro libroModelo;
    private Biblioteca biblioteca;

    public agregarLibroController(agregarLibro vista,Libro libroModelo) {
        this.vista = vista;
        this.libroModelo = libroModelo;
        this.biblioteca=biblioteca;

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
            // Obtener la provincia seleccionada
            String provinciaSeleccionada = (String) vista.getcBox_ubicacion().getSelectedItem();
            provinciaSeleccionada = provinciaSeleccionada != null ? provinciaSeleccionada.trim() : null;

            // Validar que la provincia seleccionada no sea vacía o nula
            if (provinciaSeleccionada == null || provinciaSeleccionada.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor, selecciona una provincia válida.",
                        "Provincia Inválida", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si la provincia no es válida
            }

            System.out.println("Provincia seleccionada: " + provinciaSeleccionada);

            // Resto del código para procesar el libro
            int bibliotecaId = biblioteca.obtenerIdBibliotecaPorProvincia(provinciaSeleccionada);
            System.out.println("ID de la biblioteca: " + bibliotecaId);

            // Validar que el ID de la biblioteca sea válido
            if (bibliotecaId == 0) {
                JOptionPane.showMessageDialog(vista, "No se encontró la biblioteca para la provincia seleccionada.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // (Continúa con la lógica para agregar el libro)
        } catch (Exception ex) {
            // Manejo de errores
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al agregar el libro: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
   }
    }
}
