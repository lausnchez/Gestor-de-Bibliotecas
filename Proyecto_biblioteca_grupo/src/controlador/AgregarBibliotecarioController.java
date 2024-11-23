/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Bibliotecario;
import vista.AgregarBibliotecarioView;
import vista.bibliotecariosView;

/**
 *
 * @author Paula
 */
public class AgregarBibliotecarioController implements ActionListener{
       private AgregarBibliotecarioView vista;
    private bibliotecariosView bibliotecariosView;

    public AgregarBibliotecarioController(AgregarBibliotecarioView vista, bibliotecariosView bibliotecariosView) {
        this.vista = vista;
        this.bibliotecariosView = bibliotecariosView;
        this.vista.getBtn_agregar().addActionListener(this); 
    }

    // Método para agregar un bibliotecario
    private void agregarBibliotecario() {
        String nombre = vista.getTxt_nombre().getText().trim();
        String apellidos = vista.getTxt_apellidos().getText().trim();
        String dni = vista.getTxt_dni().getText().trim();
        String telefono = vista.getTxt_telefono().getText().trim();
        String email = vista.getTxt_email().getText().trim();
        String biblioteca = (String) vista.getcBox_bibliotecas().getSelectedItem();  // Obtener el valor seleccionado del JComboBox

        // Validaciones de los campos
        if (nombre.isEmpty() || apellidos.isEmpty() || dni.isEmpty() || telefono.isEmpty() || email.isEmpty() || biblioteca.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Creamos un objeto Bibliotecario con los valores de los campos
        Bibliotecario nuevoBibliotecario = new Bibliotecario();
        nuevoBibliotecario.setNombreTrab(nombre);
        nuevoBibliotecario.setApellidosTrab(apellidos);
        nuevoBibliotecario.setDniTrab(dni);
        nuevoBibliotecario.setTelefonoTrab(telefono);
        nuevoBibliotecario.setEmailTrab(email);
        
        
        nuevoBibliotecario.setBibliotecaTrab(biblioteca); 

        // Intentamos agregar el bibliotecario a la base de datos
        boolean agregado = Bibliotecario.agregarBibliotecario(nuevoBibliotecario);

        if (agregado) {
            JOptionPane.showMessageDialog(vista, "Bibliotecario agregado correctamente.");
            actualizarTablaBibliotecarios();  
        } else {
            JOptionPane.showMessageDialog(vista, "Error al agregar bibliotecario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.getBtn_agregar()) {
            agregarBibliotecario(); 
        }
    }

    /**
     * actualiza la tabla de bibliotecario
     */
    private void actualizarTablaBibliotecarios() {
        List<Bibliotecario> bibliotecarios = Bibliotecario.obtenerTodos();

        // Actualizamos
        DefaultTableModel modelo = (DefaultTableModel) bibliotecariosView.getTebl_bibliotecarios().getModel();
        modelo.setRowCount(0);  // Limpiamos la tabla antes de llenarla con los nuevos datos

        // Llenamos la tabla 
        for (Bibliotecario biblio : bibliotecarios) {
            modelo.addRow(new Object[]{
                biblio.getNombreTrab(),
                biblio.getApellidosTrab(),
                biblio.getDniTrab(),
                biblio.getTelefonoTrab(),
                biblio.getEmailTrab(),
                biblio.getBibliotecaTrab() 
            });
        }
    }
}
