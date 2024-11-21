/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class Libro {
    private int id;
    private String isbn;
    private String titulo;
    private String autor;
    private String genero;
    private String editorial;
    private float precio;
    private int biblioteca;  // El ID de la biblioteca ahora es un int
    private boolean disponible;
    
    // Constructor
    public Libro(int id, String isbn, String titulo, String autor, String editorial, float precio, int biblioteca, boolean disponible) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
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
       // Validar los campos obligatorios
    if (this.isbn == null || this.titulo == null || this.autor == null || this.editorial == null) {
        throw new IllegalArgumentException("Faltan campos obligatorios para agregar el libro.");
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libro;
    }
    
    // Método para obtener todos los libros de la base de datos
    public static List<Libro> obtenerTodosLosLibros() {
        List<Libro> libros = new ArrayList<>();
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para mostrar la información del libro
     * @return 
     */
   
  
}
