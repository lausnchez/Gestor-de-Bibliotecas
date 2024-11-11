/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author 
 */
public class Sancion {
    private int id;
    private Socio socio;
    private Date fechaSancion;
    private Date fechaFinSancion;
    private String motivo;
    private float coste;
    private boolean cumplida;

    // Constructor
    public Sancion(int id, Socio socio, Date fechaSancion, Date fechaFinSancion, String motivo,float coste, boolean cumplida) {
        this.id = id;
        this.socio = socio;
        this.fechaSancion = fechaSancion;
        this.fechaFinSancion = fechaFinSancion;
        this.motivo = motivo;
        this.coste = coste;
        this.cumplida = cumplida;
    }

    Sancion(int idSancion, Socio socio, Date fechaSancion, Date fechaFinSancion, String motivo, float coste) {
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

    public Date getFechaSancion() {
        return fechaSancion;
    }

    public void setFechaSancion(Date fechaSancion) {
        this.fechaSancion = fechaSancion;
    }

    public Date getFechaFinSancion() {
        return fechaFinSancion;
    }

    public void setFechaFinSancion(Date fechaFinSancion) {
        this.fechaFinSancion = fechaFinSancion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public float getCoste() {
        return coste;
    }

    public void setCoste(float coste) {
        this.coste = coste;
    }

    public boolean isCumplida() {
        return cumplida;
    }

    public void setCumplida(boolean cumplida) {
        this.cumplida = cumplida;
    }
   
    
    

    // Método para obtener una sanción por su ID
    public static Sancion obtenerSancionPorId(int idSancion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Sancion sancion = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM sanciones WHERE id_sanc = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idSancion);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idSocio = rs.getInt("id_socio");
                Date fechaSancion = rs.getDate("fech_sanc");
                Date fechaFinSancion = rs.getDate("fech_fin_sancion");
                String motivo = rs.getString("tipo_sanc");
                float coste = rs.getFloat("coste_sanc");
                boolean cumplida = rs.getBoolean("estado_sanc");

                Socio socio = Socio.obtenerSocioPorId(idSocio);

                sancion = new Sancion(idSancion, socio, fechaSancion, fechaFinSancion, motivo, coste);
                sancion.setCumplida(cumplida);
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

        return sancion;
    }

    // Método para obtener todas las sanciones no cumplidas de un socio
    public static List<Sancion> obtenerSancionesNoCumplidas(Socio socio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Sancion> sanciones = new ArrayList<>();

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM sanciones WHERE id_socio = ? AND cumplida = false";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, socio.getId());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idSancion = rs.getInt("id_sanc");
                Date fechaSancion = rs.getDate("fech_sanc");
                Date fechaFinSancion = rs.getDate("fech_fin_sanc");
                String motivo = rs.getString("tipo_sanc");
                float coste = rs.getFloat("coste_sanc");
                boolean cumplida = rs.getBoolean("estado_sanc");

                Sancion sancion = new Sancion(idSancion, socio, fechaSancion, fechaFinSancion, motivo, coste);
                sancion.setCumplida(cumplida);
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

    // Método para marcar una sanción como cumplida
    public static void marcarSancionCumplida(int idSancion) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "UPDATE sanciones SET estado_sanc = true WHERE id_sanc = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idSancion);

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
