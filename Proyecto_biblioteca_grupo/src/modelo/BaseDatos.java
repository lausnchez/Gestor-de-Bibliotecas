/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author
 */
public class BaseDatos {
    // Parámetros de conexión
	private static final String DB = "Gestor de Bibliotecas";

    private static final String URL = "jdbc:mysql://localhost:3306/bibliotecas";
    private static final String USUARIO = "root"; // Cambiar
    private static final String CONTRASENA = "damdaw"; // Cambiar
    
    public static Connection conexion = null;
    public static Statement miStatement = null;
    
    // Métodos
    /**
     * Conecta la base de datos y los drivers JDBC de SQL al programa 
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws Exception
     */
    public BaseDatos() {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if(obtenerConnection() != null) {
                    miStatement = conexion.createStatement();
                    System.out.println("Conexión a la base de datos correcta");
            }else System.out.println("Conexión a la base de datos fallida");
        }
    	catch (ClassNotFoundException e) {
                System.err.println("Clase no encontrada");
        e.printStackTrace();
        } catch (SQLException e) {
                System.err.println("Error de SQL");
                e.printStackTrace();
        } catch (Exception e) {
                System.err.println("Error genérico");
                e.printStackTrace();
        }
    }
    
    /**
     * Método para comprobar la conexión a la base de datos
     * @return
     * @throws SQLException 
     */
    public static Connection obtenerConnection() throws SQLException {
        try {
            // Establece la conexión con la base de datos
            Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            return conn;
        } catch (SQLException e) {
            throw new SQLException("Error al conectar con la base de datos", e);
        }
    }
 

    /**
     * Método para cerrar una conexión y los recursos asociados
     * @param conexion 
     */
    public static void cerrarConnection(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Método para ejecutar una consulta de tipo UPDATE, DELETE, INSERT
     * @param sql 
     */
    public static void ejecutarUpdate(String sql) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Obtener conexión
            conn = obtenerConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                cerrarConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para ejecutar una consulta SELECT
     * @param sql 
     */
    public static ResultSet ejecutarSelect(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Obtener la conexión
            conn = obtenerConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Método para realizar una transacción con múltiples consultas
     * @param sqls 
     */
    public static void realizarTransaccion(String[] sqls) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Iniciar la transacción
            conn = obtenerConnection();
            conn.setAutoCommit(false);  // Desactivar autocommit para manejar la transacción manualmente
            stmt = conn.createStatement();

            // Ejecutar las consultas
            for (String sql : sqls) {
                stmt.executeUpdate(sql);
            }

            // Confirmar la transacción
            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();  // Si hay un error, deshacer los cambios
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                cerrarConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
