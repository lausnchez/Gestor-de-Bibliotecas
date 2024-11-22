/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controlador.ControladorMostrarBibliotecas;
import controlador.UsuarioController;
import modelo.Libro;
import vista.librosView;
import vista.usuarioLoginView;

/**
 *
 * @author Paula
 */
public class main {
    public static void main(String[] args) {  
        //new ControladorMostrarBibliotecas();
        // Crear la vista de login
        usuarioLoginView loginView = new usuarioLoginView();
        UsuarioController controller = new UsuarioController(loginView);
        loginView.setVisible(true);  // Asegúrate de que la ventana sea visible
        
      /*
     // Crear la vista de libros
        librosView librosView = new librosView();

         // Crear el modelo de Libro (modelo.Libro), asumiendo que puedes usar un constructor sin parámetros o el que se ajuste a tu modelo
        Libro libroModelo = new Libro( 0,                // id (por ejemplo, 0 o el valor que prefieras)
            "978-3-16-148410-0", 
            "Título de prueba",  
            "Autor de prueba", 
            "Editorial de prueba", 
            29.99f,
            '1',
            true);
        // Crear el controlador y asociarlo con la vista y el modelo
        LibrosViewController controller = new LibrosViewController(libroModelo);*/
    }
}
