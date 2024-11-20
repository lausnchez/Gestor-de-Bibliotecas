/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Paula
 */
public class agregarLibro extends javax.swing.JFrame {
      public agregarLibro() {
        initComponents();
        setTitle("Agregar Libro");
        
    ButtonGroup grupoEstado = new ButtonGroup();
    grupoEstado.add(rBtn_disponible);
    grupoEstado.add(rBtn_presado);
    }

    public JLabel getLbl_autor() {
        return Lbl_autor;
    }

    public JLabel getLbl_editorial() {
        return Lbl_editorial;
    }


    public JLabel getLbl_isbn() {
        return Lbl_isbn;
    }

    public JLabel getLbl_precio() {
        return Lbl_precio;
    }

    public JLabel getLbl_titulo() {
        return Lbl_titulo;
    }

    public JTextField getTxt_editorial() {
        return Txt_editorial;
    }

    public JButton getBtn_agregarLibro() {
        return btn_agregarLibro;
    }

    public JComboBox<String> getcBox_ubicacion() {
        return cBox_ubicacion;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public JLabel getjLabel6() {
        return jLabel6;
    }

    public JRadioButton getrBtn_disponible() {
        return rBtn_disponible;
    }

    public JRadioButton getrBtn_presado() {
        return rBtn_presado;
    }

    public JTextField getTxt_autor() {
        return txt_autor;
    }

    public JTextField getTxt_genero() {
        return txt_genero;
    }

    public JTextField getTxt_isbn() {
        return txt_isbn;
    }

    public JTextField getTxt_precio() {
        return txt_precio;
    }

    public JTextField getTxt_titulo() {
        return txt_titulo;
    }

   
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings(value = "unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        txt_genero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Lbl_isbn = new javax.swing.JLabel();
        Lbl_titulo = new javax.swing.JLabel();
        Lbl_autor = new javax.swing.JLabel();
        Lbl_precio = new javax.swing.JLabel();
        txt_isbn = new javax.swing.JTextField();
        txt_titulo = new javax.swing.JTextField();
        txt_autor = new javax.swing.JTextField();
        cBox_ubicacion = new javax.swing.JComboBox<>();
        rBtn_presado = new javax.swing.JRadioButton();
        rBtn_disponible = new javax.swing.JRadioButton();
        btn_agregarLibro = new javax.swing.JButton();
        txt_precio = new javax.swing.JTextField();
        Lbl_editorial = new javax.swing.JLabel();
        Txt_editorial = new javax.swing.JTextField();

        jLabel6.setText("GENERO");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("AGREGAR LIBRO");

        Lbl_isbn.setText("ISBN");

        Lbl_titulo.setText("TITULO");

        Lbl_autor.setText("AUTOR");

        Lbl_precio.setText("PRECIO");

        cBox_ubicacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Madrid", "BARCELONA", "VALENCIA", "SEVILLA", "MÁLAGA", "CÓRDOBA", "ALMERÍA", "TOLEDO", "BILBAO", "CEUTA", "MELILLA" }));

        rBtn_presado.setText("PRESTADO");

        rBtn_disponible.setText("DISPONIBLE");

        btn_agregarLibro.setText("AGREGAR LIBRO");

        Lbl_editorial.setText("EDITORIAL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cBox_ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lbl_isbn)
                            .addComponent(Lbl_titulo)
                            .addComponent(Lbl_autor)
                            .addComponent(Lbl_precio)
                            .addComponent(Lbl_editorial)
                            .addComponent(rBtn_disponible)
                            .addComponent(rBtn_presado))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_isbn)
                                .addComponent(txt_titulo)
                                .addComponent(txt_autor)
                                .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Txt_editorial))
                            .addComponent(btn_agregarLibro))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lbl_isbn)
                    .addComponent(txt_isbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lbl_titulo)
                    .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lbl_autor)
                    .addComponent(txt_autor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lbl_precio)
                    .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lbl_editorial)
                    .addComponent(Txt_editorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(cBox_ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(rBtn_presado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rBtn_disponible)
                        .addContainerGap(37, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_agregarLibro)
                        .addGap(43, 43, 43))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Lbl_autor;
    private javax.swing.JLabel Lbl_editorial;
    private javax.swing.JLabel Lbl_isbn;
    private javax.swing.JLabel Lbl_precio;
    private javax.swing.JLabel Lbl_titulo;
    private javax.swing.JTextField Txt_editorial;
    private javax.swing.JButton btn_agregarLibro;
    private javax.swing.JComboBox<String> cBox_ubicacion;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton rBtn_disponible;
    private javax.swing.JRadioButton rBtn_presado;
    private javax.swing.JTextField txt_autor;
    private javax.swing.JTextField txt_genero;
    private javax.swing.JTextField txt_isbn;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txt_titulo;
    // End of variables declaration//GEN-END:variables
}
