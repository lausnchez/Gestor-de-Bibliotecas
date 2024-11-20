/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Socio;
import vista.MostrarAntiguosSocios;

/**
 *
 * @author Laura Sánchez
 */
public class ControladorMostrarAntiguosSocios implements ActionListener{
    private MostrarAntiguosSocios vista;
    private Socio modelo;
    private DefaultTableModel modeloTabla;

    // Constructores
    //--------------------------------------------------------------------------
    public ControladorMostrarAntiguosSocios() {
        this.vista = new MostrarAntiguosSocios();
        this.modelo = new Socio();
        this.modeloTabla = null;
        inicializarAL();
        crearTabla();
        agregarTodos();
        this.vista.setVisible(true);
    }
    
    // Getters & Setters
    //--------------------------------------------------------------------------
    
    public MostrarAntiguosSocios getVista() {
        return vista;
    }

    public void setVista(MostrarAntiguosSocios vista) {
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
            buscarSocio();
        }
    }
    
    /**
     * Inicializa todos los actionListener necesarios de la vista
     */
    public void inicializarAL(){
        this.vista.getBtn_buscar().addActionListener(this);   
    }
    
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
        List<Socio> listadoSocios = Socio.obtenerAntiguosSocios();
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
    
    public void buscarSocio(){
        
    }
}
