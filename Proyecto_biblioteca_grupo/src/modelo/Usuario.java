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
import modelo.Biblioteca.UbiBiblio;

/**
 *
 * @author 
 */
public class Usuario {
    public enum TIPO {
        ADMINISTRADOR,
        TRABAJADOR
    }
    private int id;
    private TIPO tipo;
    private String nombre;
    private String password;
    private UbiBiblio biblioteca;

    // Constructor
    public Usuario(int id, TIPO tipo, String nombre, String password, UbiBiblio biblioteca) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.password = password;
        this.biblioteca = biblioteca;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TIPO getTipo() {
        return tipo;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UbiBiblio getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(UbiBiblio biblioteca) {
        this.biblioteca = biblioteca;
    }
    
    
    // Métodos
    
    /**
     * Método de autenticación (verificar usuario y contraseña)
     * @param usuario
     * @param password
     * @return 
     */
    public static boolean autenticarUsuario(String usuario, String password) {
        // Buscar el usuario en la base de datos y verificar la contraseña
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM usuarios WHERE id_us = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Si el usuario y la contraseña coinciden, retornar true
                return true;
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

        return false; // Si no se encuentra el usuario o la contraseña es incorrecta
    }

    /**
     * Método para obtener los detalles de un usuario por su nombre de usuario
     * @param nombreUsuario
     * @return 
     */
    public static Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM usuarios WHERE id_us = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_us");
                String nombre = rs.getString("nombre_us");
                String password = rs.getString("password");
                TIPO tipo = TIPO.valueOf(rs.getString("tipo_us"));
                UbiBiblio biblioteca = UbiBiblio.valueOf(rs.getString("biblio_usu").toUpperCase());

                usuario = new Usuario(id, tipo, nombre,password,biblioteca);
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

        return usuario;
    }

    /**
     * Método para registrar un nuevo usuario
     * @param nuevoUsuario 
     */
    public static void registrarUsuario(Usuario nuevoUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "INSERT INTO usuarios (nombre_us, password, tipo_usu, biblio_usu) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nuevoUsuario.getNombre());
            stmt.setString(3, nuevoUsuario.getPassword());
            stmt.setString(4, nuevoUsuario.getTipo().toString());
            stmt.setString(5, nuevoUsuario.getBiblioteca().toString());

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
     * Método para actualizar la contraseña de un usuario
     * @param idUsuario
     * @param nuevaPassword
     * @return 
     */
    public static boolean actualizarPassword(int idUsuario, String nuevaPassword) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "UPDATE usuarios SET password = ? WHERE id_us = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nuevaPassword);
            stmt.setInt(2, idUsuario);

            int filasActualizadas = stmt.executeUpdate();

            return filasActualizadas > 0; // Si se actualizó, retorna true
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Si ocurrió un error, retorna false
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
     * Método para obtener todos los usuarios (por ejemplo, administradores o administrativos)
     * @param tipoUsuario
     * @return 
     */
    public static List<Usuario> obtenerUsuariosPorTipo(TIPO tipoUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM usuarios WHERE tipo_us = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tipoUsuario.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre_us");
                String usuario = rs.getString("usuario");
                String password = rs.getString("password");
                UbiBiblio biblioteca = UbiBiblio.valueOf(rs.getString("biblio_usu").toUpperCase());
                TIPO tipo = TIPO.valueOf(rs.getString("tipo_us"));

                Usuario nuevoUsuario = new Usuario(id, tipo, nombre, password, biblioteca);
                usuarios.add(nuevoUsuario);
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

        return usuarios;
    }

    /**
     * Método para eliminar un usuario por su ID
     * @param idUsuario
     * @return 
     */
    public static boolean eliminarUsuario(int idUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "DELETE FROM usuarios WHERE id_us = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);

            int filasEliminadas = stmt.executeUpdate();
            return filasEliminadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
