/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Libro;
import vista.agregarLibro;
import vista.librosView;

/**
 *
 * @author Paula
 */
public class LibrosViewController implements ActionListener {

    private librosView librosView;
    private Libro libroModelo;
    private agregarLibro agregarLibro;

    public LibrosViewController(Libro libroModelo) {
        this.libroModelo = libroModelo;
        this.librosView = new librosView();

        this.librosView.setVisible(true);

        this.librosView.getBtn_agregar().addActionListener(this);
        this.librosView.getBtn_editar().addActionListener(this);
        this.librosView.getBtn_eliminar().addActionListener(this);
        this.librosView.getBtn_guardar().addActionListener(this);
         // Llamamos a la función para cargar los libros en la tabla al inicio para que se muestren nada más aparezca el jFrame
        cargarLibrosEnTabla();
    }
    
    /**
     * función que carga todos los libros y los muestra en la tabla
     */
     private void cargarLibrosEnTabla() {
        // Obtener la lista de libros del modelo
        List<Libro> libros = this.libroModelo.obtenerTodosLosLibros();

        // Crear el modelo para el JTable
        DefaultTableModel model = new DefaultTableModel();
        
        // Definir las columnas del JTable
        model.addColumn("ID");
        model.addColumn("ISBN");
        model.addColumn("Título");
        model.addColumn("Autor");
        model.addColumn("Género");
        model.addColumn("Editorial");
        model.addColumn("Precio");
        model.addColumn("Ubicación");
        model.addColumn("Estado");

        // Llenar las filas con los datos de los libros
        for (Libro libro : libros) {
            model.addRow(new Object[]{
                libro.getId(),
                libro.getIsbn(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getGenero(),
                libro.getEditorial(),
                libro.getPrecio(),
                libro.getBiblioteca(),
                libro.isDisponible() ? "Disponible" : "Prestado"
            });
        }

        // Establecer el modelo al JTable para que se muestren los libros
        this.librosView.getTbl_libros().setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.librosView.getBtn_eliminar()) {
            String idStr = JOptionPane.showInputDialog("Introduzca el ID del libro que desea eliminar:");
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    // Convertimos el ID a un número entero
                    int id = Integer.parseInt(idStr.trim());

                    // Verificar si el libro existe antes de eliminarlo
                    Libro libro = this.libroModelo.obtenerLibroPorId(id);  // buscamos el id del libro

                    if (libro != null) {//si el libro existe
                        this.libroModelo.eliminarLibro(id);  
                        JOptionPane.showMessageDialog(librosView, "El libro con ID " + id + " ha sido eliminado con éxito.",
                                "Libro Eliminado", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(librosView, "No existe un libro con el ID " + id + ". No se pudo eliminar.",
                                "Error en Eliminación", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(librosView, "El ID introducido no es válido. Por favor ingrese un número entero.",
                            "Error en ID", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(librosView, "Debe introducir un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == this.librosView.getBtn_agregar()) {
            
        } else if (e.getSource() == this.librosView.getBtn_editar()) {
            
        }
    }
}
