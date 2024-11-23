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

    public enum UBICACION {
MADRID("Madrid"),
    BARCELONA("Barcelona"),
    VALENCIA("Valencia"),
    SEVILLA("Sevilla"),
    ZARAGOZA("Zaragoza"),
    MÁLAGA("Málaga"),
    ALICANTE("Alicante"),
    MURCIA("Murcia"),
    GRANADA("Granada"),
    VALLADOLID("Valladolid"),
    BURGOS("Burgos"),
    CÓRDOBA("Córdoba"),
    CÁDIZ("Cádiz"),
    SALAMANCA("Salamanca"),
    TOLEDO("Toledo"),
    CANTABRIA("Cantabria"),
    LA_RIOJA("La Rioja"),
    NAVARRA("Navarra"),
    ARAGÓN("Aragón"),
    ASTURIAS("Asturias"),
    BALEARES("Baleares");

        private final String nombre;

        UBICACION(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }

    private int idBiblioteca;
    private UBICACION provincia;
    private String telefono;

    public Biblioteca(int id, UBICACION provincia, String telefono) {
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
    
    
    public static List<Biblioteca> obtenerTodasLasBibliotecas() {
    List<Biblioteca> bibliotecas = new ArrayList<>();
    String sql = "SELECT * FROM bibliotecas";
    ResultSet rs = BaseDatos.ejecutarSelect(sql);
    try {
        while (rs != null && rs.next()) {
            int id = rs.getInt("id_biblio");
            String provinciaStr = rs.getString("provincias_biblio");
            
            try {
                // Transformar el valor a un formato uniforme (mayúsculas) y reemplazar espacios por guiones bajos
                provinciaStr = provinciaStr.toUpperCase().replace(" ", "_");  
                UBICACION provincia = UBICACION.valueOf(provinciaStr);  // Convertir a valor del enum
                
                String telefono = rs.getString("telefono_biblio");
                bibliotecas.add(new Biblioteca(id, provincia, telefono));
            } catch (IllegalArgumentException e) {
                System.out.println("Provincia desconocida: " + provinciaStr);
                // Si la provincia no es válida, puedes asignar un valor por defecto o simplemente omitir el registro
                // En este ejemplo, asignamos una provincia por defecto:
                UBICACION provincia = UBICACION.MADRID; // O alguna provincia que elijas por defecto
                String telefono = rs.getString("telefono_biblio");
                bibliotecas.add(new Biblioteca(id, provincia, telefono));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return bibliotecas;
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
                int id = rs.getInt("id_biblio");
                String provinciaStr = rs.getString("provincias_biblio");
                UBICACION provincia = UBICACION.valueOf(provinciaStr.toUpperCase()); // Convertir de String a Enum
                String telefono = rs.getString("telefono_biblio");
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

}



