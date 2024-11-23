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
    public Libro(int id, String isbn, String titulo, String autor, String editorial, float precio, int biblioteca, boolean disponible) {
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
        String sql = "SELECT idL_lib, isbn_lib, titulo_lib, autor_lib, editorial_lib, precio_lib, estado_lib , biblioteca_lib FROM libros WHERE idL_lib = ?";
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
        String sql = "SELECT idL_lib, isbn_lib, titulo_lib, autor_lib, editorial_lib, precio_lib, estado_lib , biblioteca_lib FROM libros";
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
    
    public boolean esBibliotecaValida(int bibliotecaId) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        // Establece la conexión con la base de datos
        conn = BaseDatos.obtenerConnection();

        // Consulta para verificar si el ID de la biblioteca existe
        String sql = "SELECT COUNT(*) FROM bibliotecas WHERE id_biblio = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, bibliotecaId);  // Establecer el ID de la biblioteca en el query
        rs = stmt.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            return true;  // La biblioteca existe
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return false;  // Si no existe, retorna false
}

    /**
     * Método para actualizar del libro
     * @return 
     */
 public static boolean actualizarLibro(Libro libro) {
    Connection conn = null;
    PreparedStatement stmt = null;

    // Verifica si el ID de la biblioteca es válido
    if (!esBibliotecaValida(libro.getBiblioteca())) {
        JOptionPane.showMessageDialog(null, "El ID de la biblioteca no es válido.");
        return false;  // Detenemos la ejecución si no es válido
    } else {
    }

    try {
        conn = BaseDatos.obtenerConnection();

        // Sentencia SQL para actualizar el libro
        String sql = "UPDATE libros SET isbn_lib = ?, titulo_lib = ?, autor_lib = ?, editorial_lib = ?, precio_lib = ?, biblioteca_lib = ?, estado_lib = ? WHERE idL_lib = ?";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, libro.getIsbn());
        stmt.setString(2, libro.getTitulo());
        stmt.setString(3, libro.getAutor());
        stmt.setString(4, libro.getEditorial());
        stmt.setFloat(5, libro.getPrecio());
        stmt.setInt(6, libro.getBiblioteca());  // ID de la biblioteca
        stmt.setBoolean(7, libro.isDisponible()); 
        stmt.setInt(8, libro.getId()); 

        int filasAfectadas = stmt.executeUpdate();  

        return filasAfectadas > 0;
    } catch (SQLException e) {
        e.printStackTrace();  
        return false; 
    } finally {
        // Cerrar los recursos
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
}
  

  
