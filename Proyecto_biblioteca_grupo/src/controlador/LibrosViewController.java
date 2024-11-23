/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Libro;
import vista.EditarLibroView;
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
     private JTable tbl_libros;
     private DefaultTableModel modeloTabla;

     public LibrosViewController(librosView librosView, Libro libroModelo) {
        this.libroModelo = libroModelo;
        this.librosView =librosView;
        this.modeloTabla = new DefaultTableModel();
        tbl_libros = new JTable(modeloTabla);
        librosView.getTbl_libros().setModel(modeloTabla);

        // Registrar botones
        this.librosView.getBtn_agregar().addActionListener(this);
        this.librosView.getBtn_editar().addActionListener(this);
        this.librosView.getBtn_eliminar().addActionListener(this);
        this.librosView.getBtn_guardar().addActionListener(this);

     cargarCabeceraTabla(); 
     cargarLibrosEnTabla();  // Cargar los libros al iniciar
     
     // Aquí agregamos la tabla a la vista
        this.librosView.setVisible(true);
    }
    

    // Carga las columnas de la tabla
    private void cargarCabeceraTabla() {
        modeloTabla.setColumnIdentifiers(new String[]{
            "ID", "ISBN", "Título", "Autor", "Editorial", "Precio", "Ubicación", "Estado"
        });
      
   
    }
    /**
     * Función que carga todos los libros y los muestra en la tabla
     */
    public void cargarLibrosEnTabla() {
    modeloTabla.setRowCount(0);  // Vaciar la tabla antes de cargar los nuevos datos
    
    List<Libro> listadoLibros = this.libroModelo.obtenerTodosLosLibros(); // Obtener los libros desde el modelo
    
    if (listadoLibros.isEmpty()) {
        JOptionPane.showMessageDialog(librosView, "No se han encontrado libros.", 
            "Sin Libros", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // Añadimos cada libro al modelo de la tabla
    for (Libro libro : listadoLibros) {
        String estado = libro.isDisponible() ? "Disponible" : "Prestado";
        
        
        System.out.println("Agregando libro: " + libro.getTitulo());
        
        modeloTabla.addRow(new Object[] {
            libro.getId(),
            libro.getIsbn(),
            libro.getTitulo(),
            libro.getAutor(),
            libro.getEditorial(),
            libro.getPrecio(),
            libro.getBiblioteca(), 
            estado
                
        });  System.out.println("Columnas: " + modeloTabla.getColumnCount());
        System.out.println("Filas: " + modeloTabla.getRowCount());
        
        System.out.println("La tabla es visible: " + tbl_libros.isVisible());
    // actualiza
        modeloTabla.fireTableDataChanged();
    }
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.librosView.getBtn_eliminar()) {
        System.out.println("Botón eliminado presionado");
        String idStr = JOptionPane.showInputDialog("Introduzca el ID del libro que desea eliminar:");
        System.out.println("ID ingresado: '" + idStr + "'"); 

        if (idStr != null && !idStr.trim().isEmpty()) {
            if (esNumero(idStr.trim())) {
                int id = Integer.parseInt(idStr.trim());  // Convertimos la cadena del ID a un número entero

                Libro libro = this.libroModelo.obtenerLibroPorId(id);

                if (libro != null) { 
                    System.out.println("Procediendo a eliminar el libro con ID: " + id);
                    this.libroModelo.eliminarLibro(id);  
                    JOptionPane.showMessageDialog(librosView, "El libro con ID " + id + " ha sido eliminado con éxito.",
                            "Libro Eliminado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(librosView, "No existe un libro con el ID " + id + ". No se pudo eliminar.",
                            "Error en Eliminación", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Si no es un número válido
                JOptionPane.showMessageDialog(librosView, "El ID introducido no es un número válido. Por favor ingrese un número entero.",
                        "Error en ID", JOptionPane.ERROR_MESSAGE);
            }
         } else {
            JOptionPane.showMessageDialog(librosView, "Debe introducir un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
         }
        } else if (e.getSource() == this.librosView.getBtn_agregar()) {
            agregarLibro vistaAgregarLibro = new agregarLibro();
            Libro modeloLibro = new Libro();
            agregarLibroController controller = new agregarLibroController(vistaAgregarLibro, modeloLibro);
            vistaAgregarLibro.setVisible(true);
        } else if (e.getSource() == this.librosView.getBtn_editar()) {
            editarLibro();
         } else {
             JOptionPane.showMessageDialog(librosView, "Debe seleccionar un libro de la tabla para editarlo.", "Error", JOptionPane.ERROR_MESSAGE);
         }

    }
        

    
    public void editarLibro() {
    int fila = this.librosView.getTbl_libros().getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(librosView, "Seleccione un libro.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener ID y datos del libro seleccionado
    int id = (int) this.librosView.getTbl_libros().getValueAt(fila, 0);
    Libro libro = Libro.obtenerLibroPorId(id);  // Método para obtener el libro por ID

    if (libro == null) {
        JOptionPane.showMessageDialog(librosView, "Error al cargar los datos del libro.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Crear la vista de edición del libro
    EditarLibroView editarView = new EditarLibroView();  // Vista para editar libro

    // Pasa los datos al formulario
    editarView.getTxt_id().setText(String.valueOf(libro.getId()));
    editarView.getTxt_isbn().setText(libro.getIsbn());
    editarView.getTxt_titulo().setText(libro.getTitulo());
    editarView.getTxt_autor().setText(libro.getAutor());
    editarView.getTxt_editorial().setText(libro.getEditorial());
    editarView.getTxt_precio().setText(String.valueOf(libro.getPrecio()));
    editarView.getcBox_biblioteca();

    // Agrega acción al botón "Guardar"
    editarView.getBtn_guardar().addActionListener(e -> guardarCambios(editarView, libro));

    // Mostrar el formulario
    editarView.setVisible(true);
}

private void guardarCambios(EditarLibroView editarView, Libro libro) {
    try {
        // Obtener nuevos valores de los campos
        String nuevoIsbn = editarView.getTxt_isbn().getText();
        String nuevoTitulo = editarView.getTxt_titulo().getText();
        String nuevoAutor = editarView.getTxt_autor().getText();
        String nuevaEditorial = editarView.getTxt_editorial().getText();
        String nuevoPrecioStr = editarView.getTxt_precio().getText();
        
        // Obtener el índice de la biblioteca seleccionada en el ComboBox
        int indiceBiblioteca = editarView.getcBox_biblioteca().getSelectedIndex();

        // Validaciones simples (puedes ampliarlas)
        if (nuevoIsbn.isEmpty() || nuevoTitulo.isEmpty() || nuevoAutor.isEmpty() || nuevaEditorial.isEmpty() || 
            nuevoPrecioStr.isEmpty() || indiceBiblioteca == -1) {  // -1 indica que no se ha seleccionado ninguna opción
            JOptionPane.showMessageDialog(editarView, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación de precio
        float nuevoPrecio;
        try {
            nuevoPrecio = Float.parseFloat(nuevoPrecioStr);  // Convertir a float
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(editarView, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Actualizar objeto libro con los nuevos valores
        libro.setIsbn(nuevoIsbn);
        libro.setTitulo(nuevoTitulo);
        libro.setAutor(nuevoAutor);
        libro.setEditorial(nuevaEditorial);
        libro.setPrecio(nuevoPrecio);
        libro.setBiblioteca(indiceBiblioteca);  // Usar el índice para setear la biblioteca

        // Guardar cambios en la base de datos
        if (Libro.actualizarLibro(libro)) {
            JOptionPane.showMessageDialog(editarView, "Libro actualizado con éxito.");
            editarView.dispose();  // Cerrar el formulario
            cargarLibrosEnTabla();  // Refrescar la tabla en la vista principal
        } else {
            JOptionPane.showMessageDialog(editarView, "Error al actualizar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(editarView, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}

// Método para verificar si una cadena es un número válido
private boolean esNumero(String str) {
    try {
        Integer.parseInt(str);
        return true; // Si puede convertirlo, es un número válido
    } catch (NumberFormatException e) {
        return false; // Si ocurre un error, no es un número válido
    }
}
}