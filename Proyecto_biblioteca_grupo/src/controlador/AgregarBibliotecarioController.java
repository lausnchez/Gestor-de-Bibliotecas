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

    public AgregarBibliotecarioController(AgregarBibliotecarioView vista) {
        this.vista = vista;
        this.bibliotecariosView=bibliotecariosView;
        this.vista.getBtn_agregar().addActionListener(this);  // Establecemos el evento para el botón
    }

    private void agregarBibliotecario() {
        String nombre = vista.getTxt_nombre().getText().trim();
        String apellidos = vista.getTxt_apellidos().getText().trim();
        String dni = vista.getTxt_dni().getText().trim();
        String telefono = vista.getTxt_telefono().getText().trim();
        String email = vista.getTxt_email().getText().trim();
        String biblioteca = (String) vista.getcBox_bibliotecas().getSelectedItem();

        // Validaciones básicas
        if (nombre.isEmpty() || apellidos.isEmpty() || dni.isEmpty() || telefono.isEmpty() || email.isEmpty() || biblioteca.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Creamos un objeto Bibliotecario
        Bibliotecario nuevoBibliotecario = new Bibliotecario();
        nuevoBibliotecario.setNombreTrab(nombre);
        nuevoBibliotecario.setApellidosTrab(apellidos);
        nuevoBibliotecario.setDniTrab(dni);
        nuevoBibliotecario.setTelefonoTrab(telefono);
        nuevoBibliotecario.setEmailTrab(email);
        // Asumimos que el ID de la biblioteca es un valor numérico
        nuevoBibliotecario.setBibliotecaTrab(Integer.parseInt(biblioteca));

        // Insertamos el bibliotecario en la base de datos
        boolean agregado = Bibliotecario.agregarBibliotecario(nuevoBibliotecario);

        if (agregado) {
            JOptionPane.showMessageDialog(vista, "Bibliotecario agregado correctamente.");
            // Aquí llamamos al método para actualizar la tabla
            actualizarTablaBibliotecarios();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al agregar bibliotecario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.getBtn_agregar()) {
            agregarBibliotecario();  // Se ejecuta cuando se presiona el botón
        }
    }

    // Método para actualizar la tabla de bibliotecarios
    private void actualizarTablaBibliotecarios() {
        // Obtén la lista de bibliotecarios de la base de datos
        List<Bibliotecario> bibliotecarios = Bibliotecario.obtenerTodos();

        // Actualiza la tabla o cualquier componente de la vista que estés usando
        // Si estás usando un JTable, por ejemplo:
        DefaultTableModel modelo = (DefaultTableModel) bibliotecariosView.getTebl_bibliotecarios().getModel();
        modelo.setRowCount(0);  // Limpiar la tabla antes de volver a llenarla

        // Llenamos la tabla con los datos de los bibliotecarios
        for (Bibliotecario biblio : bibliotecarios) {
            modelo.addRow(new Object[]{
                biblio.getNombreTrab(),
                biblio.getApellidosTrab(),
                biblio.getDniTrab(),
                biblio.getTelefonoTrab(),
                biblio.getEmailTrab()
            });
        }
    }
}
