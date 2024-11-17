/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.ControllerUtils;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author
 */
public class Socio implements Comparable<Socio>{
    private BaseDatos baseDeDatos;
    
    public enum UBICACION {
    A_CORUÑA,
    ÁLAVA,
    ALBACETE,
    ALICANTE,
    ALMERÍA,
    ASTURIAS,
    ÁVILA,
    BADAJOZ,
    BARCELONA,
    BURGOS,
    CÁDIZ,
    CANTABRIA,
    CASTELLÓN,
    CEUTA,
    CÓRDOBA,
    LA_CORUÑA,
    CUENCA,
    GERONA,
    GRANADA,
    GUADALAJARA,
    GIPUZKOA,
    HUELVA,
    HUESCA,
    ISLAS_BALEARES,
    JAÉN,
    LA_RIOJA,
    LAS_PALMAS,
    LEÓN,
    LLEIDA,
    LUGO,
    MADRID,
    MÁLAGA,
    MURCIA,
    NAVARRA,
    OURENSE,
    PALENCIA,
    PONTEVEDRA,
    SALAMANCA,
    SANTA_CRUZ_DE_TENERIFE,
    SEGOVIA,
    SEVILLA,
    SORIA,
    TARRAGONA,
    TERUEL,
    TOLEDO,
    VALENCIA,
    VALLADOLID,
    VIZCAYA,
    ZAMORA,
    ZARAGOZA
}

 
    private int id;
    private String bibliotecaAsociada;
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private boolean pago; 
    private List<Sancion> sanciones;
    private List<Prestamo> prestamos;
    private UBICACION provincia;
    private int numSanciones;
    private String cuentaBancaria;
    
    // Constructores
    //--------------------------------------------------------------------------
    public Socio
        (int id, String bibliotecaAsociada, String dni, String nombre, String apellidos, String telefono, String email, boolean pago,UBICACION provincia, int numSanciones, String cuentaBancaria) {
        this.id = id;
        this.bibliotecaAsociada = bibliotecaAsociada;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.pago = pago;
        this.sanciones = sanciones;
        this.prestamos = prestamos;
        this.provincia = provincia;
        this.numSanciones = numSanciones;
        this.cuentaBancaria = cuentaBancaria;
    }

    public Socio(){
        this.id = -1;
        this.bibliotecaAsociada = "";
        this.dni = "";
        this.nombre = "";
        this.apellidos = "";
        this.telefono = "";
        this.email = "";
        this.pago = false;
        this.sanciones = null;
        this.prestamos = null;
        this.provincia = null;
        this.numSanciones = 0;
        this.cuentaBancaria = "";
    }

    // Getters & Setters
    //--------------------------------------------------------------------------
    public BaseDatos getBaseDeDatos() {
        return baseDeDatos;
    }

