/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import modelo.BaseDatos; // Importa tu clase de conexión
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Paula
 */
public class PruebaConexion {
  
    public static void main(String[] args) {
        try {
            // Inicializar conexión
            BaseDatos bd = new BaseDatos();

            // Consulta de prueba
            String sql = "SELECT NOW() AS fecha_hora_actual";
            ResultSet rs = BaseDatos.ejecutarSelect(sql);

            // Mostrar resultados
            if (rs.next()) {
                System.out.println("Conexión exitosa!");
                System.out.println("Fecha y hora actual del servidor MySQL: " + rs.getString("fecha_hora_actual"));
            } else {
                System.out.println("No se obtuvo respuesta de la base de datos.");
            }

            // Cerrar el ResultSet
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.err.println("Error al intentar conectarse a la base de datos:");
            e.printStackTrace();
        }
    }
}

