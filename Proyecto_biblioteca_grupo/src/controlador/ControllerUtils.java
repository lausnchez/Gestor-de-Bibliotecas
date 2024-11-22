/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.BaseDatos;

/**
 *
 * @author Paula
 */
public class ControllerUtils {
    private BaseDatos baseDeDatos;

    // Constructores
    //--------------------------------------------------------------------------
    public ControllerUtils() {   
    }

    
    // Getters & Setters
    //--------------------------------------------------------------------------
    public BaseDatos getBaseDeDatos() {
        return baseDeDatos;
    }
 
    public void setBaseDeDatos(BaseDatos baseDeDatos) {
        this.baseDeDatos = baseDeDatos;
    }
    
    // Métodos
    //--------------------------------------------------------------------------
    public static List<String> obtenerProvinciasArrayList(){
        List<String> provincias = new ArrayList<>();
        ResultSet resultado;
        try {
            resultado = BaseDatos.miStatement.executeQuery
            ("SELECT DISTINCT provincia FROM bibliotecas");
            while(resultado.next()){
                provincias.add(resultado.getString("provincia"));
            }            
        } catch (SQLException ex) {
            //Logger.getLogger(ControllerUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error al obtener las provincias");
        }
        return provincias;
    }
    
    public static String crearEnumProvincia(){
        String valoresEnum = "";
        List<String> provincias = obtenerProvinciasArrayList();
        for(String valor: provincias){
            valoresEnum.concat(valor + ", ");
        }
        return valoresEnum;
    }
    
    /***
     * Recoge las distintas bibliotecas que están contenidas en la base de datos
     * @return 
     */
    public static List<String> obtenerBibliotecas(){
        List<String> bibliotecas = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement prepStat = null;
        ResultSet rs = null;
        try {
            conexion = BaseDatos.obtenerConnection();
            String sql = "SELECT DISTINCT nombre_biblio FROM bibliotecas;";
            prepStat = conexion.prepareStatement(sql);
            rs = prepStat.executeQuery();
            while(rs.next()){
                bibliotecas.add(rs.getString("nombre_biblio"));
            }
            BaseDatos.cerrarConnection(conexion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bibliotecas;
    }
    
    /**
     * Comprueba que un valor que le pasamos es  un valor numérico
     * @param valor
     * @return 
     */
    public static boolean controlarInt(String valor){
        try {
            Integer.parseInt(valor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
        
    /***
     * Pide un id de una biblioteca y devuelve el nombre correspondiente
     * @param idBiblioteca
     * @return 
     */
    public static String nombreBiblioteca(int idBiblioteca){
        String nombre = "";
        Connection conexion = null;
        PreparedStatement prepStat = null;
        ResultSet rs = null;
        try {
            conexion = BaseDatos.obtenerConnection();
            String sql = "SELECT nombre_biblio FROM bibliotecas WHERE id_biblio = ?;";
            prepStat = conexion.prepareStatement(sql);
            prepStat.setInt(1, idBiblioteca);
            rs = prepStat.executeQuery();
            if(rs.next()) nombre = rs.getString("nombre_biblio");
            BaseDatos.cerrarConnection(conexion);
        } catch (Exception e) {
            System.err.println("No se encontró un resultado");
            e.printStackTrace();
        } 
        return nombre;
    }
    
    /**
     * Método para obtener el ID de la biblioteca a partir del nombre de la provincia
     * @param provincia
     * @return idBiblioteca
     **/
    public static int idbibliotecaPaula(String provincia) {
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
    
    /**
     * Obtiene el ID de una biblioteca pasándole su nombre
     * @param provincia
     * @param provincia
     * @param provincia
     * @param biblioteca
     * @return 
     */
    public static int idbibliotecaLaura(String biblioteca){
        int id = -1;
        Connection conexion = null;
        PreparedStatement prepStat = null;
        ResultSet rs = null;
        try {
            conexion = BaseDatos.obtenerConnection();
            String sql = "SELECT id_biblio FROM bibliotecas WHERE nombre_biblio LIKE ? LIMIT 1;";
            prepStat = conexion.prepareStatement(sql);
            prepStat.setString(1, biblioteca);
            rs = prepStat.executeQuery();
            if(rs.next()) id = rs.getInt("id_biblio");
            BaseDatos.cerrarConnection(conexion);
        } catch (Exception e) {
            System.err.println("No se encontró un resultado");
            e.printStackTrace();
        } 
        return id;
    }
    
}