    public void setBaseDeDatos(BaseDatos baseDeDatos) {
        this.baseDeDatos = baseDeDatos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBibliotecaAsociada() {
        return bibliotecaAsociada;
    }

    public void setBibliotecaAsociada(String bibliotecaAsociada) {
        this.bibliotecaAsociada = bibliotecaAsociada;
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

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
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

    public UBICACION getProvincia() {
        return provincia;
    }

    public void setProvincia(UBICACION provincia) {
        this.provincia = provincia;
    }

    public int getNumSanciones() {
        return numSanciones;
    }

    public void setNumSanciones(int numSanciones) {
        this.numSanciones = numSanciones;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    // Métodos
    //--------------------------------------------------------------------------
    @Override
    public int compareTo(Socio o) {
        if(this.getId() > o.getId()) return 1;
        else return -1;
    }
    
    /**
     * Método para añadir un préstamo a la lista de libros prestados
     * @param prestamo 
     */
    public void agregarPrestamo(Prestamo prestamo) {
        this.prestamos.add(prestamo);
    }

    /**
     * Método para añadir una sanción
     * @param sancion 
     */
    public void agregarSancion(Sancion sancion) {
        this.sanciones.add(sancion);
    }

    /**
     * Método para actualizar el estado de la cuota
     * @param idSocio -> id del socio que queremos actualizar
     * @param pago -> estado al que queremos actualizar el pago
     */
    public static void actualizarCuota(int idSocio, boolean pago) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "UPDATE socios SET pago = ? WHERE id_prest = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, pago);
            stmt.setInt(2, idSocio);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Estado de la cuota actualizado correctamente.");
            }else System.out.println("No se actualizaron datos");
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
            String sql = "INSERT INTO socios(dni_soc, nombre_soc, apellidos_soc, tlf_soc, email_soc, provincia_soc, pago_soc, biblioteca_soc, cuentaBancaria_soc) VALUES (?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, socio.getDni());
            stmt.setString(2, socio.getNombre());
            stmt.setString(3, socio.getApellidos());
            stmt.setString(4, socio.getTelefono());
            stmt.setString(5, socio.getEmail());
            stmt.setString(6, socio.getProvincia().toString());
            stmt.setBoolean(7, socio.isPago());
            stmt.setString(8, socio.getBibliotecaAsociada());
            stmt.setString(9, socio.getCuentaBancaria());

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
        Socio socioNuevo = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM socios WHERE id_soc = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idSocio);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_soc");
                String biblioteca = rs.getString("biblioteca_soc");
                String dni = rs.getString("dni_soc");
                String nombre = rs.getString("nombre_soc");
                String apellidos = rs.getString("apellidos_soc");
                String tlf = rs.getString("tlf_soc");
                String email = rs.getString("email_soc");
                String provincia = rs.getString("provincia_soc");
                int numSanciones = rs.getInt("numSanciones_soc");
                String cuentaBancaria = rs.getString("cuentaBancaria_soc");
                boolean pago = rs.getBoolean("pago_soc");

                socioNuevo = new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, UBICACION.valueOf(provincia.toUpperCase()), numSanciones, cuentaBancaria);
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
        return socioNuevo;
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
            String sql = "SELECT * FROM socios WHERE pago_soc = 0";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_soc");
                String biblioteca = rs.getString("biblioteca_soc");
                String dni = rs.getString("dni_soc");
                String nombre = rs.getString("nombre_soc");
                String apellidos = rs.getString("apellidos_soc");
                String tlf = rs.getString("tlf_soc");
                String email = rs.getString("email_soc");
                String provincia = rs.getString("provincia_soc");
                int numSanciones = rs.getInt("numSanciones_soc");
                String cuentaBancaria = rs.getString("cuentaBancaria_soc");
                boolean pago = rs.getBoolean("pago_soc");

                Socio socio = new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, UBICACION.valueOf(provincia.toUpperCase()), numSanciones, cuentaBancaria);
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
    
    
    
    public static List<Socio> obtenerSocios() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Socio socioNuevo = null;
        List<Socio> listado = new ArrayList<>();
        try {
            conn = BaseDatos.obtenerConnection();
            //String sql = "SELECT * FROM socios;";
            String sql = "select socios.id_soc,\n" +
                " bibliotecas.nombre_biblio,\n" +
                " socios.dni_soc,\n" +
                " socios.nombre_soc,\n" +
                " socios.apellidos_soc,\n" +
                " socios.tlf_soc,\n" +
                " socios.email_soc,\n" +
                " socios.provincia_soc,\n" +
                " socios.numSanciones_soc,\n" +
                " socios.cuentaBancaria_soc,\n" +
                " socios.pago_soc\n" +
                " FROM socios, bibliotecas\n" +
                " WHERE socios.biblioteca_soc = bibliotecas.id_biblio;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id_soc");
                String biblioteca = rs.getString("nombre_biblio");
                String dni = rs.getString("dni_soc");
                String nombre = rs.getString("nombre_soc");
                String apellidos = rs.getString("apellidos_soc");
                String tlf = rs.getString("tlf_soc");
                String email = rs.getString("email_soc");
                String provincia = rs.getString("provincia_soc");
                int numSanciones = rs.getInt("numSanciones_soc");
                String cuentaBancaria = rs.getString("cuentaBancaria_soc");
                boolean pago = rs.getBoolean("pago_soc");

                socioNuevo = new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, UBICACION.valueOf(provincia.toUpperCase()), numSanciones, cuentaBancaria);
                listado.add(socioNuevo);
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
        return listado;
    }
}
