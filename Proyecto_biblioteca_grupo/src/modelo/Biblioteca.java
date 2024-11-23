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
import javax.swing.JOptionPane;

/**
 *
 * @author Paula
 */
public class Biblioteca {    
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

    // Atributos de la clase Biblioteca
    //--------------------------------------------------------------------------
    private int idBiblioteca;
    private String nombre;
    private String provincia;
    private String ciudad;
    private String calle;
    private String telefono;
    private String email;

    // Contructores
    //--------------------------------------------------------------------------
    public Biblioteca(){
        this.idBiblioteca = -1;
        this.nombre = "";
        this.provincia = "";
        this.ciudad = "";
        this.calle = "";
        this.telefono = "";
        this.email = "";
    }

    public Biblioteca(int idBiblioteca, String nombre, String provincia, String ciudad, String calle, String telefono, String email) {
        this.idBiblioteca = idBiblioteca;
        this.nombre = nombre;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.calle = calle;
        this.telefono = telefono;
        this.email = email;
    }
    
    public int getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(int idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        
// Método para agregar una biblioteca a la base de datos
    public void agregarBiblioteca() {
        String sql = "INSERT INTO bibliotecas (id_biblioteca, ubi_biblioteca, tel_biblioteca) " +
                     "VALUES (" + this.idBiblioteca + ", '" + this.provincia+ "', '" + this.telefono + "')";
        BaseDatos.ejecutarUpdate(sql);
    }

    // Método para actualizar los datos de una biblioteca
    /*
    public void actualizarBiblioteca() {
        String sql = "UPDATE bibliotecas SET ubi_biblioteca = '" + this.provincia +
                     "', tel_biblioteca = '" + this.telefono + "' WHERE id_biblioteca = " + this.idBiblioteca;
        BaseDatos.ejecutarUpdate(sql);
    }  */
  
    /**
     * Método que devuelve la biblioteca por su id
     * @param id
     * @return 
     */
    
    public static Biblioteca obtenerBibliotecaPorId(int id) {
        String sql = "SELECT * FROM bibliotecas WHERE id_biblio = " + id;
        ResultSet rs = BaseDatos.ejecutarSelect(sql);
        try {
            if (rs != null && rs.next()) {
                int idEncontrado = rs.getInt("id_biblio");
                String nombre = rs.getString("nombre_biblio");
                String provincia = rs.getString("provincias_biblio");
                String ciudad = rs.getString("ciudad_biblio");
                String calle = rs.getString("calle_biblio");
                String telefono = rs.getString("telefono_biblio");
                String email = rs.getString("email_biblio");

                return new Biblioteca(idEncontrado, nombre, provincia, ciudad, calle, telefono, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra la biblioteca
    }
    
    
    /**
     * Nos pide un nombre de una biblioteca y nos devuelve su ID
     * @param nombreBiblioteca
     * @return 
     */
    public static int obtenerIDBibliotecaPorNombre(String nombreBiblioteca){
        int id = -1;
        String sql = "SELECT id_biblio FROM bibliotecas WHERE nombre_biblio LIKE \"" + nombreBiblioteca + "\"";
        ResultSet rs = BaseDatos.ejecutarSelect(sql);
        try {
            while(rs != null && rs.next()){
                id = rs.getInt("id_biblio");
            }
        } catch (SQLException e) {
        }
        return id;
    }
    
    /**
     * Método para obtener todas las Bibliotecas
     * Devuelve las bibliotecas
     * @return 
     */
    /*
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
    */
    
    /**
     * Método que elimina la biblioteca
     * @param id 
     */
    /*public static void eliminarBiblioteca(int id) {
        String sql = "DELETE FROM bibliotecas WHERE id_biblioteca = " + id;
        BaseDatos.ejecutarUpdate(sql);
    }*/

    // Método toString para mostrar la información de la biblioteca
    @Override
    public String toString() {
        return "Biblioteca{id=" + idBiblioteca + ", provincia='" + provincia + "', telefono='" + telefono + "'}";
    }


    /**
     * Nos devuelve una lista de Strings para implementar a los combobox
     * @return 
     */
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
    
    /**
     * Nos devuelve las bibliotecas que hay en una provincia escogida
     * @return 
     */
    public static List<String> recogerBibliotecasPorProvincia(String provincia){
        List<String> bibliotecas = new ArrayList<>();
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT DISTINCT nombre_biblio FROM bibliotecas.bibliotecas WHERE provincias_biblio LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, provincia);
            rs = stmt.executeQuery();
            while(rs.next()){
                bibliotecas.add(rs.getString("nombre_biblio"));
            }
        } catch (SQLException e) {
            System.err.println("Error al recoger bibliotecas");
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
        return bibliotecas;
    }
    
    /**
     * Método que nos devuelve un listado de todas las bibliotecas de la
     * base de datos
     * @return
     */
    public static List<Biblioteca> obtenerBibliotecas() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Biblioteca biblio = null;
        List<Biblioteca> listado = new ArrayList<>();
        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * from Bibliotecas;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id_biblio");
                String nombre = rs.getString("nombre_biblio");
                String provincia = rs.getString("provincias_biblio");
                String ciudad = rs.getString("ciudad_biblio");
                String calle = rs.getString("calle_biblio");
                String telefono = rs.getString("telefono_biblio");
                String email = rs.getString("email_biblio");

                biblio = new Biblioteca(id, nombre, provincia, ciudad, calle, telefono, email);
                listado.add(biblio);
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
        return listado;
    }
    
    /**
     * Método para obtener una biblioteca por su id
     * @param idSocio
     * @return 
     */
    public static List<Biblioteca> obtenerBibliotecasPorID(int ID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Biblioteca> resultado = new ArrayList<>();
        
        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM bibliotecas WHERE id_biblio LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + ID + "%");
            rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id_biblio");
                String nombre = rs.getString("nombre_biblio");
                String provincia = rs.getString("provincias_biblio");
                String ciudad = rs.getString("ciudad_biblio");
                String calle = rs.getString("calle_biblio");
                String tlf = rs.getString("telefono_biblio");
                String email = rs.getString("email_biblio");
                resultado.add(new Biblioteca(id, nombre, provincia, ciudad, calle, tlf, email));
            }
            if(resultado.size() == 0){
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return resultado;
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
        return resultado;
    }
    
    /**
     * Método para obtener una biblioteca por su id
     * @param ID
     * @return 
     */
    public static Biblioteca obtenerBibliotecaUnicaID(int ID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Biblioteca biblio = null;
        
        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM bibliotecas WHERE id_biblio LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + ID + "%");
            rs = stmt.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id_biblio");
                String nombre = rs.getString("nombre_biblio");
                String provincia = rs.getString("provincias_biblio");
                String ciudad = rs.getString("ciudad_biblio");
                String calle = rs.getString("calle_biblio");
                String tlf = rs.getString("telefono_biblio");
                String email = rs.getString("email_biblio");
                biblio = new Biblioteca(id, nombre, provincia, ciudad, calle, tlf, email);
            }
            if(biblio == null){
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return null;
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
        return biblio;
    }
    
