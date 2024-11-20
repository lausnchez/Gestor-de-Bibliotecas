/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Biblioteca.UBICACION;

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
    private UBICACION biblioteca;
    private boolean disponible;
    
    // Constructor
    public Libro(int id, String isbn, String titulo, String autor,String genero, String editorial,float precio, UBICACION biblioteca, boolean disponible) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public UBICACION getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(UBICACION biblioteca) {
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
        String sql = "INSERT INTO libros (id_lib, isbn_lib, titulo_lib, autor_lib, genero_lib, editorial_lib, precio_lib, estado_lib) " +
                 "VALUES (" + this.id + ", '" + this.isbn + "', '" + this.titulo + "', '" + this.autor + "', '" + this.genero + "', '" + this.editorial + "', " + this.precio + ",'" + this.biblioteca.toString() + "' " + (this.disponible ? 1 : 0) + ")";
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
        String sql = "SELECT * FROM libros WHERE id_lib = " + id;
        ResultSet rs = BaseDatos.ejecutarSelect(sql);
        try {
            if (rs != null && rs.next()) {
                String isbn = rs.getString("isbn_lib");
                String titulo = rs.getString("titulo_lib");
                String autor = rs.getString("autor_lib");
                String genero= rs.getString("genero_lib");
                String editorial=rs.getString("editorial_lib");
                float precio=rs.getFloat("precio_lib");
                UBICACION biblioteca = UBICACION.valueOf(rs.getString("biblio_lib"));
                boolean disponible = rs.getBoolean("estado_lib");
                return new Libro(id,isbn, titulo, autor, genero, editorial, precio, biblioteca, disponible);
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
        String sql = "SELECT * FROM libros";
        ResultSet rs = BaseDatos.ejecutarSelect(sql);
        try {
            while (rs != null && rs.next()) {
                int id = rs.getInt("id_lib");
                String isbn = rs.getString("isbn_lib");
                String titulo = rs.getString("titulo_lib");
                String autor = rs.getString("autor_lib");
                String genero= rs.getString("genero_lib");
                String editorial = rs.getString("editorial_lib");
                float precio=rs.getFloat("precio_lib");
                UBICACION biblioteca = UBICACION.valueOf(rs.getString("biblio_lib"));
                boolean disponible = rs.getBoolean("estado_lib");
                libros.add(new Libro(id,isbn, titulo, autor, genero, editorial, precio, biblioteca, disponible));
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
                String genero= rs.getString("genero_lib");
                String editorial = rs.getString("editorial_lib");
                float precio=rs.getFloat("precio_lib");
                UBICACION biblioteca = UBICACION.valueOf(rs.getString("biblio_lib"));
                boolean disponible = rs.getBoolean("estado_lib");
                return new Libro(id, isbn, titulo, autor, genero, editorial, precio, biblioteca, disponible);
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
        String sql = "DELETE FROM libros WHERE id_lib = " + id;
        BaseDatos.ejecutarUpdate(sql);
    }

    /**
     * Método para mostrar la información del libro
     * @return 
     */
   
    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", genero=" + genero + ", editorial=" + editorial + ", precio=" + precio + ", biblioteca=" + biblioteca + ", disponible=" + disponible + '}';
    }

}
