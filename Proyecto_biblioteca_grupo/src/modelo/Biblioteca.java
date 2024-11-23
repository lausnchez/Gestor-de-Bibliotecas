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
    
    public Biblioteca(String nombre, String provincia, String ciudad, String calle, String telefono, String email) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.calle = calle;
        this.telefono = telefono;
        this.email = email;
    }
    
    //Getters & Setters
    //--------------------------------------------------------------------------

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
    
    
    public static List<Biblioteca> obtenerTodasLasBibliotecasPaula() {
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
    /*
    public void actualizarBiblioteca() {
        String sql = "UPDATE bibliotecas SET ubi_biblioteca = '" + this.provincia.getNombre() +
                     "', tel_biblioteca = '" + this.telefono + "' WHERE id_biblioteca = " + this.idBiblioteca;
        BaseDatos.ejecutarUpdate(sql);
    }  */
  
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
    public static List<Biblioteca> obtenerTodasLasBiblioteca() {
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
    /*public static void eliminarBiblioteca(int id) {
        String sql = "DELETE FROM bibliotecas WHERE id_biblioteca = " + id;
        BaseDatos.ejecutarUpdate(sql);
    }*/

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
    
    /**
     * Método actualizado para registrar una biblioteca.
     * Incluye el nombre, provincia, ciudad, calle, email y teléfono
     * @param biblio
     */
    public static void registrarBiblioteca(Biblioteca biblio) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = BaseDatos.obtenerConnection();
            String sql = "INSERT INTO bibliotecas(nombre_biblio, provincias_biblio, ciudad_biblio, calle_biblio, telefono_biblio, email_biblio) VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, biblio.getNombre());
            stmt.setString(2, biblio.getProvincia());
            stmt.setString(3, biblio.getCiudad());
            stmt.setString(4, biblio.getCalle());
            stmt.setString(5, biblio.getTelefono());
            stmt.setString(6, biblio.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar biblioteca");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión");
                e.printStackTrace();
            }
        }
    }
}



