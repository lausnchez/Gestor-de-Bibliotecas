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
import modelo.Libro;
import vista.bibliotecariosView;

/**
 *
 * @author Paula
 */
public class BibliotecariosController {
    private bibliotecariosView vista;

    // Constructor del controlador
    public BibliotecariosController(bibliotecariosView vista) {
        this.vista = vista;
        this.vista.setVisible(true); // Asegúrate de que la vista se muestra

        // Aquí te aseguras de que los botones estén asociados con los métodos
        this.vista.getBtn_agregar().addActionListener(e -> agregarBibliotecario());
        this.vista.getBtn_editar().addActionListener(e -> editarBibliotecario());
        this.vista.getBtn_eliminar().addActionListener(e -> eliminarBibliotecario());
        this.vista.getBtn_buscar().addActionListener(e -> buscarBibliotecario());

        // También asegúrate de que la tabla se configure antes de su uso
        inicializarTabla();
    }

    private void inicializarTabla() {
        // Aquí añades los datos a la tabla de bibliotecarios
        DefaultTableModel modelo = (DefaultTableModel) this.vista.getTebl_bibliotecarios().getModel();
        modelo.setRowCount(0); // Limpiar las filas actuales si las hubiera

        // Obtener datos de bibliotecarios y agregarlos a la tabla (esto es solo un ejemplo)
        List<Bibliotecario> bibliotecarios = obtenerBibliotecarios();
        for (Bibliotecario biblio : bibliotecarios) {
            modelo.addRow(new Object[] {
                biblio.getIdTrab(),
                biblio.getBibliotecaTrab(),
                biblio.getDniTrab(),
                biblio.getNombreTrab(),
                biblio.getApellidosTrab(),
                biblio.getUsuarioTrab(),
                biblio.getTelefonoTrab(),
                biblio.getEmailTrab()
            });
        }
    }

    // Métodos para agregar, editar, eliminar bibliotecarios (implementación necesaria)
    private void agregarBibliotecario() {
        // Implementación de agregar
    }

    private void editarBibliotecario() {
        // Implementación de editar
    }

    private void eliminarBibliotecario() {
        // Implementación de eliminar
    }

    private void buscarBibliotecario() {
        // Implementación de buscar
    }

    private List<Bibliotecario> obtenerBibliotecarios() {
        // Aquí obtienes los bibliotecarios desde la base de datos
        return new ArrayList<>();  // Esto debe retornar una lista de bibliotecarios
    }
}


