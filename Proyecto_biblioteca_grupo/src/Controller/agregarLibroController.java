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

    public agregarLibroController(agregarLibro vista,Libro libroModelo) {
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

        // Obtención de datos de la vista
        try {
            String isbn = vista.getTxt_isbn().getText().trim();
            String titulo = vista.getTxt_titulo().getText().trim();
            String autor = vista.getTxt_autor().getText().trim();
            String editorial = (String) vista.getcBox_editorial().getSelectedItem();
            String precioStr = vista.getTxt_precio().getText().trim();
            boolean disponible = vista.getrBtn_disponible().isSelected();

            // Validar que los campos no estén vacíos
            if (isbn.isEmpty() || titulo.isEmpty() || autor.isEmpty() || editorial == null || precioStr.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor, complete todos los campos obligatorios.",
                        "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                return; // Salir si algún campo está vacío
            }

            // Validar el precio
            float precio;
            try {
                precio = Float.parseFloat(precioStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Por favor ingrese un valor numérico válido en el precio.",
                        "Error en el Precio", JOptionPane.ERROR_MESSAGE);
                return; // Salir si el precio no es válido
            }

            // Obtener la provincia seleccionada
            String provinciaSeleccionada = (String) vista.getcBox_ubicacion().getSelectedItem();
            provinciaSeleccionada = provinciaSeleccionada.trim();
              System.out.println("Provincia seleccionada: " + provinciaSeleccionada);

            // Verificar que la provincia seleccionada no sea vacía o nula
            if (provinciaSeleccionada != null && !provinciaSeleccionada.isEmpty()) {
                // Obtener el ID de la biblioteca de la provincia seleccionada
                int bibliotecaId = obtenerIdBibliotecaPorProvincia(provinciaSeleccionada);
 System.out.println("ID de la biblioteca: " + bibliotecaId);

                // Verificar si se obtuvo un ID válido (diferente de 0)
                if (bibliotecaId != 0) {
                    // Establecer los valores en el modelo
                    libroModelo.setIsbn(isbn);
                    libroModelo.setTitulo(titulo);
                    libroModelo.setAutor(autor);
                    libroModelo.setEditorial(editorial);
                    libroModelo.setPrecio(precio);
                    libroModelo.setDisponible(disponible);
                    libroModelo.setBiblioteca(bibliotecaId);  // Asignar el ID de la biblioteca

                    // Llamar al método para agregar el libro en el modelo
                    libroModelo.agregarLibro();

                    // Mostrar mensaje de éxito
                    JOptionPane.showMessageDialog(vista, "El libro se ha agregado con éxito.",
                            "Libro Agregado", JOptionPane.INFORMATION_MESSAGE);

                    // Limpiar los campos de la vista después de agregar el libro
                    vista.getTxt_isbn().setText("");
                    vista.getTxt_titulo().setText("");
                    vista.getTxt_autor().setText("");
                    vista.getTxt_precio().setText("");
                    vista.getcBox_editorial().setSelectedIndex(0);
                    vista.getrBtn_disponible().setSelected(true);
                } else {
                    JOptionPane.showMessageDialog(vista, "No se encontró la biblioteca para la provincia seleccionada.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Por favor, selecciona una provincia válida.",
                        "Provincia Inválida", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            // Captura cualquier excepción inesperada
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al agregar el libro: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Esto es útil para depurar, se puede quitar en producción
        }
    }
    }
}
