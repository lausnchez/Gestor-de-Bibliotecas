package controlador;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Biblioteca;
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
     cargarLibrosEnTabla(); 
     
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
    modeloTabla.setRowCount(0);  // Vaciar la tabla 
    
    List<Libro> listadoLibros = this.libroModelo.obtenerTodosLosLibros();
    
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
                     // Recargar la tabla para reflejar el cambio
                    cargarLibrosEnTabla();
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
        } else if (e.getSource() == this.librosView.getBtn_guardar()) {
            cargarLibrosEnTabla();
        }else {
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
    EditarLibroView editarView = new EditarLibroView();  // Vista para editar libro

    // Pasa los datos al formulario
    editarView.getTxt_id().setText(String.valueOf(libro.getId()));
    editarView.getTxt_isbn().setText(libro.getIsbn());
    editarView.getTxt_titulo().setText(libro.getTitulo());
    editarView.getTxt_autor().setText(libro.getAutor());
    editarView.getTxt_editorial().setText(libro.getEditorial());
    editarView.getTxt_precio().setText(String.valueOf(libro.getPrecio()));

    String nombreBiblioteca = obtenerNombreBibliotecaPorId(libro.getBiblioteca()); // Debes crear este método
    editarView.getcBox_biblioteca().setSelectedItem(nombreBiblioteca);

    editarView.getBtn_guardar().addActionListener(e -> guardarCambios(editarView, libro));

    editarView.setVisible(true);
}

private String obtenerNombreBibliotecaPorId(int bibliotecaId) {
    // Lógica para obtener el nombre de la biblioteca a partir del ID.
    if (bibliotecaId == 1) {
        return "Madrid";
    } else if (bibliotecaId == 2) {
        return "Barcelona";
    } else if (bibliotecaId == 3) {
        return "Biblioteca Sur";
    }
    return "Desconocida"; // En caso de que no se encuentre
}

private void guardarCambios(EditarLibroView editarView, Libro libro) {
    try {
        // Obtener nuevos valores de los campos
        String nuevoIsbn = editarView.getTxt_isbn().getText();
        String nuevoTitulo = editarView.getTxt_titulo().getText();
        String nuevoAutor = editarView.getTxt_autor().getText();
        String nuevaEditorial = editarView.getTxt_editorial().getText();
        String nuevoPrecioStr = editarView.getTxt_precio().getText();
        
        int indiceBiblioteca = editarView.getcBox_biblioteca().getSelectedIndex();
        
        //List<Biblioteca> bibliotecas = Biblioteca.obtenerTodasLasBibliotecasPaula();
        List<Biblioteca> bibliotecas = Biblioteca.obtenerBibliotecas();
        int idBiblioteca = bibliotecas.get(indiceBiblioteca).getIdBiblioteca();

        // Validaciones
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

        // Actualizar objeto libro 
        libro.setIsbn(nuevoIsbn);
        libro.setTitulo(nuevoTitulo);
        libro.setAutor(nuevoAutor);
        libro.setEditorial(nuevaEditorial);
        libro.setPrecio(nuevoPrecio);
        libro.setBiblioteca(idBiblioteca); 

        if (Libro.actualizarLibro(libro)) {
            JOptionPane.showMessageDialog(editarView, "Libro actualizado con éxito.");
            editarView.dispose();  
            cargarLibrosEnTabla(); 
        } else {
            JOptionPane.showMessageDialog(editarView, "Error al actualizar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(editarView, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}

private int obtenerIdBibliotecaPorIndice(int indice) {
    // Asumiendo que tienes una lista de bibliotecas cargada en la vista o controlador
    //List<Biblioteca> bibliotecas = Biblioteca.obtenerTodasLasBibliotecasPaula();
    List<Biblioteca> bibliotecas = Biblioteca.obtenerBibliotecas();

    if (indice >= 0 && indice < bibliotecas.size()) {
        return bibliotecas.get(indice).getIdBiblioteca(); 
    }

    return -1;  // Si el índice es inválido
}

/**
 * 
 * @param str
 * @return Método para verificar si una cadena es un número válido
 */
private boolean esNumero(String str) {
    try {
        Integer.parseInt(str);
        return true; // Si puede convertirlo, es un número válido
    } catch (NumberFormatException e) {
        return false; // Si ocurre un error, no es un número válido
    }
}
}