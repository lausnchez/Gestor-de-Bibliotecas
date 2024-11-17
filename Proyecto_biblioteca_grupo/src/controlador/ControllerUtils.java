/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.BaseDatos;

/**
 *
 * @author Laura Sánchez
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
}
