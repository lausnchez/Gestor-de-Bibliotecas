/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Usuario;
import vista.MenuAdminView;
import vista.MenuView;
import vista.librosView;
import vista.usuarioLoginView;

/**
 *
 * @author Paula
 */
public class UsuarioController implements ActionListener{
    
    private usuarioLoginView usuarioView;
    private MenuAdminView vistaMenuAdmin;
    private MenuView menuView;

    public UsuarioController(usuarioLoginView usuarioView) {
        this.usuarioView = usuarioView;
        // Añadir botones
        this.usuarioView.getBtn_acceder().addActionListener(this);
        this.usuarioView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == usuarioView.getBtn_acceder()) {
            String nombreUsuario = usuarioView.getTxt_usuario().getText();
            String password = usuarioView.getTxt_contrasenia().getText();
            String tipoUsuario = (String) usuarioView.getcBox_login().getSelectedItem();

            // Convertir el tipo de usuario a un tipo del modelo (ADMINISTRADOR o BIBLIOTECARIO)
            Usuario.TIPO tipo = tipoUsuario.equals("ADMINISTRADOR/A") ? Usuario.TIPO.ADMINISTRADOR : Usuario.TIPO.TRABAJADOR;

            // Autenticación
            if (Usuario.autenticarUsuario(nombreUsuario, password)) {
                JOptionPane.showMessageDialog(usuarioView, "¡Bienvenido, " + nombreUsuario + "!", "Inicio de sesión exitoso", JOptionPane.INFORMATION_MESSAGE);
                usuarioView.dispose(); // Cerrar la ventana de login

                // Si es ADMINISTRADOR/A, mostrar el menú de administrador
                if (tipo == Usuario.TIPO.ADMINISTRADOR) {
                    vistaMenuAdmin = new MenuAdminView();
                    MenuAdminController controllerAdmin = new MenuAdminController(vistaMenuAdmin);
                    vistaMenuAdmin.setVisible(true);
                }
                // Si es BIBLIOTECARIO/A, mostrar el menú de bibliotecario
                else if (tipo == Usuario.TIPO.TRABAJADOR) {
                    menuView = new MenuView();
                    MenuController menuController = new MenuController(menuView);
                    menuView.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(usuarioView, "Usuario o contraseña incorrectos", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

