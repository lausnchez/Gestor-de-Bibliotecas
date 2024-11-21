/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.BaseDatos;

/**
 *
 * @author Paula
 */
public class ControllerUtils {
    
    /**
     * Método para obtener el ID de la biblioteca a partir del nombre de la provincia
     **/
    public static int idbiblioteca(String provincia) {
        int idBiblioteca = -1;  // Valor predeterminado para indicar que no se encontró

        String sql = "SELECT id_biblio FROM bibliotecas WHERE provincias_biblio = ?"; 

        try (Connection conn = BaseDatos.obtenerConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, provincia);  

            ResultSet rs = stmt.executeQuery();

            // Si se encuentra la provincia, se obtiene el ID
            if (rs.next()) {
                idBiblioteca = rs.getInt("id_biblio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idBiblioteca; 
    }
}

