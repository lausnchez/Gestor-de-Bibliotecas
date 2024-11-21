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
import javax.management.remote.JMXConnectorFactory;
import javax.swing.JOptionPane;
/**
 *
 * @author
 */
public class Socio implements Comparable<Socio>{
    private BaseDatos baseDeDatos;
    
    private int id;
    private String biblioteca;
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private boolean pago; 
    private List<Sancion> sanciones;
    private List<Prestamo> prestamos;
    private String provincia;
    private int numSanciones;
    private String cuentaBancaria;
    
    // Constructores
    //--------------------------------------------------------------------------
    public Socio
        (int id, String bibliotecaAsociada, String dni, String nombre, String apellidos, String telefono, String email, boolean pago,String provincia, int numSanciones, String cuentaBancaria) {
        this.id = id;
        this.biblioteca = bibliotecaAsociada;
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

    public Socio
        (String bibliotecaAsociada, String dni, String nombre, String apellidos, String telefono, String email, boolean pago,String provincia, int numSanciones, String cuentaBancaria) {
        this.biblioteca = bibliotecaAsociada;
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
        this.biblioteca = "";
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

    public String getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(String biblioteca) {
        this.biblioteca = biblioteca;
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
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
    /**
     * Ordenar la lista de Socios por ID
     * @param o
     * @return 
     */
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
            stmt.setString(8, socio.getBiblioteca());
            stmt.setString(9, socio.getCuentaBancaria());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar cliente");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión");
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
            String sql = "SELECT * FROM socios WHERE id_soc LIKE ?";
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

                socioNuevo = new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, provincia.toUpperCase(), numSanciones, cuentaBancaria);
            }else{
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return null;
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
     * Transforma el resultado del método obtenerSocioPorID en una Lista de Socios
     * @param socio
     * @return 
     */
    public static List<Socio> buscarPorIDList(Socio socio){
        List<Socio> resultado = new ArrayList<>();
        if(socio != null){
            resultado.add(socio);
            return resultado;
        }
        else{
            return null;
        }
    }
    
    /**
     * Método para obtener un socio por su DNI
     * @param idSocio
     * @return 
     */
    public static List<Socio> obtenerSocioPorDNI(String DNISocio, Boolean actual) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Socio> resultado = new ArrayList<>();
        
        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM socios WHERE dni_soc LIKE ? AND actual_soc = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + DNISocio + "%");
            stmt.setBoolean(2, actual);
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

