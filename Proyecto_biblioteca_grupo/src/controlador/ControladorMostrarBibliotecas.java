/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
                buscarSocio();
        }
        if(e.getSource() == this.vista.getBtn_editar()){
            editarSocio();
        }
        if(e.getSource() == this.vista.getBtn_eliminar()){
            borrarSocio();
        }
        if(e.getSource() == this.vista.getBtn_agregar()){
            new ControladorAgregarUsuario();
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
        this.vista.getBtn_eliminar().addActionListener(this);
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
    
    //Métodos de borrar Socios
    public void borrarSocio(){
        int id = -1;
        if(this.vista.getTbl_clientes().getSelectedRow() != -1){
            Object idTabla = this.vista.getTbl_clientes().getValueAt(this.vista.getTbl_clientes().getSelectedRow(), 0);
            Socio socioEncontrado = Socio.obtenerSocioPorId((int)idTabla);
            
            String mensaje = "¿Desea eliminar este usuario?" +
                "\nDNI: " + socioEncontrado.getDni() +
                "\nNombre: " + socioEncontrado.getNombre() + " " + socioEncontrado.getApellidos() +
                "\nProvincia: " + socioEncontrado.getProvincia() + 
                "\nTelefono: " + socioEncontrado.getTelefono() +
                "\nEmail: " + socioEncontrado.getEmail() + "\n";
            int opcion = JOptionPane.showConfirmDialog(this.vista, mensaje, "Eliminar usuario", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(opcion == JOptionPane.YES_NO_OPTION){
                Socio.eliminarSocio((int)idTabla);
            }
        }else
            JOptionPane.showMessageDialog(this.vista, "Seleccione un usuario a eliminar", "Error", JOptionPane.ERROR_MESSAGE);
    }

    
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
    
    
    public void buscarSocio(){
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
                    agregarPorParametro(Biblioteca.obtenerBibliotecaPorID(id));
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
}
