/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Usuario;
import vista.MenuAdminView;
import vista.librosView;
import vista.usuarioLoginView;

/**
 *
 * @author Paula
 */
public class UsuarioController implements ActionListener{
    
    private usuarioLoginView usuarioView;
    private MenuAdminView vistaMenuAdmin;

    public UsuarioController(usuarioLoginView usuarioView) {
        this.usuarioView = usuarioView;
        this.vistaMenuAdmin=vistaMenuAdmin;
       //Añador los eventos actionListener para que se puedan usar los botones
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


            if (Usuario.autenticarUsuario(nombreUsuario, password)) {
                JOptionPane.showMessageDialog(usuarioView, "¡Bienvenido, " + nombreUsuario + "!", "Inicio de sesión exitoso", JOptionPane.INFORMATION_MESSAGE);
                
                usuarioView.dispose(); // Cerrar la ventana de login
            MenuAdminView vistaMenuAdmin = new MenuAdminView();
            MenuAdminController controller = new MenuAdminController(vistaMenuAdmin);
            vistaMenuAdmin.setVisible(true);
            } else {
                // Si la autenticación falla
                JOptionPane.showMessageDialog(usuarioView, "Usuario o contraseña incorrectos", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