    /**
     * Método para obtener una biblioteca por su nombre
     * @param busqueda
     * @return 
     */
    public static List<Biblioteca> obtenerBibliotecaPorNombre(String busqueda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Biblioteca> resultado = new ArrayList<>();
        
        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM bibliotecas WHERE nombre_biblio LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + busqueda + "%");
            rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id_biblio");
                String nombre = rs.getString("nombre_biblio");
                String provincia = rs.getString("provincias_biblio");
                String ciudad = rs.getString("ciudad_biblio");
                String calle = rs.getString("calle_biblio");
                String tlf = rs.getString("telefono_biblio");
                String email = rs.getString("email_biblio");
                resultado.add(new Biblioteca(id, nombre, provincia, ciudad, calle, tlf, email));
            }
            if(resultado.size() == 0){
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return resultado;
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
        return resultado;
    }
    
    /**
     * Método para obtener una biblioteca por su provincia
     * @param busqueda
     * @return 
     */
    public static List<Biblioteca> obtenerBibliotecaPorProvincia(String busqueda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Biblioteca> resultado = new ArrayList<>();
        
        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM bibliotecas WHERE provincias_biblio LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + busqueda + "%");
            rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id_biblio");
                String nombre = rs.getString("nombre_biblio");
                String provincia = rs.getString("provincias_biblio");
                String ciudad = rs.getString("ciudad_biblio");
                String calle = rs.getString("calle_biblio");
                String tlf = rs.getString("telefono_biblio");
                String email = rs.getString("email_biblio");
                resultado.add(new Biblioteca(id, nombre, provincia, ciudad, calle, tlf, email));
            }
            if(resultado.size() == 0){
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return resultado;
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
        return resultado;
    }
    
    /**
     * Método para obtener una biblioteca por su ciudad
     * @param busqueda
     * @return 
     */
    public static List<Biblioteca> obtenerBibliotecaPorCiudad(String busqueda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Biblioteca> resultado = new ArrayList<>();
        
        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM bibliotecas WHERE ciudad_biblio LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + busqueda + "%");
            rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id_biblio");
                String nombre = rs.getString("nombre_biblio");
                String provincia = rs.getString("provincias_biblio");
                String ciudad = rs.getString("ciudad_biblio");
                String calle = rs.getString("calle_biblio");
                String tlf = rs.getString("telefono_biblio");
                String email = rs.getString("email_biblio");
                resultado.add(new Biblioteca(id, nombre, provincia, ciudad, calle, tlf, email));
            }
            if(resultado.size() == 0){
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return resultado;
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
        return resultado;
    }
    
    /**
     * Método para obtener una biblioteca por su calle
     * @param busqueda
     * @return 
     */
    public static List<Biblioteca> obtenerBibliotecaPorCalle(String busqueda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Biblioteca> resultado = new ArrayList<>();
        
        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM bibliotecas WHERE calle_biblio LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + busqueda + "%");
            rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id_biblio");
                String nombre = rs.getString("nombre_biblio");
                String provincia = rs.getString("provincias_biblio");
                String ciudad = rs.getString("ciudad_biblio");
                String calle = rs.getString("calle_biblio");
                String tlf = rs.getString("telefono_biblio");
                String email = rs.getString("email_biblio");
                resultado.add(new Biblioteca(id, nombre, provincia, ciudad, calle, tlf, email));
            }
            if(resultado.size() == 0){
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return resultado;
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
        return resultado;
    }
    
    /**
     * Método para obtener una biblioteca por su teléfono
     * @param busqueda
     * @return 
     */
    public static List<Biblioteca> obtenerBibliotecaPorTelefono(String busqueda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Biblioteca> resultado = new ArrayList<>();
        
        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM bibliotecas WHERE telefono_biblio LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + busqueda + "%");
            rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id_biblio");
                String nombre = rs.getString("nombre_biblio");
                String provincia = rs.getString("provincias_biblio");
                String ciudad = rs.getString("ciudad_biblio");
                String calle = rs.getString("calle_biblio");
                String tlf = rs.getString("telefono_biblio");
                String email = rs.getString("email_biblio");
                resultado.add(new Biblioteca(id, nombre, provincia, ciudad, calle, tlf, email));
            }
            if(resultado.size() == 0){
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return resultado;
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
        return resultado;
    }
    
    /**
     * Método para obtener una biblioteca por su email
     * @param busqueda
     * @return 
     */
    public static List<Biblioteca> obtenerBibliotecaPorEmail(String busqueda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Biblioteca> resultado = new ArrayList<>();
        
        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "SELECT * FROM bibliotecas WHERE email_biblio LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + busqueda + "%");
            rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id_biblio");
                String nombre = rs.getString("nombre_biblio");
                String provincia = rs.getString("provincias_biblio");
                String ciudad = rs.getString("ciudad_biblio");
                String calle = rs.getString("calle_biblio");
                String tlf = rs.getString("telefono_biblio");
                String email = rs.getString("email_biblio");
                resultado.add(new Biblioteca(id, nombre, provincia, ciudad, calle, tlf, email));
            }
            if(resultado.size() == 0){
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
                return resultado;
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
        return resultado;
    }
    
    /**
     * 
     * @param id 
     */
    public static void eliminarBiblioteca(int id){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "DELETE FROM bibliotecas WHERE id_biblio = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Biblioteca " + id + " eliminada correctamente");
            }else System.out.println("No se actualizaron datos");
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
    
    
    public static void actualizarBiblioteca(int id, String nombre, String telefono, String email){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "UPDATE bibliotecas "
                    + "SET telefono_biblio = ?,"
                    + "email_biblio = ? "
                    + "WHERE id_biblio = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, telefono);
            stmt.setString(2, email);
            stmt.setInt(3, id);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                String mensaje = "Nuevos datos de la biblioteca:" +
                "\nNombre: " + nombre +
                "\nEmail: " + email +
                "\nTeléfono: " + telefono + "\n";
                JOptionPane.showMessageDialog(null, mensaje, "Biblioteca actualizada", JOptionPane.INFORMATION_MESSAGE);
            }else System.out.println("No se actualizaron datos");
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
