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
import java.util.*;
import java.text.*;
import java.util.Date;
import modelo.Biblioteca.UbiBiblio;
/**
 *
 * @author 
 */
public class InformeMensual {
    private int id;
    private Date fechaInforme;
    private List<Recibo> recibos;
    private List<Sancion> sanciones;
    private List<Libro> librosPendientesDevolucion;
    
    // Constructor
    public InformeMensual(int id, Date fechaInforme, List<Recibo> recibos, List<Sancion> sanciones, List<Libro> librosPendientesDevolucion) {
        this.id = id;
        this.fechaInforme = fechaInforme;
        this.recibos = recibos;
        this.sanciones = sanciones;
        this.librosPendientesDevolucion = librosPendientesDevolucion;
    }

    private InformeMensual(int i, Date fechaInforme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInforme() {
        return fechaInforme;
    }

    public void setFechaInforme(Date fechaInforme) {
        this.fechaInforme = fechaInforme;
    }

    public List<Recibo> getRecibos() {
        return recibos;
    }

    public void setRecibos(List<Recibo> recibos) {
        this.recibos = recibos;
    }

    public List<Sancion> getSanciones() {
        return sanciones;
    }

    public void setSanciones(List<Sancion> sanciones) {
        this.sanciones = sanciones;
    }

    public List<Libro> getLibrosPendientesDevolucion() {
        return librosPendientesDevolucion;
    }

    public void setLibrosPendientesDevolucion(List<Libro> librosPendientesDevolucion) {
        this.librosPendientesDevolucion = librosPendientesDevolucion;
    }
    
    // Métodos
    /**
     * Método para generar el informe mensual
     * @param idBiblioteca
     * @return 
     */
    public static InformeMensual generarInforme(int idBiblioteca) {
    // Crear una nueva instancia de InformeMensual
    Date fechaInforme = new Date(); // Esto obtiene la fecha actual
    InformeMensual informe = new InformeMensual(0, fechaInforme);
  
        // Obtener los recibos de todos los socios de la biblioteca
        informe.setRecibos(obtenerRecibosBiblioteca(idBiblioteca));

        // Obtener las sanciones no cumplidas de los socios
        informe.setSanciones(obtenerSancionesBiblioteca(idBiblioteca));

        // Obtener los libros pendientes de devolución (es decir, los libros que están fuera del plazo)
        informe.setLibrosPendientesDevolucion(obtenerLibrosPendientesDevolucion(idBiblioteca));

        return informe;
    }

    /**
     * Método para obtener los recibos de los socios de una biblioteca
     * @param idBiblioteca
     * @return 
     */
    private static List<Recibo> obtenerRecibosBiblioteca(int idBiblioteca) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Recibo> recibos = new ArrayList<>();

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM recibos WHERE id_biblioteca = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idBiblioteca);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idSocio = rs.getInt("id_socio");
        // Obtener la provincia de la biblioteca (la provincia está en la tabla "bibliotecas")
                String sqlProvincia = "SELECT ubi_biblioteca FROM bibliotecas WHERE id_biblioteca = ?";
                PreparedStatement stmtProvincia = conn.prepareStatement(sqlProvincia);
                stmtProvincia.setInt(1, idBiblioteca);
                ResultSet rsProvincia = stmtProvincia.executeQuery();
                UbiBiblio provincia = null;
                if (rsProvincia.next()) {
                    String provinciaStr = rsProvincia.getString("ubi_biblioteca");
                    provincia = UbiBiblio.valueOf(provinciaStr.toUpperCase()); // Convertir de String a Enum
                }
                rsProvincia.close();  // Cerrar el ResultSet de la provincia

                Date fecha = rs.getDate("fech_recib");
                boolean pagado = rs.getBoolean("cuota_recib");
                Socio socio = Socio.obtenerSocioPorId(idSocio);
                Recibo recibo = new Recibo(socio, provincia, idSocio, fecha, pagado);
                recibos.add(recibo);
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

        return recibos;
    }

    /**
     *  Método para obtener las sanciones no cumplidas de los socios de una biblioteca
     * @param idBiblioteca
     * @return 
     */
    private static List<Sancion> obtenerSancionesBiblioteca(int idBiblioteca) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Sancion> sanciones = new ArrayList<>();

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM sanciones WHERE estado_sanc = false AND id_biblioteca = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idBiblioteca);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idSancion = rs.getInt("id_sanc");
                String motivo = rs.getString("tipo_sanc");
                Date fechaSancion = rs.getDate("fech_sanc");
                Date fechaFinSancion = rs.getDate("fecha_fin_sancion");
                float coste = rs.getFloat("coste_sanc");
                boolean estado= rs.getBoolean("estado_sanc");
                int idSocio = rs.getInt("id_socio");
                Socio socio = Socio.obtenerSocioPorId(idSocio);
                Sancion sancion = new Sancion(idSancion, socio, fechaSancion, fechaFinSancion, motivo, coste);
                sanciones.add(sancion);
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

        return sanciones;
    }

    /**
     * Método para obtener los libros pendientes de devolución
     * @param idBiblioteca
     * @return 
     */
    private static List<Libro> obtenerLibrosPendientesDevolucion(int idBiblioteca) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Libro> libros_pendientes_devolucion = new ArrayList<>();

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM prestamos WHERE id_biblioteca = ? AND fecha_dev IS NULL AND fecha_prest < ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idBiblioteca);

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, -15); // Se consideran los libros con fecha de entrega hace más de 15 días
            Date fechaLimite = cal.getTime();
            stmt.setDate(2, new java.sql.Date(fechaLimite.getTime()));

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idLibro = rs.getInt("id_lib");
                Libro libro = Libro.obtenerLibroPorId(idLibro);
                libros_pendientes_devolucion.add(libro);
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

        return libros_pendientes_devolucion;
    }

    /**
     * Método para mostrar el informe en consola
     */
    public void mostrarInforme() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Informe Mensual - Fecha: " + sdf.format(fechaInforme));
        System.out.println("\nRecibos de socios:");
        for (Recibo recibo : recibos) {
            System.out.println("Socio: " + recibo.getSocio().getNombre() + " - Pagado: " + recibo.isPagado());
        }

        System.out.println("\nSanciones no cumplidas:");
        for (Sancion sancion : sanciones) {
            System.out.println("Socio: " + sancion.getSocio().getNombre() + " - Motivo: " + sancion.getMotivo());
        }

        System.out.println("\nLibros pendientes de devolución:");
        for (Libro libro : librosPendientesDevolucion) {
            System.out.println("Libro: " + libro.getTitulo() + " - Autor: " + libro.getAutor());
        }
    }
    
}
