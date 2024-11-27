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
import java.util.Date;

/**
 *
 * @author l_md1
 */
class Recibo {
    private int id;
    private Socio socio;
    private Biblioteca biblioteca;
    private float cuota;
    private Date fecha;
    private boolean pagado;
    
    // Constructor
    public Recibo(Socio socio, Biblioteca biblioteca, float cuota, Date fecha, boolean pagado) {
        this.id = id;
        this.socio = socio;
        this.biblioteca = biblioteca;
        this.cuota = cuota;
        this.fecha = fecha;
        this.pagado = pagado;
    }

    Recibo(Socio socio, Biblioteca.UBICACION provincia, int idSocio, Date fecha, boolean pagado) {
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

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public float getCuota() {
        return cuota;
    }

    public void setCuota(float cuota) {
        this.cuota = cuota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }


    // Método para obtener los detalles del recibo en formato texto
    public String obtenerDetallesRecibo() {
        String estadoPago = this.pagado ? "Pagado" : "No Pagado";
        return "Recibo: " + this.id + " - Socio: " + this.socio.getNombre()+ " - Biblioteca: " + this.biblioteca.getIdBiblioteca() + " - Cuota: " + this.cuota +" - Fecha: " + this.fecha.toString() + " - Estado: " + estadoPago;
    }

    /**
     * Método para guardar el recibo en la base de datos
     */
    public void guardarRecibo() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "INSERT INTO recibos (id_socio_rec,id_biblioteca, cuota_recib, fech_recib, pagado) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.socio.getId());
             stmt.setInt(1, this.biblioteca.getIdBiblioteca());
            stmt.setFloat(1,this.cuota);
            stmt.setDate(2, new java.sql.Date(this.fecha.getTime()));
            stmt.setBoolean(3, this.pagado);
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
     * Método para obtener un recibo por su ID desde la base de datos
     * @param idRecibo
     * @return 
     */

    public static Recibo obtenerReciboPorId(int idRecibo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Recibo recibo = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM recibos WHERE id_recib = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idRecibo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idSocio = rs.getInt("id_socio_rec");
                int idBiblio = rs.getInt("id_biblioteca");
                float cuota= rs.getFloat("cuota_recib");
                Date fecha = rs.getDate("fech_recib");
                boolean pagado = rs.getBoolean("pagado");
                Socio socio = Socio.obtenerSocioPorId(idSocio);
                Biblioteca biblioteca = Biblioteca.obtenerBibliotecaPorId(idBiblio);
                recibo = new Recibo(socio,biblioteca, cuota, fecha, pagado);
                recibo.setId(idRecibo);
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

        return recibo;
    }
    
    /**
     * Método para actualizar el estado de pago de un recibo en la base de datos
     */
    public void actualizarEstadoPago() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "UPDATE recibos SET pagado = ? WHERE id_recib = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, this.pagado);
            stmt.setInt(2, this.id);
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
     * Método para eliminar el recibo de la base de datos
     */
    public void eliminarRecibo() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "DELETE FROM recibos WHERE id_recib = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id);
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
}
