/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controlador.ControladorMostrarBibliotecas;
import controlador.ControladorMostrarPrestamos;
import controlador.UsuarioController;
import modelo.Libro;
import vista.librosViewAntigua;
import vista.usuarioLoginView;

/**
 *
 * @author Paula
 */
public class main {
    public static void main(String[] args) {  
        // Crear la vista de login
        usuarioLoginView loginView = new usuarioLoginView();
        UsuarioController controller = new UsuarioController(loginView);
        loginView.setVisible(true);  // Asegúrate de que la ventana sea visible
    
    }
}
