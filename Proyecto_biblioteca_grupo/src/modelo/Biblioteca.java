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
public class Biblioteca {

    public enum UbiBiblio {
        Madrid("Madrid"),
        BARCELONA("Barcelona"),
        VALENCIA("Valencia"),
        SEVILLA("Sevilla"),
        ZARAGOZA("Zaragoza"),
        MALAGA("Málaga"),
        CORDOBA("Córdoba"),
        ALMERIA("Almería"),
        TOLEDO("Toledo"),
        BILBAO("Bilbao"),
        CEUTA("Ceuta"),
        MELILLA("Melilla");

        private final String nombre;

        UbiBiblio(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }

    private int idBiblioteca;
    private UbiBiblio provincia;
    private String telefono;

    public Biblioteca(int id, UbiBiblio provincia, String telefono) {
        this.idBiblioteca = id;
        this.provincia = provincia;
        this.telefono = telefono;
    }

    public int getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(int idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public UbiBiblio getProvincia() {
        return provincia;
    }

    public void setProvincia(UbiBiblio provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
// Método para agregar una biblioteca a la base de datos
    public void agregarBiblioteca() {
        String sql = "INSERT INTO bibliotecas (id_biblioteca, ubi_biblioteca, tel_biblioteca) " +
                     "VALUES (" + this.idBiblioteca + ", '" + this.provincia.getNombre() + "', '" + this.telefono + "')";
        BaseDatos.ejecutarUpdate(sql);
    }

    // Método para actualizar los datos de una biblioteca
    public void actualizarBiblioteca() {
        String sql = "UPDATE bibliotecas SET ubi_biblioteca = '" + this.provincia.getNombre() +
                     "', tel_biblioteca = '" + this.telefono + "' WHERE id_biblioteca = " + this.idBiblioteca;
        BaseDatos.ejecutarUpdate(sql);
    }
    
    
public int obtenerIdBibliotecaPorProvincia(String provincia) {

    // Consulta SQL para obtener el ID de la biblioteca a partir de la provincia
    String sql = "SELECT idBibliotecas FROM bibliotecas WHERE provincia = ?";

    try (Connection conn = BaseDatos.obtenerConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, provincia);  // Establecer la provincia seleccionada

        // Ejecutar la consulta
        ResultSet rs = stmt.executeQuery();

        // Si se encuentra una biblioteca con esa provincia, obtener su ID
        if (rs.next()) {
            idBiblioteca = rs.getInt("idBibliotecas");
        }
    } catch (SQLException e) {
        e.printStackTrace();  // Es útil para depurar el error
    }

    return idBiblioteca;  // Retorna el ID de la biblioteca o 0 si no se encontró
}
    /**
     * Método que devuelve la biblioteca por su id
     * @param id
     * @return 
     */
    public static Biblioteca obtenerBibliotecaPorId(int id) {
        String sql = "SELECT * FROM bibliotecas WHERE idBibliotecas = " + id;
        ResultSet rs = BaseDatos.ejecutarSelect(sql);
        try {
            if (rs != null && rs.next()) {
                String provinciaStr = rs.getString("provincia");
                UbiBiblio provincia = UbiBiblio.valueOf(provinciaStr.toUpperCase()); // Convertir de String a Enum
                String telefono = rs.getString("telefono");
                return new Biblioteca(id, provincia, telefono);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra la biblioteca
    }

    /**
     * Método para obtener todas las Bibliotecas
     * Devuelve las bibliotecas
     * @return 
     */
    public static List<Biblioteca> obtenerTodasLasBibliotecas() {
        List<Biblioteca> bibliotecas = new ArrayList<>();
        String sql = "SELECT * FROM bibliotecas";
        ResultSet rs = BaseDatos.ejecutarSelect(sql);
        try {
            while (rs != null && rs.next()) {
                int id = rs.getInt("id_biblioteca");
                String provinciaStr = rs.getString("ubi_biblioteca");
                UbiBiblio provincia = UbiBiblio.valueOf(provinciaStr.toUpperCase()); // Convertir de String a Enum
                String telefono = rs.getString("tel_biblioteca");
                bibliotecas.add(new Biblioteca(id, provincia, telefono));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bibliotecas;
    }

    /**
     * Método que elimina la biblioteca
     * @param id 
     */
    public static void eliminarBiblioteca(int id) {
        String sql = "DELETE FROM bibliotecas WHERE id_biblioteca = " + id;
        BaseDatos.ejecutarUpdate(sql);
    }

    // Método toString para mostrar la información de la biblioteca
    @Override
    public String toString() {
        return "Biblioteca{id=" + idBiblioteca + ", provincia='" + provincia.getNombre() + "', telefono='" + telefono + "'}";
    }
}
