/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
<<<<<<< HEAD
=======
import modelo.Biblioteca.UbiBiblio;
>>>>>>> main

public class Libro {
    private int id;
    private String isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private float precio;
    private int biblioteca;  // El ID de la biblioteca ahora es un int
    private boolean disponible;
    
    // Constructor
<<<<<<< HEAD
    public Libro(int id, String isbn, String titulo, String autor, String editorial, float precio, int biblioteca, boolean disponible) {
=======
    public Libro(int id, String isbn, String titulo, String autor, String editorial,float precio, UbiBiblio biblioteca, boolean disponible) {
>>>>>>> main
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.precio = precio;
        this.biblioteca = biblioteca; // ID de la biblioteca
        this.disponible = disponible;
    }

    public Libro() {
        // Constructor por defecto
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

<<<<<<< HEAD
=======
  
>>>>>>> main
    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(int biblioteca) {
        this.biblioteca = biblioteca;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    /**
     * 
     * @param
     * @return un autor
     */
    public static int insertarAutor(String nombreAutor) {
    int idAutor = -1;
    String sql = "INSERT INTO autores (nombre_aut) VALUES (?)";

    try (Connection conn = BaseDatos.obtenerConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        stmt.setString(1, nombreAutor);
        int filasAfectadas = stmt.executeUpdate();
        
        // Si se insertó correctamente, obtener el idAutor generado
        if (filasAfectadas > 0) {
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idAutor = rs.getInt(1); 
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return idAutor;  // Devuelve el idAutor insertado
}
    
    public static void registrarLibro(Libro libro) {
         int idAutor = insertarAutor(libro.getAutor());  

    // Si el autor no se pudo insertar (por algún error), no continuamos con el registro del libro
        if (idAutor == -1) {
            System.out.println("Error al registrar el autor.");
            return;
        }
        // Consulta SQL 
        String sql = "INSERT INTO libros (biblioteca_lib, isbn_lib, titulo_lib, autor_lib, editorial_lib, precio_lib, estado_lib) " +
                     "VALUES (?, ?, ?, ?, ?, ?,?)";

        try (Connection conn = BaseDatos.obtenerConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Establecer los valores de los parámetros de la consulta SQL
            stmt.setInt(1, libro.getBiblioteca());
            stmt.setString(2, libro.getIsbn());
            stmt.setString(3, libro.getTitulo());
            stmt.setInt(4, idAutor);
            stmt.setString(5, libro.getEditorial());
            stmt.setFloat(6, libro.getPrecio());
            stmt.setBoolean(7, libro.isDisponible());
            System.out.println(libro.precio);
            // Ejecutar
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Precio ingresado: " + libro.getPrecio());
            System.out.println("ID Biblioteca: " + libro.getBiblioteca());
            
            if (filasAfectadas > 0) {
                System.out.println("Libro registrado con éxito.");
            } else {
                System.out.println("Error al registrar el libro.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   /**
    * Método para agregar un libro
    */
    public void agregarLibro() {
<<<<<<< HEAD
       // Validar los campos obligatorios
    if (this.isbn == null || this.titulo == null || this.autor == null || this.editorial == null) {
        throw new IllegalArgumentException("Faltan campos obligatorios para agregar el libro.");
=======
        String sql = "INSERT INTO libros (id_lib, isbn_lib, titulo_lib, autor_lib, editorial_lib, precio_lib, estado_lib) " +
                 "VALUES (" + this.id + ", '" + this.isbn + "', '" + this.titulo + "', '" + this.autor + "', '" + this.editorial + "', " + this.precio + ",'" + this.biblioteca.toString() + "' " + (this.disponible ? 1 : 0) + ")";
        BaseDatos.ejecutarUpdate(sql);
>>>>>>> main
    }

    // Validar ComboBox valor seleccionado
    if (this.biblioteca == 0) { 
        throw new IllegalArgumentException("Debe seleccionar una biblioteca válida.");
    }

    String sql = "INSERT INTO libros (biblioteca_lib, isbn_lib, titulo_lib, autor_lib, editorial_lib, precio_lib, estado_lib) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = BaseDatos.obtenerConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // Establecer los valores en la consulta
        stmt.setInt(1, this.biblioteca);
        stmt.setString(2, this.isbn);
        stmt.setString(3, this.titulo);
        stmt.setString(4, this.autor);
        stmt.setString(5, this.editorial);
        stmt.setFloat(6, this.precio);
        stmt.setInt(7, this.disponible ? 1 : 0);  // Convertir booleano a entero (1 o 0)

        // Ejecutar la consulta
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    /**
     * 
     * @param id
     * @return   Método para obtener un libro por su ID
     */
    public static Libro obtenerLibroPorId(int id) {
<<<<<<< HEAD
        String sql = "SELECT idL_lib, isbn_lib, titulo_lib, autor_lib, editorial_lib, precio_lib, estado_lib, biblioteca_lib FROM libros WHERE idL_lib = ?";
        Libro libro = null;
        try (Connection conn = BaseDatos.obtenerConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String isbn = rs.getString("isbn_lib");
                String titulo = rs.getString("titulo_lib");
                String autor = rs.getString("autor_lib");
                String editorial = rs.getString("editorial_lib");
                float precio = rs.getFloat("precio_lib");
                boolean disponible = "Disponible".equalsIgnoreCase(rs.getString("estado_lib"));
                int bibliotecaId = rs.getInt("biblioteca_lib");

                // Crear el libro con los datos obtenidos
                libro = new Libro(id, isbn, titulo, autor, editorial, precio, bibliotecaId, disponible);
=======
        String sql = "SELECT * FROM libros WHERE idLibros = " + id;
        
        System.out.println("Libro seleccionado con ID: "+id);
        ResultSet rs = BaseDatos.ejecutarSelect(sql);
        try {
            if (rs != null && rs.next()) {   
                System.out.println("Libro encontrado: " + rs.getString("nombre"));
                String isbn = rs.getString("isbn");
                String titulo = rs.getString("nombre");
                String autor = rs.getString("autor");
                String editorial=rs.getString("editorial");
                float precio=rs.getFloat("precio");
                String dbValue = rs.getString("bibliotecaAsociada");
                UbiBiblio biblioteca = UbiBiblio.ALMERIA;
                boolean disponible = rs.getBoolean("estado");
                System.out.println("ISBN: " + isbn + ", Titulo: " + titulo + ", Autor: " + autor);
                
                
                Libro libro=new Libro(id,isbn, titulo, autor, editorial, precio, biblioteca, disponible);
                System.out.println("Libro encontrado con ID " + id + ": " + libro); // Verifica si el libro está siendo creado
                return libro;
                
>>>>>>> main
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libro;
    }
    
    // Método para obtener todos los libros de la base de datos
    public static List<Libro> obtenerTodosLosLibros() {
        List<Libro> libros = new ArrayList<>();
<<<<<<< HEAD
        String sql = "SELECT idL_lib, isbn_lib, titulo_lib, autor_lib, editorial_lib, precio_lib, estado_lib, biblioteca_lib FROM libros";
        ResultSet rs = BaseDatos.ejecutarSelect(sql);
        try {
            while (rs != null && rs.next()) {
                int id = rs.getInt("idL_lib");
                String isbn = rs.getString("isbn_lib");
                String titulo = rs.getString("titulo_lib");
                String autor = rs.getString("autor_lib");
                String editorial = rs.getString("editorial_lib");
                float precio = rs.getFloat("precio_lib");
                boolean disponible = "Disponible".equalsIgnoreCase(rs.getString("estado_lib"));
                int bibliotecaId = rs.getInt("biblioteca_lib");

                // Crear el libro y añadirlo a la lista
                libros.add(new Libro(id, isbn, titulo, autor, editorial, precio, bibliotecaId, disponible));
=======
        String sql = "SELECT l.idLibros, l.isbn, l.nombre, l.autor, l.editorial, l.precio, l.estado, b.provincia " +
             "FROM libros l " +
             "JOIN bibliotecas b ON l.bibliotecaAsociada = b.idBibliotecas";
        ResultSet rs = BaseDatos.ejecutarSelect(sql);
        try {
            while (rs != null && rs.next()) {
                System.out.println("Procesando libro...");
                int id = rs.getInt("idLibros");
                String isbn = rs.getString("isbn");
                String titulo = rs.getString("nombre");
                String autor = rs.getString("autor");
                String editorial = rs.getString("editorial");
                float precio=rs.getFloat("precio");
                String estadoStr = rs.getString("estado");

            // Convertir el estado de String a booleano (Puedes ajustarlo si es necesario)
            boolean disponible = false;
            if ("Disponible".equalsIgnoreCase(estadoStr)) {
                disponible = true;
            } else if ("Prestado".equalsIgnoreCase(estadoStr)) {
                disponible = false;
            }
                
                System.out.println("ID: " + id + ", Titulo: " + titulo + ", Autor: " + autor);
                libros.add(new Libro(id,isbn, titulo, autor, editorial, precio, null, disponible));
>>>>>>> main
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

<<<<<<< HEAD
    // Método para eliminar un libro de la base de datos
    public static void eliminarLibro(int id) {
        String sql = "DELETE FROM libros WHERE idL_lib = ?";
        try (Connection conn = BaseDatos.obtenerConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("El libro con ID " + id + " ha sido eliminado.");
            } else {
                System.out.println("No se encontró un libro con ID " + id + " para eliminar.");
=======
    /**
     * Método para buscar un libro por su ISBN
     * @param isbn
     * @return 
     */
    public static Libro obtenerLibroPorIsbn(String isbn) {
        String sql = "SELECT * FROM libros WHERE isbn_lib = '" + isbn + "'";
        ResultSet rs = BaseDatos.ejecutarSelect(sql);
        try {
            if (rs != null && rs.next()) {
                int id = rs.getInt("id_lib");
                String titulo = rs.getString("titulo_lib");
                String autor = rs.getString("autor_lib");
                String editorial = rs.getString("editorial_lib");
                float precio=rs.getFloat("precio_lib");
                UbiBiblio biblioteca = UbiBiblio.valueOf(rs.getString("biblio_lib"));
                boolean disponible = rs.getBoolean("estado_lib");
                return new Libro(id, isbn, titulo, autor, editorial, precio, biblioteca, disponible);
>>>>>>> main
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
=======
        return null; // Si no se encuentra el libro
    }

    /**
     * Método para eliminar un libro de la base de datos
     * @param id 
     */
    public static void eliminarLibro(int id) {
        String sql = "DELETE FROM libros WHERE idLibros = " + id;
        
        System.out.println("Ejecutando SQL: " + sql); 
        BaseDatos.ejecutarUpdate(sql);
>>>>>>> main
    }

    
    public boolean validarDatos(String id, String isbn, String titulo, String autor, String genero, String editorial, String precio, String ubicacion) {

    StringBuilder mensajeError = new StringBuilder();
    boolean hayError = false;

    // Validar ID
    if (id.trim().length() == 0) {
        mensajeError.append("Falta introducir un ID.\n");
        hayError = true;
    }

    // Validar ISBN
    if (isbn.trim().length() == 0) {
        mensajeError.append("Falta introducir un ISBN válido.\n");
        hayError = true;
    }

    // Validar Título
    if (titulo.trim().length() == 0) {
        mensajeError.append("Falta introducir un título válido.\n");
        hayError = true;
    }

    // Validar Autor
    if (autor.trim().length() == 0) {
        mensajeError.append("Falta introducir un autor válido.\n");
        hayError = true;
    }

    // Validar Género
    if (genero.trim().length() == 0) {
        mensajeError.append("Falta introducir un género válido.\n");
        hayError = true;
    }

    // Validar Editorial (puede ser un ComboBox, asegurémonos de que no sea vacío)
    if (editorial.trim().length() == 0) {
        mensajeError.append("Falta seleccionar una editorial válida.\n");
        hayError = true;
    }

    // Validar Precio (asegurarnos de que es un número válido)
    if (precio.trim().length() == 0) {
        mensajeError.append("Falta introducir un precio válido.\n");
        hayError = true;
    } else {
        try {
            Float.parseFloat(precio);  // Intentamos convertir el precio a un número
        } catch (NumberFormatException ex) {
            mensajeError.append("El precio no es un número válido.\n");
            hayError = true;
        }
    }

    // Validar Ubicación (también puede ser un ComboBox, asegurémonos de que no esté vacío)
    if (ubicacion.trim().length() == 0) {
        mensajeError.append("Falta seleccionar una ubicación válida.\n");
        hayError = true;
    }

    // Si hay errores, mostramos el mensaje en un JOptionPane
    if (hayError) {
        System.err.println(mensajeError.toString());
        JOptionPane.showMessageDialog(null, mensajeError.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        return false;  // Si hay error, no es válido
    }

    // Si no hay errores, todo está bien
    return true;
}
    /**
     * Método para mostrar la información del libro
     * @return 
     */
   
<<<<<<< HEAD
  
=======
    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial + ", precio=" + precio + ", biblioteca=" + biblioteca + ", disponible=" + disponible + '}';
    }
>>>>>>> main
}
  
