/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import modelo.Libro;
import vista.usuarioLoginView;

/**
 *
 * @author Paula
 */
public class main {
    public static void main(String[] args) {
             // Crear la vista de login
        usuarioLoginView loginView = new usuarioLoginView();
        
        // Crear el controlador y pasarle la vista
        UsuarioController controller = new UsuarioController(loginView);
        
        // Mostrar la vista de login
        loginView.setVisible(true);  // Asegúrate de que la ventana sea visible
    }
}
