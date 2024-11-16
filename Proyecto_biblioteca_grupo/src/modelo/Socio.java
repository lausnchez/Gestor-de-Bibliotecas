/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author
 */
public class Socio {
    private BaseDatos baseDeDatos;
    
    public enum UBICACION {
        MADRID,
        BARCELONA,
        VALENCIA,
        TOLEDO,
        BILBAO,
        CANTABRIA,
        LUGO,
        PALENCIA,
        ASTURIAS,
        MURCIA
    }
    private int id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private boolean cuotaPagada; 
    private List<Sancion> sanciones;
    private List<Prestamo> prestamos;
    private Date fechaAlta;
    private UBICACION ubicacion;
    
    // Constructor
    public Socio(int id, String dni, String nombre, String apellidos, String telefono, String email, boolean cuotaPagada, List<Sancion> sanciones, List<Prestamo> prestamos, Date fecha_alta, UBICACION ubicacion) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.cuotaPagada = cuotaPagada;
        this.sanciones = sanciones;
        this.prestamos = prestamos;
        this.fechaAlta = fecha_alta;
        this.ubicacion = ubicacion;
    }
    
     // Getters y setters
    private Socio(int idSocio, String nombre, String dni, boolean cuotaPagada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCuotaPagada() {
        return cuotaPagada;
    }

    public void setCuotaPagada(boolean cuotaPagada) {
        this.cuotaPagada = cuotaPagada;
    }

    public List<Sancion> getSanciones() {
        return sanciones;
    }

    public void setSanciones(List<Sancion> sanciones) {
        this.sanciones = sanciones;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public Date getFecha_alta() {
        return fechaAlta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fechaAlta = fecha_alta;
    }

    public UBICACION getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UBICACION ubicacion) {
        this.ubicacion = ubicacion;
    }

    // Métodos
    /**
     * Método para añadir un préstamo a la lista de libros prestados
     * @param prestamo 
     */
    public void añadirPrestamo(Prestamo prestamo) {
        this.prestamos.add(prestamo);
    }

    /**
     * Método para añadir una sanción
     * @param sancion 
     */
    public void añadirSancion(Sancion sancion) {
        this.sanciones.add(sancion);
    }

    /**
     * Método para actualizar el estado de la cuota
     * @param idSocio
     * @param cuotaPagada 
     */
    public static void actualizarCuota(int idSocio, boolean cuotaPagada) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "UPDATE socios SET cuota_pagada_soc = ? WHERE id_soc = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, cuotaPagada);
            stmt.setInt(2, idSocio);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Estado de la cuota actualizado correctamente.");
            }
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
     * Método para registrar un socio
     * @param socio 
     */
    public static void registrarSocio(Socio socio) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "INSERT INTO socios (dni_soc, nombre_soc, apellidos_soc, tel_soc, email_soc, f_alta_soc, ubi_soc, cuota_pagada_soc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, socio.getDni());
            stmt.setString(2, socio.getNombre());
            stmt.setString(3, socio.getApellidos());
            stmt.setString(4, socio.getTelefono());
            stmt.setString(5, socio.getEmail());
            stmt.setDate(6, socio.getFecha_alta());
            stmt.setString(7, socio.getUbicacion().toString());
            stmt.setBoolean(8, socio.isCuotaPagada());

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
     * Método para obtener un socio por su ID
     * @param idSocio
     * @return 
     */
    public static Socio obtenerSocioPorId(int idSocio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Socio socio = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM socios WHERE id_soc = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idSocio);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre_soc");
                String dni = rs.getString("dni_soc");
                boolean cuotaPagada = rs.getBoolean("cuota_pagada_soc");
                socio = new Socio(idSocio, nombre, dni, cuotaPagada);
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

        return socio;
    }

    /**
     * Método para obtener los socios con cuotas pendientes
     * @return 
     */
    public static List<Socio> obtenerSociosConCuotaPendiente() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Socio> socios = new ArrayList<>();

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM socios WHERE cuota_pagada_soc = false";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_soc");
                String nombre = rs.getString("nombre_soc");
                String dni = rs.getString("dni_soc");
                boolean cuotaPagada = rs.getBoolean("cuota_pagada_soc");

                Socio socio = new Socio(id, nombre, dni, cuotaPagada);
                socios.add(socio);
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

        return socios;
    }

    /**
     * Método para obtener el estado de los libros prestados
     * @param idSocio 
     */
    public static void obtenerEstadoLibrosPrestados(int idSocio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM prestamos WHERE id_soc_prest = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idSocio);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPrestamo = rs.getInt("id_soc_prest");
                String libro = rs.getString("id_lib_prest").toString();
                Date fechaPrestamo = rs.getDate("fecha_prest");
                Date fechaDevolucion = rs.getDate("fecha_dev");

                System.out.println("ID Prestamo: " + idPrestamo);
                System.out.println("Libro: " + libro);
                System.out.println("Fecha de préstamo: " + fechaPrestamo);
                System.out.println("Fecha de devolución: " + fechaDevolucion);
                System.out.println();
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
    }
}
