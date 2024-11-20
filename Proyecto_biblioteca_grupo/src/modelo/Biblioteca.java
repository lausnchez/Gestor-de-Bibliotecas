/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paula
 */
public class Biblioteca {

    Biblioteca(int id, UBICACION provincia1, String telefono1) {
    }
    
    public enum UBICACION{
        A_CORUÑA,
        ÁLAVA,
        ALBACETE,
        ALICANTE,
        ALMERÍA,
        ASTURIAS,
        ÁVILA,
        BADAJOZ,
        BARCELONA,
        BURGOS,
        CÁDIZ,
        CANTABRIA,
        CASTELLÓN,
        CEUTA,
        CÓRDOBA,
        LA_CORUÑA,
        CUENCA,
        GERONA,
        GRANADA,
        GUADALAJARA,
        GIPUZKOA,
        HUELVA,
        HUESCA,
        ISLAS_BALEARES,
        JAÉN,
        LA_RIOJA,
        LAS_PALMAS,
        LEÓN,
        LLEIDA,
        LUGO,
        MADRID,
        MÁLAGA,
        MURCIA,
        NAVARRA,
        OURENSE,
        PALENCIA,
        PONTEVEDRA,
        SALAMANCA,
        SANTA_CRUZ_DE_TENERIFE,
        SEGOVIA,
        SEVILLA,
        SORIA,
        TARRAGONA,
        TERUEL,
        TOLEDO,
        VALENCIA,
        VALLADOLID,
        VIZCAYA,
        ZAMORA,
        ZARAGOZA
    }
    
    /*
    public enum UbiBiblio {
        MADRID("Madrid"),
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
    */
    
    // Atributos de la clase Biblioteca
    private int idBiblioteca;
    private UBICACION provincia;
    private String telefono;

    public int getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(int idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public UBICACION getProvincia() {
        return provincia;
    }

    public void setProvincia(UBICACION provincia) {
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
                     "VALUES (" + this.idBiblioteca + ", '" + this.provincia+ "', '" + this.telefono + "')";
        BaseDatos.ejecutarUpdate(sql);
    }

    // Método para actualizar los datos de una biblioteca
    public void actualizarBiblioteca() {
        String sql = "UPDATE bibliotecas SET ubi_biblioteca = '" + this.provincia +
                     "', tel_biblioteca = '" + this.telefono + "' WHERE id_biblioteca = " + this.idBiblioteca;
        BaseDatos.ejecutarUpdate(sql);
    }

    /**
     * Método que devuelve la biblioteca por su id
     * @param id
     * @return 
     */
    public static Biblioteca obtenerBibliotecaPorId(int id) {
        String sql = "SELECT * FROM bibliotecas WHERE id_biblioteca = " + id;
        ResultSet rs = BaseDatos.ejecutarSelect(sql);
        try {
            if (rs != null && rs.next()) {
                String provinciaStr = rs.getString("ubi_biblioteca");
                UBICACION provincia = UBICACION.valueOf(provinciaStr.toUpperCase()); // Convertir de String a Enum
                String telefono = rs.getString("tel_biblioteca");
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
                UBICACION provincia = UBICACION.valueOf(provinciaStr.toUpperCase()); // Convertir de String a Enum
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
        return "Biblioteca{id=" + idBiblioteca + ", provincia='" + provincia + "', telefono='" + telefono + "'}";
    }

    // Método principal para probar la clase
    public static void main(String[] args) {
        // Crear una biblioteca
        Biblioteca biblio1 = new Biblioteca(1, UBICACION.MADRID, "912345678");

        // Agregar la biblioteca a la base de datos
        biblio1.agregarBiblioteca();

        // Mostrar la información de la biblioteca
        System.out.println(biblio1.toString());

        // Obtener la biblioteca desde la base de datos
        Biblioteca biblioRecuperada = Biblioteca.obtenerBibliotecaPorId(1);
        if (biblioRecuperada != null) {
            System.out.println("Biblioteca recuperada: " + biblioRecuperada.toString());
        }

        // Actualizar la biblioteca
        biblio1.setTelefono("987654321");
        biblio1.actualizarBiblioteca();

        // Mostrar la biblioteca actualizada
        biblioRecuperada = Biblioteca.obtenerBibliotecaPorId(1);
        if (biblioRecuperada != null) {
            System.out.println("Biblioteca después de la actualización: " + biblioRecuperada.toString());
        }

        // Eliminar la biblioteca
        Biblioteca.eliminarBiblioteca(1);
    }

    public static List<String> recogerNombreBibliotecas(){
        List<String> bibliotecas = new ArrayList<>();
        String sql = "SELECT DISTINCT nombre_biblio FROM bibliotecas.bibliotecas";
        ResultSet rs = BaseDatos.ejecutarSelect(sql);
        try {
            while(rs.next()){
                bibliotecas.add(rs.getString("nombre_biblio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bibliotecas;
    }
    
}