                resultado.add(new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, provincia.toUpperCase(), numSanciones, cuentaBancaria));
            }else{
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return resultado;
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
        return resultado;
    }
    
    /**
     * Método para obtener un socio por su teléfono
     * @param buscar
     * @return 
     */
    public static List<Socio> obtenerSocioPorTelefono(String buscar, Boolean actual) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Socio> resultado = new ArrayList<>();

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM socios WHERE tlf_soc LIKE \"%?%\" AND actual_soc = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + buscar + "%");
            stmt.setBoolean(2, actual);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_soc");
                String biblioteca = rs.getString("biblioteca_soc");
                String dni = rs.getString("dni_soc");
                String nombre = rs.getString("nombre_soc");
                String apellidos = rs.getString("apellidos_soc");
                String telefono = rs.getString("tlf_soc");
                String email = rs.getString("email_soc");
                String provincia = rs.getString("provincia_soc");
                int numSanciones = rs.getInt("numSanciones_soc");
                String cuentaBancaria = rs.getString("cuentaBancaria_soc");
                boolean pago = rs.getBoolean("pago_soc");

                resultado.add(new Socio(id, biblioteca, dni, nombre, apellidos, telefono, email, pago, provincia.toUpperCase(), numSanciones, cuentaBancaria));
            }else{
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return null;
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
        return resultado;
    }
    
    /**
     * Método para obtener un socio por su Email
     * @param buscar
     * @return 
     */
    public static List<Socio> obtenerSocioPorEmail(String buscar, Boolean actual) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Socio> resultado = new ArrayList<>();

        try {
            conn = BaseDatos.obtenerConnection();
           String sql = "SELECT socios.id_soc,"
                    + " bibliotecas.nombre_biblio,"
                    + " socios.dni_soc,"
                    + " socios.nombre_soc,"
                    + " socios.apellidos_soc,"
                    + " socios.tlf_soc,"
                    + " socios.email_soc,"
                    + " socios.provincia_soc,"
                    + " socios.numSanciones_soc,"
                    + " socios.cuentaBancaria_soc,"
                    + " socios.pago_soc "
                    + "FROM socios, bibliotecas "
                    + "WHERE socios.biblioteca_soc = bibliotecas.id_biblio "
                    + "AND email_soc LIKE ? "
                    + " AND actual_soc = ?"
                    + " ORDER BY id_soc ASC;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + buscar + "%");
            stmt.setBoolean(2, actual);
            rs = stmt.executeQuery();
            int contador = 0;
            while(rs.next()){
                int id = rs.getInt("id_soc");
                String biblioteca = rs.getString("bibliotecas.nombre_biblio");
                String dni = rs.getString("dni_soc");
                String nombre = rs.getString("nombre_soc");
                String apellidos = rs.getString("apellidos_soc");
                String tlf = rs.getString("tlf_soc");
                String email = rs.getString("email_soc");
                String provincia = rs.getString("provincia_soc");
                int numSanciones = rs.getInt("numSanciones_soc");
                String cuentaBancaria = rs.getString("cuentaBancaria_soc");
                boolean pago = rs.getBoolean("pago_soc");

                resultado.add(new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, provincia.toUpperCase(), numSanciones, cuentaBancaria));
                contador++;
            }
            if(contador == 0){
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return null;
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
        };
        return resultado;
    }
    
    /**
     * Método para obtener un socio por su Provincia
     * @param buscar
     * @return 
     */
    public static List<Socio> obtenerSocioPorProvincia(String buscar, Boolean actual) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Socio> resultado = new ArrayList<>();
        try {
            conn = BaseDatos.obtenerConnection();
           String sql = "SELECT socios.id_soc,"
                    + " bibliotecas.nombre_biblio,"
                    + " socios.dni_soc,"
                    + " socios.nombre_soc,"
                    + " socios.apellidos_soc,"
                    + " socios.tlf_soc,"
                    + " socios.email_soc,"
                    + " socios.provincia_soc,"
                    + " socios.numSanciones_soc,"
                    + " socios.cuentaBancaria_soc,"
                    + " socios.pago_soc "
                    + "FROM socios, bibliotecas "
                    + "WHERE socios.biblioteca_soc = bibliotecas.id_biblio "
                    + "AND provincia_soc LIKE ? "
                    + " AND actual_soc = ?"
                    + " ORDER BY id_soc ASC;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + buscar + "%");
            stmt.setBoolean(2, actual);
            rs = stmt.executeQuery();
            int contador = 0;
            while(rs.next()){
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

                resultado.add(new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, provincia.toUpperCase(), numSanciones, cuentaBancaria));
                contador++;
            }
            if(contador == 0){
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return resultado;
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
        };
        return resultado;
    }
    
    
    /**
     * Método para obtener un socio por su Provincia
     * @param buscar
     * @return 
     */
    public static List<Socio> obtenerSocioPorCBancaria(String buscar, Boolean actual) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Socio> resultado = new ArrayList<>();
        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT socios.id_soc,"
                    + " bibliotecas.nombre_biblio,"
                    + " socios.dni_soc,"
                    + " socios.nombre_soc,"
                    + " socios.apellidos_soc,"
                    + " socios.tlf_soc,"
                    + " socios.email_soc,"
                    + " socios.provincia_soc,"
                    + " socios.numSanciones_soc,"
                    + " socios.cuentaBancaria_soc,"
                    + " socios.pago_soc "
                    + "FROM socios, bibliotecas "
                    + "WHERE socios.biblioteca_soc = bibliotecas.id_biblio "
                    + "AND (nombre_soc LIKE ? OR apellidos_soc LIKE ?)"
                    + " AND actual_soc = ?"
                    + " ORDER BY id_soc ASC;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + buscar + "%");
            stmt.setString(2, "%" + buscar + "%");
            stmt.setBoolean(3, actual);
            rs = stmt.executeQuery();
            int contador = 0;
            while(rs.next()){
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

                resultado.add(new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, provincia.toUpperCase(), numSanciones, cuentaBancaria));
                contador++;
            }
            if(contador == 0){
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return resultado;
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
        };
        return resultado;
    }
    
    /**
     * Método para obtener un socio por su nombre y apellidos
     * @param buscar
     * @return 
     */
    public static List<Socio> obtenerSocioPorNombreApellidos(String buscar, Boolean actual) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Socio> resultado = new ArrayList<>();
        try {
            conn = BaseDatos.obtenerConnection();
           /* String sql = "SELECT * FROM socios, bibliotecas"
                    + " WHERE bibliotecas.id_biblio = socios.biblioteca_soc"
                    + " AND provincia_soc = ?";
            */
           String sql = "SELECT socios.id_soc,\n" +
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
                 " FROM socios\n" +
                 " INNER JOIN bibliotecas ON socios.biblioteca_soc = bibliotecas.id_biblio\n" +
                 " WHERE (LOWER(nombre_soc) LIKE LOWER(?) OR LOWER(apellidos_soc) LIKE LOWER(?))" +
                 " AND actual_soc = ?" +
                 " ORDER BY id_soc ASC;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + buscar + "%");
            stmt.setString(2, "%" + buscar + "%");
            stmt.setBoolean(3, actual);
            rs = stmt.executeQuery();
            int contador = 0;
            while(rs.next()){
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

                resultado.add(new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, provincia.toUpperCase(), numSanciones, cuentaBancaria));
                contador++;
            }
            if(contador == 0){
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return resultado;
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
        };
        return resultado;
    } 
        
    /**
     * Método para obtener un socio por su número de sanciones
     * @param buscar
     * @return 
     */
    public static List<Socio> obtenerSocioPorSanciones(int buscar) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Socio> resultado = new ArrayList<>();
        try {
            conn = BaseDatos.obtenerConnection();
           /* String sql = "SELECT * FROM socios, bibliotecas"
                    + " WHERE bibliotecas.id_biblio = socios.biblioteca_soc"
                    + " AND provincia_soc = ?";
            */
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
                " WHERE socios.biblioteca_soc = bibliotecas.id_biblio" +
                " AND numSanciones_soc LIKE ?" +
                " ORDER BY id_soc ASC;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, buscar);
            rs = stmt.executeQuery();
            int contador = 0;
            while(rs.next()){
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

                resultado.add(new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, provincia.toUpperCase(), numSanciones, cuentaBancaria));
                contador++;
            }
            if(contador == 0){
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return resultado;
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
        };
        return resultado;
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

                Socio socio = new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, provincia.toUpperCase(), numSanciones, cuentaBancaria);
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
                " WHERE socios.biblioteca_soc = bibliotecas.id_biblio" +
                " AND actual_soc = 1" +
                " ORDER BY id_soc ASC;";
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

                socioNuevo = new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, provincia.toUpperCase(), numSanciones, cuentaBancaria);
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
    
    /**
     * Nos pasa un listado de socios buscando por nombre
     * @param nombreBuscar
     * @return 
     */
    public static List<Socio> obtenerSociosDNI(String nombreBuscar) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Socio socioNuevo = null;
        List<Socio> listado = new ArrayList<>();
        try {
            conn = BaseDatos.obtenerConnection();
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
                " WHERE socios.biblioteca_soc = bibliotecas.id_biblio" +
                " AND actual_soc = 1"+
                " AND nombre_soc" +
                " ORDER BY id_soc ASC;";
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

                socioNuevo = new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, provincia.toUpperCase(), numSanciones, cuentaBancaria);
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
    
    
    
    /**
     * Nos devuelve una lista de antiguos socios para importar a la ventana
     * @return 
     */
    public static List<Socio> obtenerAntiguosSocios() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Socio socioNuevo = null;
        List<Socio> listado = new ArrayList<>();
        try {
            conn = BaseDatos.obtenerConnection();
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
                " WHERE socios.biblioteca_soc = bibliotecas.id_biblio" +
                " AND actual_soc = 0" +
                " ORDER BY id_soc ASC;";
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

                socioNuevo = new Socio(id, biblioteca, dni, nombre, apellidos, tlf, email, pago, provincia.toUpperCase(), numSanciones, cuentaBancaria);
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
    
    public static void eliminarSocio(int id){
        /*
        BORRADO FISICO ---------------------------------------------------------
        
        Boolean validacion = false;
        Connection conexion = null;
        PreparedStatement prepStat = null;
        ResultSet rs = null;
        try {
            conexion = BaseDatos.obtenerConnection();
            String sql = "DELETE FROM socios WHERE id_soc = ?";
            prepStat = conexion.prepareStatement(sql);
            prepStat.setInt(1, id);
            prepStat.executeUpdate();
            if(Socio.obtenerSocioPorId(id) == null){
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente" , "Borrado correcto", JOptionPane.INFORMATION_MESSAGE);
            }else JOptionPane.showMessageDialog(null, "El usuario no pudo ser borrado" , "Borrado incorrecto", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return validacion;
        */
        
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "UPDATE socios SET actual_soc = 0 WHERE id_soc = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Socio " + id + " eliminado correctamente");
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
     * Edita un socio que le pasamos
     * @param id ID original del usuario
     * @param telefono Nuevo teléfono
     * @param email Nuevo email
     * @param provincia Nueva provincia
     * @param biblioteca Nueva biblioteca
     * @param cBancaria Nueva cuenta bancaria
     */
    public static void actualizarSocio(int id, String nombre, String telefono, String email, String provincia, String biblioteca, String cBancaria){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "UPDATE socios "
                    + "SET tlf_soc = ?,"
                    + "email_soc = ?,"
                    + "cuentaBancaria_soc = ?,"
                    + "provincia_soc = ?,"
                    + "biblioteca_soc = ?"
                    + "  WHERE id_soc = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, telefono);
            stmt.setString(2, email);
            stmt.setString(3, cBancaria);
            stmt.setString(4, provincia);
            stmt.setInt(5, Biblioteca.obtenerIDBibliotecaPorNombre(biblioteca));
            stmt.setInt(6, id);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                String mensaje = "Nuevos datos del usuario:" +
                "\nNombre: " + nombre +
                "\nProvincia: " + provincia + 
                "\nTelefono: " + telefono +
                "\nEmail: " + email + "\n";
                JOptionPane.showMessageDialog(null, mensaje, "Usuario actualizado", JOptionPane.INFORMATION_MESSAGE);
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
}
