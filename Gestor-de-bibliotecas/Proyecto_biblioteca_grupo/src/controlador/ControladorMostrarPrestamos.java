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
import modelo.Biblioteca;
import modelo.Libro;
import modelo.Prestamo;
import modelo.Socio;
import vista.MostrarPrestamos;

/**
 *
 * @author Laura Sánchez
 */
public class ControladorMostrarPrestamos implements ActionListener{
    private MostrarPrestamos vista;
    private Prestamo modelo;
    private DefaultTableModel modeloTabla;
    
    
    public ControladorMostrarPrestamos(){
        this.vista = new MostrarPrestamos();
        this.modelo = new Prestamo();
        this.modeloTabla = null;
        inicializarAL();
        crearTabla();
        agregarTodos();
        this.vista.setVisible(true);
    }

    // Métodos
    //--------------------------------------------------------------------------
    public void inicializarAL(){
        this.vista.getBtn_agregar().addActionListener(this);
        this.vista.getBtn_buscar().addActionListener(this);
        this.vista.getBtn_finalizar().addActionListener(this);
    }
    
    public void crearTabla(){
        this.modeloTabla = new DefaultTableModel();
        this.modeloTabla.addColumn("ID");
        this.modeloTabla.addColumn("Libro");
        this.modeloTabla.addColumn("Biblioteca");
        this.modeloTabla.addColumn("ID Socio");
        this.modeloTabla.addColumn("Nombre Socio");
        this.modeloTabla.addColumn("Fecha de prestamo");
        this.modeloTabla.addColumn("Devolución prevista");
    }
    
    public void agregarTodos(){
        modeloTabla.setRowCount(0);  // Vaciar tabla
        List<Prestamo> prestamos = Prestamo.obtenerPrestamos();
        //Collections.sort(listadoSocios);
        for(int i=0; i<prestamos.size(); i++){
            Prestamo prest = prestamos.get(i);
            String nombreLibro = Libro.nombreLibroPorID(prest.getId());
            this.modeloTabla.addRow(new Object[]{
                prest.getId(),
                Libro.nombreLibroPorID(prest.getLibro()),
                Biblioteca.obtenerNombreBibliotecaPorID(prest.getBiblioteca()),
                prest.getSocio(),
                Socio.obtenerNombreCompletoPorID(prest.getSocio()),
                prest.getFechaPrestamo(),
                prest.getFechaDevolucion()
            });
        }
        this.vista.getTbl_prestamo().setModel(modeloTabla);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.vista.getBtn_agregar()){
            new ControladorAgregarPrestamos();
        }
    }

}
