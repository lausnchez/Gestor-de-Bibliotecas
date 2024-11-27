/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Bibliotecario;
import modelo.Libro;
import vista.AgregarBibliotecarioView;
import vista.EditarBibliotecarioView;
import vista.bibliotecariosView;

/**
 *
 * @author Paula
 */
public class BibliotecariosController implements ActionListener {
    private bibliotecariosView vista;
    private Bibliotecario modelo;
    private DefaultTableModel modeloTabla;
    private bibliotecariosView bibliotecariosView;
    private AgregarBibliotecarioView agregarBibliotecariosController;
 
   
    
    public BibliotecariosController(bibliotecariosView vista,Bibliotecario modelo){
    this.vista = vista;
    this.modeloTabla = new DefaultTableModel();
    this.modelo = modelo;
    crearTabla();
    agregarTodos();

    // Verifica que los botones están inicializados antes de agregar los action listeners
    this.vista.getBtn_agregar().addActionListener(this);
    this.vista.getBtn_editar().addActionListener(this);
    this.vista.getBtn_eliminar().addActionListener(this);
    this.vista.getcBox_buscar().addActionListener(this);
    this.vista.getTxt_buscar().addActionListener(this);
    this.vista.getBtn_actualizar().addActionListener(this);

    this.vista.setVisible(true);
}
    
    // Métodos principales
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.getBtn_agregar()) {
            agregarBibliotecario();
        }
        else if (e.getSource() == this.vista.getBtn_editar()) {
            editarBibliotecario();
        }
        else if (e.getSource() == this.vista.getBtn_eliminar()) {
            borrarBibliotecario();
        }
        else if (e.getSource() == this.vista.getcBox_buscar()) {
            actualizarTabla();
        } else if (e.getSource() == this.vista.getBtn_actualizar()) {
            actualizarTabla();
        }
    }

   private void agregarBibliotecario() {
    AgregarBibliotecarioView agregarView = new AgregarBibliotecarioView();
    new AgregarBibliotecarioController(agregarView, bibliotecariosView);
    agregarView.setVisible(true);
}

   public void crearTabla() {
    if (this.vista.getTebl_bibliotecarios() != null) {
        this.modeloTabla = new DefaultTableModel();
        this.modeloTabla.addColumn("ID");
        this.modeloTabla.addColumn("Biblioteca");
        this.modeloTabla.addColumn("DNI");
        this.modeloTabla.addColumn("Nombre");
        this.modeloTabla.addColumn("Usuario");
        this.modeloTabla.addColumn("Teléfono");
        this.modeloTabla.addColumn("Email");
        this.vista.getTebl_bibliotecarios().setModel(modeloTabla);
    } else {
        System.err.println("Error: tebl_bibliotecarios no está inicializado.");
    }
}

    public void agregarTodos() {
        modeloTabla.setRowCount(0);
        List<Bibliotecario> lista = modelo.obtenerTodos(); // Método por implementar
        for (Bibliotecario b : lista) {
            modeloTabla.addRow(new Object[]{
                b.getIdTrab(),
                b.getBibliotecaTrab(),
                b.getDniTrab(),
                b.getNombreTrab() + " " + b.getApellidosTrab(),
                b.getUsuarioTrab(),
                b.getTelefonoTrab(),
                b.getEmailTrab()
            });
        }
    }

    public void buscarBibliotecario() {
        String busqueda = this.vista.getTxt_buscar().getText().trim();
        modeloTabla.setRowCount(0);
        List<Bibliotecario> resultados = modelo.buscarPorParametro(busqueda); // Método por implementar
        for (Bibliotecario b : resultados) {
            modeloTabla.addRow(new Object[]{
                b.getIdTrab(),
                b.getBibliotecaTrab(),
                b.getDniTrab(),
                b.getNombreTrab() + " " + b.getApellidosTrab(),
                b.getUsuarioTrab(),
                b.getTelefonoTrab(),
                b.getEmailTrab()
            });
        }
    }

    public void borrarBibliotecario() {
        int fila = this.vista.getTebl_bibliotecarios().getSelectedRow();
        if (fila != -1) {
            int id = (int) this.vista.getTebl_bibliotecarios().getValueAt(fila, 0);
            Bibliotecario.eliminarBibliotecario(id);
            JOptionPane.showMessageDialog(vista, "Bibliotecario eliminado correctamente.");
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un bibliotecario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

 public void editarBibliotecario() {
    int fila = this.vista.getTebl_bibliotecarios().getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(vista, "Seleccione un bibliotecario.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int id = (int) this.vista.getTebl_bibliotecarios().getValueAt(fila, 0);
    Bibliotecario biblio = Bibliotecario.obtenerBibliotecarioPorId(id);

    if (biblio == null) {
        JOptionPane.showMessageDialog(vista, "Error al cargar los datos del bibliotecario.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    EditarBibliotecarioView editarView = new EditarBibliotecarioView();

    // Pasar los datos al formulario
    editarView.getTxt_id().setText(String.valueOf(biblio.getIdTrab()));
    editarView.getTxt_nombre().setText(biblio.getNombreTrab());
    editarView.getTxt_dni().setText(biblio.getDniTrab());
    editarView.getTxt_email().setText(biblio.getEmailTrab());
    editarView.getTxt_telefono().setText(biblio.getTelefonoTrab());

    editarView.getTxt_id().setEnabled(false);
    editarView.getBtn_guardar().addActionListener(e -> guardarCambios(editarView, biblio));

    editarView.setVisible(true);
}
 
 private void guardarCambios(EditarBibliotecarioView editarView, Bibliotecario biblio) {
    try {
        // Obtener nuevos valores de los campos
        String nuevoNombre = editarView.getTxt_nombre().getText();
        String nuevoDni = editarView.getTxt_dni().getText();
        String nuevoEmail = editarView.getTxt_email().getText();
        String nuevoTelefono = editarView.getTxt_telefono().getText();

        // Validaciones simples (puedes ampliarlas)
        if (nuevoNombre.isEmpty() || nuevoDni.isEmpty() || nuevoEmail.isEmpty() || nuevoTelefono.isEmpty()) {
            JOptionPane.showMessageDialog(editarView, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        biblio.setNombreTrab(nuevoNombre);
        biblio.setDniTrab(nuevoDni);
        biblio.setEmailTrab(nuevoEmail);
        biblio.setTelefonoTrab(nuevoTelefono);

        // Guardar cambios en la base de datos
        if (Bibliotecario.actualizarBibliotecario(biblio)) {
            JOptionPane.showMessageDialog(editarView, "Bibliotecario actualizado con éxito.");
            editarView.dispose();
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(editarView, "Error al actualizar el bibliotecario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(editarView, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}
  
  

    public void actualizarTabla() {
        agregarTodos();
    }
}
