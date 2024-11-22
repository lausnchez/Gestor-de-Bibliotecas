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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Biblioteca.UBICACION;

/**
 *
 * @author 
 */
public class Prestamo {
    private int id;
    private Socio socio;
    private UBICACION biblioteca;
    private Date fechaPrestamo;
    private Libro libro;
    private Date fechaDevolucion;
    private boolean devuelto;
    
    // Constructor
    public Prestamo(int id, Socio socio, UBICACION biblioteca, Date fechaPrestamo, Libro libro, Date fechaDevolucion, boolean devuelto) {
        this.id = id;
        this.socio = socio;
        this.biblioteca = biblioteca;
        this.fechaPrestamo = fechaPrestamo;
        this.libro = libro;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    private Prestamo(int idPrestamo, Socio socio, Date fechaPrestamo, Libro libro, Date fechaDevolucion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public UBICACION getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(UBICACION biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
    
    
    // Métodos
    
    /**
     * Método para registrar un préstamo en la base de datos
     * @param prestamo 
     */
    public static void registrarPrestamo(Prestamo prestamo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "INSERT INTO prestamos (id_prest, id_socio_prest, biblioteca_prest fecha_prestamo, id_lib, fecha_dev, devuelto) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, prestamo.getSocio().getId());
            stmt.setString(2, prestamo.getBiblioteca().toString());
            stmt.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            stmt.setInt(4, prestamo.getLibro().getId());
            stmt.setDate(5, new java.sql.Date(prestamo.getFechaDevolucion().getTime()));
            stmt.setBoolean(6, prestamo.isDevuelto());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para obtener un préstamo por su ID
     * @param idPrestamo
     * @return 
     */
    public static Prestamo obtenerPrestamoPorId(int idPrestamo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Prestamo prestamo = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM prestamos WHERE id_prest = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPrestamo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idSocio = rs.getInt("id_socio_prest");
                 String bibliotecaStr = rs.getString("biblio_prest"); 
                Date fechaPrestamo = rs.getDate("fecha_prest");
                int idLibro = rs.getInt("id_libro_prest");
                Date fechaDevolucion = rs.getDate("fecha_dev");
                boolean devuelto = rs.getBoolean("devuelto");

                Socio socio = Socio.obtenerSocioPorId(idSocio);
                Libro libro = Libro.obtenerLibroPorId(idLibro);

                prestamo = new Prestamo(idPrestamo, socio, fechaPrestamo, libro, fechaDevolucion);
                prestamo.setDevuelto(devuelto);
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

        return prestamo;
    }

    /**
     * Método para obtener los préstamos no devueltos de un socio
     * @param socio
     * @return 
     */
    public static List<Prestamo> obtenerPrestamosNoDevueltos(Socio socio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Prestamo> prestamos = new ArrayList<>();

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM prestamos WHERE id_socio_prest = ? AND devuelto = false";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, socio.getId());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPrestamo = rs.getInt("id");
                 UBICACION biblioteca = UBICACION.valueOf(rs.getString("biblio_lib"));
                Date fechaPrestamo = rs.getDate("fecha_prestamo");
                int idLibro = rs.getInt("id_libro");
                Date fechaDevolucion = rs.getDate("fecha_devolucion");
                boolean devuelto = rs.getBoolean("devuelto");
                Libro libro = Libro.obtenerLibroPorId(idLibro);

                Prestamo prestamo = new Prestamo(idPrestamo, socio, fechaPrestamo, libro, fechaDevolucion);
                prestamo.setDevuelto(devuelto);
                prestamos.add(prestamo);
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

        return prestamos;
    }

    /**
     * Método para devolver un libro (marcar como devuelto)
     * @param idPrestamo 
     */
    public static void devolverLibro(int idPrestamo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "UPDATE prestamos SET devuelto = true WHERE id_prest = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPrestamo);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para comprobar si un libro ya ha sido prestado a un socio
     * @param socio
     * @param libro
     * @return 
     */
    public static boolean libroYaPrestado(Socio socio, Libro libro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM prestamos WHERE socio_prest = ? AND id_libro_prest = ? AND devuelto = false";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, socio.getId());
            stmt.setInt(2, libro.getId());
            rs = stmt.executeQuery();

            return rs.next();  // Si encuentra un préstamo no devuelto, el libro ya está prestado
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

        return false;
    }
}
