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
import modelo.Biblioteca.UbiBiblio;

/**
 *
 * @author 
 */
public class Libro {
    private int id;
    private String isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private float precio;
    private UbiBiblio biblioteca;
    private boolean disponible;
    
    // Constructor
    public Libro(int id, String isbn, String titulo, String autor, String editorial,float precio, UbiBiblio biblioteca, boolean disponible) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.precio = precio;
        this.biblioteca = biblioteca;
        this.disponible = disponible;
    }

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

    public UbiBiblio getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(UbiBiblio biblioteca) {
        this.biblioteca = biblioteca;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

   

    // Métodos
    /**
     * Método para agregar un libro a la base de datos
     */
    public void agregarLibro() {
        String sql = "INSERT INTO libros (id_lib, isbn_lib, titulo_lib, autor_lib, editorial_lib, precio_lib, estado_lib) " +
                 "VALUES (" + this.id + ", '" + this.isbn + "', '" + this.titulo + "', '" + this.autor + "', '" + this.editorial + "', " + this.precio + ",'" + this.biblioteca.toString() + "' " + (this.disponible ? 1 : 0) + ")";
        BaseDatos.ejecutarUpdate(sql);
    }

    /**
     * Método para actualizar la disponibilidad de un libro
     */
    public void actualizarDisponibilidad() {
        String sql = "UPDATE libros SET estado_lib = " + (this.disponible ? 1 : 0) + " WHERE id_lib = " + this.id;
        BaseDatos.ejecutarUpdate(sql);
    }

    // Método para obtener un libro de la base de datos por su ID
    public static Libro obtenerLibroPorId(int id) {
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
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra el libro
    }

    /**
     * Método para obtener todos los libros de la base de datos
     * @return 
     */
    public static List<Libro> obtenerTodosLosLibros() {
        List<Libro> libros = new ArrayList<>();
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
   
    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial + ", precio=" + precio + ", biblioteca=" + biblioteca + ", disponible=" + disponible + '}';
    }
}
