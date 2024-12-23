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
import modelo.Socio;
import vista.MostrarSocio;

/**
 *
 * @author Laura Sánchez
 */
public class ControladorMostrarsocio implements ActionListener{
    private MostrarSocio vista;
    private Socio modelo;
    private DefaultTableModel modeloTabla;

    // Constructores
    //--------------------------------------------------------------------------
    public ControladorMostrarsocio() {
        this.vista = new MostrarSocio();
        this.modelo = new Socio();
        this.modeloTabla = null;
        inicializarAL();
        crearTabla();
        agregarTodos();
        this.vista.setVisible(true);
    }
    
    // Getters & Setters
    //--------------------------------------------------------------------------
    
    public MostrarSocio getVista() {
        return vista;
    }

    public void setVista(MostrarSocio vista) {
        this.vista = vista;
    }

    public Socio getModelo() {
        return modelo;
    }

    public void setModelo(Socio modelo) {
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
        if(e.getSource() == this.vista.getBtn_agregarUsuario()){
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
        this.vista.getBtn_agregarUsuario().addActionListener(this);
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
        this.modeloTabla.addColumn("DNI");
        this.modeloTabla.addColumn("Nombre Completo");
        this.modeloTabla.addColumn("Teléfono");
        this.modeloTabla.addColumn("Email");
        this.modeloTabla.addColumn("Biblioteca");
        this.modeloTabla.addColumn("Provincia");
        this.modeloTabla.addColumn("Sanciones");
        this.modeloTabla.addColumn("Cuenta Bancaria");
        this.modeloTabla.addColumn("Pago Realizado");
    }

    public void agregarTodos(){
        modeloTabla.setRowCount(0);  // Vaciar tabla
        List<Socio> listadoSocios = modelo.obtenerSocios();
        //Collections.sort(listadoSocios);
        for(int i=0; i<listadoSocios.size(); i++){
            Socio nuevoSocio = listadoSocios.get(i);
            String pago;
            if(nuevoSocio.isPago()) pago = "PAGADO"; else pago = "NO PAGADO";
            this.modeloTabla.addRow(new Object[]{
                nuevoSocio.getId(),
                nuevoSocio.getDni(),
                nuevoSocio.getNombre().concat(" ").concat(nuevoSocio.getApellidos()),
                nuevoSocio.getTelefono(),
                nuevoSocio.getEmail(),
                nuevoSocio.getBiblioteca(),
                nuevoSocio.getProvincia(),
                nuevoSocio.getNumSanciones(),
                nuevoSocio.getCuentaBancaria(),
                pago       
            });
        }
        this.vista.getTbl_clientes().setModel(modeloTabla);
    }
    
    /**
     * Recibe una lista que filtra por un parámetro específico
     */
    public void agregarPorParametro(List<Socio> listadoSocios){
        modeloTabla.setRowCount(0);  // Vaciar tabla
        //Collections.sort(listadoSocios);
        if(listadoSocios != null){
            for(int i=0; i<listadoSocios.size(); i++){
                Socio nuevoSocio = listadoSocios.get(i);
                String pago;
                if(nuevoSocio.isPago()) pago = "PAGADO"; else pago = "NO PAGADO";
                this.modeloTabla.addRow(new Object[]{
                    nuevoSocio.getId(),
                    nuevoSocio.getDni(),
                    nuevoSocio.getNombre().concat(" ").concat(nuevoSocio.getApellidos()),
                    nuevoSocio.getTelefono(),
                    nuevoSocio.getEmail(),
                    nuevoSocio.getBiblioteca(),
                    nuevoSocio.getProvincia(),
                    nuevoSocio.getNumSanciones(),
                    nuevoSocio.getCuentaBancaria(),
                    pago       
                });
            }
        }
        this.vista.getTbl_clientes().setModel(modeloTabla);
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
                    agregarPorParametro(Socio.buscarPorIDList(Socio.obtenerSocioPorId(id)));
                }else JOptionPane.showMessageDialog(vista, "Valor no válido", "Error", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:     // Nombre completo
                agregarPorParametro(Socio.obtenerSocioPorNombreApellidos(busqueda, true));
                break;
            case 3:     // DNI
                agregarPorParametro(Socio.obtenerSocioPorDNI(busqueda, true));
                break;
            case 4:     // Teléfono
                agregarPorParametro(Socio.obtenerSocioPorTelefono(busqueda, true));
                break;
            case 5:     // Email
                agregarPorParametro(Socio.obtenerSocioPorEmail(busqueda, true));
                break;
            case 6:     // Provincia
                agregarPorParametro(Socio.obtenerSocioPorProvincia(busqueda, true));
                break;
            case 7:     // Número de sanciones 
                if(ControllerUtils.controlarInt(busqueda)){
                    int num = Integer.parseInt(busqueda);
                    agregarPorParametro(Socio.obtenerSocioPorSanciones(num, true));
                }else JOptionPane.showMessageDialog(vista, "Valor no válido", "Error", JOptionPane.INFORMATION_MESSAGE);                
                break;
            case 8:     // Cuenta bancaria
                agregarPorParametro(Socio.obtenerSocioPorCBancaria(busqueda, true));
                break;
        }
    }
}
