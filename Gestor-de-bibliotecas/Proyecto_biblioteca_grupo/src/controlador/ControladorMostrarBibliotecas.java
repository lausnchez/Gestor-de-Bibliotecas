/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.BaseDatos;
import modelo.Biblioteca;
import vista.MostrarBibliotecas;

/**
 *
 * @author Laura Sánchez
 */
public class ControladorMostrarBibliotecas implements ActionListener{
    private MostrarBibliotecas vista;
    private Biblioteca modelo;
    private DefaultTableModel modeloTabla;

    // Constructores
    //--------------------------------------------------------------------------
    public ControladorMostrarBibliotecas() {
        this.vista = new MostrarBibliotecas();
        this.modelo = new Biblioteca();
        this.modeloTabla = null;
        inicializarAL();
        crearTabla();
        agregarTodos();
        this.vista.setVisible(true);
    }
    
    // Getters & Setters
    //--------------------------------------------------------------------------
    
    public MostrarBibliotecas getVista() {
        return vista;
    }

    public void setVista(MostrarBibliotecas vista) {
        this.vista = vista;
    }

    public Biblioteca getModelo() {
        return modelo;
    }

    public void setModelo(Biblioteca modelo) {
        this.modelo = modelo;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }
    
    // Métodos
    //--------------------------------------------------------------------------
    /**
     * Método de implementación del ActionListener
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.vista.getBtn_buscar()){
            if(!this.vista.getTxt_buscador().getText().trim().isEmpty()) 
                buscarBiblioteca();
        }
        if(e.getSource() == this.vista.getBtn_editar()){
            editarBiblio();
        }
        /*if(e.getSource() == this.vista.getBtn_eliminar()){
            int id = this.vista.getTbl_biblio().getSelectedRow();
            eliminarBiblioteca(id);
        }*/
        if(e.getSource() == this.vista.getBtn_agregar()){
            new ControladorAgregarBibliotecas();
        }
        if(e.getSource() == this.vista.getBtn_refresh()){
            actualizarTabla();
        }
    }
    
    /**
     * Inicializa todos los actionListener necesarios de la vista
     */
    public void inicializarAL(){
        this.vista.getBtn_buscar().addActionListener(this);
        this.vista.getBtn_agregar().addActionListener(this);
        this.vista.getBtn_editar().addActionListener(this);
        //this.vista.getBtn_eliminar().addActionListener(this);
        this.vista.getBtn_refresh().addActionListener(this);   
    }
    
    /**
     * Actualiza los contenidos de la tabla
     */
    public void actualizarTabla(){
        modeloTabla.setRowCount(0);  
        agregarTodos();
    }
    
    /**
     * Crea el modelo para la tabla de la vista MostrarSocio
     */
    public void crearTabla(){
        this.modeloTabla = new DefaultTableModel();
        this.modeloTabla.addColumn("ID");
        this.modeloTabla.addColumn("Nombre");
        this.modeloTabla.addColumn("Provincia");
        this.modeloTabla.addColumn("Ciudad");
        this.modeloTabla.addColumn("Calle");
        this.modeloTabla.addColumn("Teléfono");
        this.modeloTabla.addColumn("Email");
    }

    public void agregarTodos(){
        modeloTabla.setRowCount(0);  // Vaciar tabla
        List<Biblioteca> listadoSocios = Biblioteca.obtenerBibliotecas();
        //Collections.sort(listadoSocios);
        for(int i=0; i<listadoSocios.size(); i++){
            Biblioteca nuevaBiblio = listadoSocios.get(i);
            this.modeloTabla.addRow(new Object[]{
                nuevaBiblio.getIdBiblioteca(),
                nuevaBiblio.getNombre(),
                nuevaBiblio.getProvincia(),
                nuevaBiblio.getCiudad(),
                nuevaBiblio.getCalle(),
                nuevaBiblio.getTelefono(),
                nuevaBiblio.getEmail()
            });
        }
        this.vista.getTbl_biblio().setModel(modeloTabla);
    }
    
    /**
     * Recibe una lista que filtra por un parámetro específico
     * @param listado
     */
    public void agregarPorParametro(List<Biblioteca> listado){
        modeloTabla.setRowCount(0);  // Vaciar tabla
        //Collections.sort(listadoSocios);
        if(listado != null){
            for(int i=0; i<listado.size(); i++){
                Biblioteca nuevaBiblio = listado.get(i);
                this.modeloTabla.addRow(new Object[]{
                nuevaBiblio.getIdBiblioteca(),
                nuevaBiblio.getNombre(),
                nuevaBiblio.getProvincia(),
                nuevaBiblio.getCiudad(),
                nuevaBiblio.getCalle(),
                nuevaBiblio.getTelefono(),
                nuevaBiblio.getEmail()
            });
            }
        }
        this.vista.getTbl_biblio().setModel(modeloTabla);
    }
    
    //Métodos de borrar Biblioteca
    public void borrarBiblioteca(){
        int id = -1;
        if(this.vista.getTbl_biblio().getSelectedRow() != -1){
            Object idTabla = this.vista.getTbl_biblio().getValueAt(this.vista.getTbl_biblio().getSelectedRow(), 0);
            //Socio socioEncontrado = Socio.obtenerSocioPorId((int)idTabla);
            Biblioteca biblioEncontrada = Biblioteca.obtenerBibliotecaUnicaID((int)idTabla);
            String mensaje = "¿Desea eliminar este usuario?" +
                "\nID: " + biblioEncontrada.getIdBiblioteca() +
                "\nNombre: " + biblioEncontrada.getNombre() +
                "\nProvincia: " + biblioEncontrada.getProvincia() + 
                "\nCiudad: " + biblioEncontrada.getCiudad() +
                "\nCalle: " + biblioEncontrada.getCalle() +
                "\nTeléfono: " + biblioEncontrada.getTelefono() +
                "\nEmail: " + biblioEncontrada.getEmail() + "\n";
            int opcion = JOptionPane.showConfirmDialog(this.vista, mensaje, "Eliminar usuario", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(opcion == JOptionPane.YES_NO_OPTION){
                Biblioteca.eliminarBiblioteca((int)idTabla);
            }
        }else
            JOptionPane.showMessageDialog(this.vista, "Seleccione un usuario a eliminar", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Método para eliminar bibliotecas de la base de datos
     * @param id 
     */
    public static Boolean eliminarBiblioteca(int id){
        Boolean validacion = false;
        Connection conexion = null;
        PreparedStatement prepStat = null;
        try {
            conexion = BaseDatos.obtenerConnection();
            String sql = "DELETE FROM bibliotecas WHERE id_biblio = ?";
            prepStat = conexion.prepareStatement(sql);
            prepStat.setInt(1, id);
            prepStat.executeUpdate();
            if(Biblioteca.obtenerBibliotecaUnicaID(id) == null){
                JOptionPane.showMessageDialog(null, "Biblioteca eliminada correctamente" , "Borrado correcto", JOptionPane.INFORMATION_MESSAGE);
            }else JOptionPane.showMessageDialog(null, "La biblioteca no pudo ser borrada" , "Borrado incorrecto", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return validacion;     
    }
    
    /*
    public void editarSocio(){
        int id = -1;
        if(this.vista.getTbl_clientes().getSelectedRow() != -1){
            Object idTabla = this.vista.getTbl_clientes().getValueAt(this.vista.getTbl_clientes().getSelectedRow(), 0); // Cogemos la fila
            Socio socioEncontrado = Socio.obtenerSocioPorId((int)idTabla);  // Creamos un socio con los datos seleccionados
            System.out.println(socioEncontrado.getBiblioteca());
            int idBiblio = Integer.parseInt(socioEncontrado.getBiblioteca());
            //socioEncontrado.setBiblioteca(ControllerUtils.nombreBiblioteca(idBiblio));
            new ControladorAgregarUsuario(socioEncontrado);
        }else JOptionPane.showMessageDialog(this.vista, "Seleccione un usuario para editar", "Error", JOptionPane.ERROR_MESSAGE);
    }
    */
    
    public void buscarBiblioteca(){
        int selector = this.vista.getcBox_filtro().getSelectedIndex();
        String busqueda = this.vista.getTxt_buscador().getText().trim();
        modeloTabla.setRowCount(0);
        switch(selector){
            case 0:     // Mostrar todos
                agregarTodos();
                break;
            case 1:     // ID
                if(ControllerUtils.controlarInt(busqueda)){
                    int id = Integer.parseInt(busqueda);
                    agregarPorParametro(Biblioteca.obtenerBibliotecasPorID(id));
                }else JOptionPane.showMessageDialog(vista, "Valor no válido", "Error", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:     // Nombre
                agregarPorParametro(Biblioteca.obtenerBibliotecaPorNombre(busqueda));
                break;
            case 3:     // Provincias
                agregarPorParametro(Biblioteca.obtenerBibliotecaPorProvincia(busqueda));
                break;
            case 4:     // Ciudad
                agregarPorParametro(Biblioteca.obtenerBibliotecaPorCiudad(busqueda));
                break;
            case 5:     // Calle
                agregarPorParametro(Biblioteca.obtenerBibliotecaPorCalle(busqueda));
                break;
            case 6:     // Teléfono
                agregarPorParametro(Biblioteca.obtenerBibliotecaPorTelefono(busqueda));
                break;
            case 7:     // Email
                agregarPorParametro(Biblioteca.obtenerBibliotecaPorEmail(busqueda));
                break;
        }
    }
    
    /**
     * Escoge la fila seleccionada de la tabla
     */
    public void editarBiblio(){
        int id = -1;
        if(this.vista.getTbl_biblio().getSelectedRow() != -1){  // Comprobamos la fila
            Object idTabla = this.vista.getTbl_biblio().getValueAt(this.vista.getTbl_biblio().getSelectedRow(), 0); // Cogemos el id de la fila seleccionada
            Biblioteca biblioEncontrada = Biblioteca.obtenerBibliotecaUnicaID((int)idTabla);  // Creamos una biblioteca con los datos seleccionados
            new ControladorAgregarBibliotecas(biblioEncontrada);
        }else JOptionPane.showMessageDialog(this.vista, "Seleccione un usuario para editar", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
