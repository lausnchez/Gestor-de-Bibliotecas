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
import java.util.List;

/**
 *
 * @author Paula
 */
public class Bibliotecario {
    private int idTrab;
    private int bibliotecaTrab;
    private String dniTrab;
    private String nombreTrab;
    private String apellidosTrab;
    private String usuarioTrab;
    private String telefonoTrab;
    private String emailTrab;

    // Constructores
    public Bibliotecario(int idTrab, int bibliotecaTrab, String dniTrab, String nombreTrab, 
                         String apellidosTrab, String usuarioTrab, String telefonoTrab, String emailTrab) {
        this.idTrab = idTrab;
        this.bibliotecaTrab = bibliotecaTrab;
        this.dniTrab = dniTrab;
        this.nombreTrab = nombreTrab;
        this.apellidosTrab = apellidosTrab;
        this.usuarioTrab = usuarioTrab;
        this.telefonoTrab = telefonoTrab;
        this.emailTrab = emailTrab;
    }

    public Bibliotecario() {}

    // Getters & Setters

    public int getIdTrab() {
        return idTrab;
    }

    public void setIdTrab(int idTrab) {
        this.idTrab = idTrab;
    }

    public int getBibliotecaTrab() {
        return bibliotecaTrab;
    }

    public void setBibliotecaTrab(int bibliotecaTrab) {
        this.bibliotecaTrab = bibliotecaTrab;
    }

    public String getDniTrab() {
        return dniTrab;
    }

    public void setDniTrab(String dniTrab) {
        this.dniTrab = dniTrab;
    }

    public String getNombreTrab() {
        return nombreTrab;
    }

    public void setNombreTrab(String nombreTrab) {
        this.nombreTrab = nombreTrab;
    }

    public String getApellidosTrab() {
        return apellidosTrab;
    }

    public void setApellidosTrab(String apellidosTrab) {
        this.apellidosTrab = apellidosTrab;
    }

    public String getUsuarioTrab() {
        return usuarioTrab;
    }

    public void setUsuarioTrab(String usuarioTrab) {
        this.usuarioTrab = usuarioTrab;
    }

    public String getTelefonoTrab() {
        return telefonoTrab;
    }

    public void setTelefonoTrab(String telefonoTrab) {
        this.telefonoTrab = telefonoTrab;
    }

    public String getEmailTrab() {
        return emailTrab;
    }

    public void setEmailTrab(String emailTrab) {
        this.emailTrab = emailTrab;
    }
    
    /**
     * 
     * @return todos los bibliotecarios
     */
     public static List<Bibliotecario> obtenerTodos() {
    List<Bibliotecario> listaBibliotecarios = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = BaseDatos.obtenerConnection();  // Obtener la conexión

        if (conn == null) {
            throw new SQLException("No se pudo obtener la conexión a la base de datos.");
        }

        // Consulta SQL
        String sql = "SELECT id_trab, biblioteca_trab, dni_trab, nombre_trab, apellidos_trab, usuario_trab, telefono_trab, email_trab FROM bibliotecario";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()) {
            Bibliotecario biblio = new Bibliotecario();
            biblio.setIdTrab(rs.getInt("id_trab"));
            biblio.setBibliotecaTrab(rs.getInt("biblioteca_trab"));
            biblio.setDniTrab(rs.getString("dni_trab"));
            biblio.setNombreTrab(rs.getString("nombre_trab"));
            biblio.setApellidosTrab(rs.getString("apellidos_trab"));
            biblio.setUsuarioTrab(rs.getString("usuario_trab"));
            biblio.setTelefonoTrab(rs.getString("telefono_trab"));
            biblio.setEmailTrab(rs.getString("email_trab"));

            // Añadir a la lista
            listaBibliotecarios.add(biblio);
        }
    } catch (SQLException e) {
        e.printStackTrace();  // Muestra el error si ocurre
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return listaBibliotecarios;
}
     
     /**
      * 
      * @param
      * @return devuelñve bibliotecario por id
      */
     public static Bibliotecario obtenerBibliotecarioPorId(int id) {
    Bibliotecario bibliotecario = null;
    String sql = "SELECT * FROM bibliotecario WHERE id_trab = ?";
    
    try (Connection conn = BaseDatos.obtenerConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        // Establecer el parámetro en la consulta SQL
        stmt.setInt(1, id);
        
        // Ejecutar la consulta y obtener el resultado
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Crear el bibliotecario y llenar los datos
                bibliotecario = new Bibliotecario();
                bibliotecario.setIdTrab(rs.getInt("id_trab"));
                bibliotecario.setBibliotecaTrab(rs.getInt("biblioteca_trab"));
                bibliotecario.setDniTrab(rs.getString("dni_trab"));
                bibliotecario.setNombreTrab(rs.getString("nombre_trab"));
                bibliotecario.setApellidosTrab(rs.getString("apellidos_trab"));
                bibliotecario.setUsuarioTrab(rs.getString("usuario_trab"));
                bibliotecario.setTelefonoTrab(rs.getString("telefono_trab"));
                bibliotecario.setEmailTrab(rs.getString("email_trab"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return bibliotecario;  // Devolver el objeto o null si no se encontró
}
     
     /**
      * elimina un bibliotecario
      * @param id 
      */
    public static void eliminarBibliotecario(int id) {
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conn = BaseDatos.obtenerConnection();
        String sql = "DELETE FROM bibliotecario WHERE id_trab = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
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
    
    public static boolean actualizarBibliotecario(Bibliotecario bibliotecario) {
    String sql = "UPDATE bibliotecario SET nombre_trab = ?, apellidos_trab = ?, email_trab = ?, dni_trab=? WHERE id_trab = ?";
    try (Connection conn = BaseDatos.obtenerConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, bibliotecario.getNombreTrab());
        stmt.setString(2, bibliotecario.getApellidosTrab());
        stmt.setString(3, bibliotecario.getEmailTrab());
        stmt.setInt(4, bibliotecario.getIdTrab());
        stmt.setString(5, bibliotecario.getDniTrab());

        return stmt.executeUpdate() > 0; // Si se actualizó al menos una fila, retorna true
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    /**
     * 
     * @param devuelve una lista pasandole un parámetro
     * @return 
     */
    
    public static List<Bibliotecario> buscarPorParametro(String parametro) {
    List<Bibliotecario> lista = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = BaseDatos.obtenerConnection();
        String sql = "SELECT * FROM bibliotecario WHERE nombre_trab LIKE ? OR apellidos_trab LIKE ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + parametro + "%");
        stmt.setString(2, "%" + parametro + "%");
        rs = stmt.executeQuery();

        while (rs.next()) {
            Bibliotecario biblio = new Bibliotecario();
            biblio.setIdTrab(rs.getInt("id_trab"));
            biblio.setBibliotecaTrab(rs.getInt("biblioteca_trab"));
            biblio.setDniTrab(rs.getString("dni_trab"));
            biblio.setNombreTrab(rs.getString("nombre_trab"));
            biblio.setApellidosTrab(rs.getString("apellidos_trab"));
            biblio.setUsuarioTrab(rs.getString("usuario_trab"));
            biblio.setTelefonoTrab(rs.getString("telefono_trab"));
            biblio.setEmailTrab(rs.getString("email_trab"));
            lista.add(biblio);
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
    return lista;
}
}
